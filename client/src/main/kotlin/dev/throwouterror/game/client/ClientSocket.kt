package dev.throwouterror.game.client

import dev.throwouterror.game.common.ErrorDialog
import dev.throwouterror.game.common.ErrorType
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.data.JsonUtils
import xyz.baddeveloper.lwsl.Events
import xyz.baddeveloper.lwsl.client.SocketClient
import xyz.baddeveloper.lwsl.client.events.ClientPacketEvent
import java.net.ConnectException

/**
 * @author Theo Paris
 */
class ClientSocket(val game: ClientGame) : Thread() {
    var connection: SocketClient = SocketClient("localhost", 3000)


    override fun run() {
        // Connect to server
        connection.connect()
        connection.createEventBus().observeEvents().subscribe { e ->
            when (e.type) {
                Events.CONNECT.toString() -> println("Connected!")
                Events.DISCONNECT.toString() -> println("Disconnected!")
                Events.PACKET_RECEIVED.toString() -> {
                    val event = e as ClientPacketEvent
                    val data = event.packet.jsonObject
                    println(String.format("Received info packet from %s", event.client.address))
                    if (event.packet.isPacket(PlayerInfoPacket::class.java)) {
                        if (data.getString("type") == "createPlayer") {
                            val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
                            println("Received player info with id: " + info.id.toString())
                            game.player = ClientPlayer(info, this)
                            game.players[info.id] = game.player!!
                        }

                        if (data.getString("type") == "playerTransform") {
                            val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
                            game.players[info.id]?.transform = info.transform
                        }
                    }
                }
            }
        }
    }
}