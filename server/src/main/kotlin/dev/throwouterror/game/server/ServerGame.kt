package dev.throwouterror.game.server

import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.data.JsonUtils
import xyz.baddeveloper.lwsl.Events
import xyz.baddeveloper.lwsl.server.ClientCreationHandler
import xyz.baddeveloper.lwsl.server.SocketHandler
import xyz.baddeveloper.lwsl.server.SocketServer
import xyz.baddeveloper.lwsl.server.events.ServerPacketEvent
import java.io.IOException
import java.util.*

/**
 * Creates a new game server with the port and the maximum number of players.
 * You can set the maxConnections to 0 so that there can be an infinite amount of players.
 */
class ServerGame(private val port: Int, private val maxConnections: Int) {

    val server: SocketServer = SocketServer(port)
    private val players: ArrayList<ServerPlayer> = arrayListOf()

    fun start() {
        server.setMaxConnections(maxConnections)
                .handleClientCreation(object : ClientCreationHandler {
                    override fun createClient(socketHandler: SocketHandler) {
                        server.clients[socketHandler.id] = socketHandler
                    }
                })
        server.createEventBus()
                .observeEvents().subscribe { e ->
                    when (e.type) {
                        Events.PACKET_RECEIVED.toString() -> {
                            val event = e as ServerPacketEvent
                            val data = event.packet.jsonObject
                            if (event.packet.isPacket(PlayerInfoPacket::class.java) && data.getString("type") == "updateTransform") {
                                val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
                                players.filter { it.id != info.id }.forEach { it.socket.sendPacket(PlayerInfoPacket("playerTransform", PlayerInfo(info.id, info.transform))) }
                            }
                        }
                        Events.CONNECT.toString() -> {
                            println(String.format("Client connected! (%s)", e.client!!.getRemoteAddress().toString()))
                            val player = ServerPlayer(e.client!!, PlayerInfo(UUID.randomUUID(), Transform.pos(0f, 0f, 0f)))

                            e.client?.sendPacket(PlayerInfoPacket("createPlayer", PlayerInfo.fromPlayer(player)))
                            players.add(player)
                        }
                        Events.DISCONNECT.toString() -> {
                            println(String.format("Client disconnected! (%s)", e.client?.getRemoteAddress().toString()))
                        }
                        Events.READY.toString() -> println("Server listening on port $port")
                    }
                }
        server.start()
    }
}

fun main(args: Array<String>) {
    try {
        if (args.isNotEmpty() && args[0] != "") {
            val port = args[0].toInt()
            val t = ServerGame(port, 0)
            t.start()
        } else {
            val t = ServerGame(3000, 0)
            t.start()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}