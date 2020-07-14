package dev.throwouterror.game.server

import dev.throwouterror.eventbus.annotation.EventHandler
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.data.JsonUtils
import xyz.baddeveloper.lwsl.server.SocketServer
import xyz.baddeveloper.lwsl.server.events.ServerConnectEvent
import xyz.baddeveloper.lwsl.server.events.ServerDisconnectEvent
import xyz.baddeveloper.lwsl.server.events.ServerPacketReceivedEvent
import java.io.IOException
import java.util.*

/**
 * Creates a new game server with the port and the maximum number of players.
 * You can set the maxConnections to 0 so that there can be an infinite amount of players.
 */
class ServerGame(port: Int, private val maxConnections: Int) : Thread() {

    val server: SocketServer = SocketServer(port)
    private val players: ArrayList<ServerPlayer> = arrayListOf()

    override fun run() {
        server.setMaxConnections(maxConnections)
                .addEventListener(this)
        server.start();
    }

    @EventHandler
    fun onPacketReceived(event: ServerPacketReceivedEvent) {
        val packet = event.packet
        val data = packet.getObject();
        if (packet.isPacket(PlayerInfoPacket::class.java) && data.getString("type") == "updateTransform") {
            val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
            event.client.sendPacket(PlayerInfoPacket("playerTransform", PlayerInfo(info.id, info.transform)))
        }
    }

    @EventHandler
    fun onConnect(event: ServerConnectEvent) {
        println(String.format("Client connected! (%s)", event.client.socket.remoteSocketAddress.toString()))
        val player = ServerPlayer(event.client, PlayerInfo(UUID.randomUUID(), Transform.pos(0f, 0f, 0f)))

        event.client.sendPacket(PlayerInfoPacket("createPlayer", PlayerInfo.fromPlayer(player)))
        players.add(player)
    }

    @EventHandler
    fun onDisconnect(event: ServerDisconnectEvent) {
        println(String.format("Client disconnected! (%s)", event.client.socket.remoteSocketAddress.toString()))
    }
}

fun main(args: Array<String>) {
    try {
        if (args.isNotEmpty() && args[0] != "") {
            val port = args[0].toInt()
            val t: Thread = ServerGame(port, 0)
            t.start()
        } else {
            val t: Thread = ServerGame(3000, 0)
            t.start()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}