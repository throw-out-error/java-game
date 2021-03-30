package dev.throwouterror.game.client

import com.firenodes.lwsl.Events
import com.firenodes.lwsl.client.SocketClient
import com.firenodes.lwsl.client.events.ClientPacketEvent
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.data.JsonUtils

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
                    if (event.packet isOf PlayerInfoPacket::class.java) {
                        if (data.get("type").asString == "createPlayer") {
                            val info = JsonUtils.builder.fromJson(data.get("info"), PlayerInfo::class.java)
                            println("Received player info with id: " + info.id.toString())
                            game.player = ClientPlayer(info, this)
                            game.players[info.id] = game.player!!
                        }

                        if (data.get("type").asString == "playerTransform") {
                            val info = JsonUtils.builder.fromJson(data.get("info"), PlayerInfo::class.java)
                            game.players[info.id]?.transform = info.transform
                        }
                    }
                }
            }
        }
    }
}
