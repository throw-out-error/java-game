/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.client

import dev.throwouterror.game.common.ErrorDialog
import dev.throwouterror.game.common.ErrorType
import dev.throwouterror.game.common.networking.Packet
import dev.throwouterror.game.common.networking.PlayerInfo
import dev.throwouterror.game.common.networking.TextPacket
import dev.throwouterror.util.data.JsonUtils
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket
import java.net.SocketException
import javax.swing.JFrame
import javax.swing.JOptionPane


/**
 * @author Theo Paris
 */
class ClientSocket(val game: ClientGame) : Thread() {
    val host = InetAddress.getLocalHost()
    var connection: Socket? = null
    var outputStream: ObjectOutputStream? = null
    var inputStream: ObjectInputStream? = null

    fun send(packet: Packet) {
        packet.toStream(outputStream!!)
    }

    override fun run() {
        // Connect to server
        try {
            connection = Socket(host.hostName, 3000)
            outputStream = ObjectOutputStream(connection!!.getOutputStream());
            inputStream = ObjectInputStream(connection!!.getInputStream());

            if (connection!!.isConnected) println("Socket connected.")
            else {
                JOptionPane.showMessageDialog(JFrame(), "Socket Error", "Error connecting to server!",
                        JOptionPane.ERROR_MESSAGE)
                println("Socket did not connect successfully!")
                interrupt()
            }

            // Receive packets from the server
            while (true) {
                try {
                    val packet = Packet.fromStream(inputStream!!);
                    println("Received packet: " + packet.name);
                    if (packet.name == "player-info") {
                        val msg = packet as TextPacket
                        println("Received player info: " + msg.getPacketData())
                        val data = JsonUtils.get().fromJson(msg.getPacketData(), PlayerInfo::class.java)
                        game.player = ClientPlayer(data.id, data.transform, this)
                        game.glWindow!!.addGLEventListener(game.player)
                        game.glWindow!!.addKeyListener(game.player)
                        game.players[data.id] = game.player!!
                    }

                    if (packet.name == "playerTransform") {
                        val msg = packet as TextPacket
                        val data = JsonUtils.get().fromJson(msg.getPacketData(), PlayerInfo::class.java)

                        game.players[data.id]?.transform = data.transform
                    }

                } catch (e: SocketException) {
                    ErrorDialog.create(ErrorType.SOCKET, "A socket error occurred, the connection may have been closed.")
                    println("Socket error encountered, cleaning up...")
                    inputStream!!.close()
                    outputStream!!.close()
                    connection!!.close()
                    break
                }
            }
        } catch (e: SocketException) {
            ErrorDialog.create(ErrorType.SOCKET, "Error connecting to server - the server cannot be reached on the host.")
            println("The server cannot be reached!")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}