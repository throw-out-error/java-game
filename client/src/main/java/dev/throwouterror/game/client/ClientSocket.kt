
package dev.throwouterror.game.client

import dev.throwouterror.eventbus.annotation.EventHandler
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.data.JsonUtils
import xyz.baddeveloper.lwsl.client.SocketClient
import xyz.baddeveloper.lwsl.client.events.ClientConnectEvent
import xyz.baddeveloper.lwsl.client.events.ClientDisconnectEvent
import xyz.baddeveloper.lwsl.client.events.ClientPacketReceivedEvent
import xyz.baddeveloper.lwsl.client.events.ClientPacketSentEvent
import java.net.InetAddress
import javax.swing.JFrame
import javax.swing.JOptionPane


/**
 * @author Theo Paris
 */
class ClientSocket(val game: ClientGame) : Thread() {
    val host = InetAddress.getLocalHost()
    var connection: SocketClient? = null

    override fun run() {
        // Connect to server
        connection = SocketClient("localhost", 3000)
                .addEventListener(this)
        connection!!.connect()

        // Make sure the client is connected, otherwise send an error to the user
        if (!this.connection!!.isConnected) {
            JOptionPane.showMessageDialog(JFrame(), "Socket Error", "Error connecting to server!",
                    JOptionPane.ERROR_MESSAGE)
            println("Socket did not connect successfully!")
            interrupt()
        }
    }

    @EventHandler
    fun onConnect(event: ClientConnectEvent) {
        println("Connected!")
    }

    @EventHandler
    fun onDisconnect(event: ClientDisconnectEvent) {
        println("Disconnected!")
    }

    @EventHandler
    fun onPacketReceived(event: ClientPacketReceivedEvent) {
        val data = event.packet.`object`
        println(String.format("Received info packet from %s", event.client.address))
        if (event.packet.isPacket(PlayerInfoPacket::class.java)) {
            if (data.getString("type") == "createPlayer") {
                val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
                println("Received player info with id: " + info.id.toString())
                game.player = ClientPlayer(info.id, info.transform, this)
                game.glWindow!!.addGLEventListener(game.player)
                game.frame.addKeyListener(game.player)
                game.players[info.id] = game.player!!
            }

            if (data.getString("type") == "playerTransform") {
                val info = JsonUtils.get().fromJson(data.getString("info"), PlayerInfo::class.java)
                game.players[info.id]?.transform = info.transform
            }
        }
    }

    @EventHandler
    fun onPacketSent(event: ClientPacketSentEvent) {
        println(String.format("Sent packet %s to %s.", event.packet.getObject().toString(), event.client.address))
    }
}