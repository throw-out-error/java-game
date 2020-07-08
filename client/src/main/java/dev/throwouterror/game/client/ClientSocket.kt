/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.client

import dev.throwouterror.game.common.networking.Packet
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket
import java.net.SocketException

/**
 * @author Theo Paris
 */
class ClientSocket(val game: ClientGame) : Thread() {
    override fun run() {
        // Connect to server
        val host = InetAddress.getLocalHost()
        var socket: Socket? = null
        var outputStream: ObjectOutputStream? = null
        var inputStream: ObjectInputStream? = null

        try {
            socket = Socket(host.hostName, 3000)
            outputStream = ObjectOutputStream(socket.getOutputStream());
            inputStream = ObjectInputStream(socket.getInputStream());
        } catch (e: SocketException) {
            println("The server cannot be reached!")
        }

        if (socket != null && socket.isConnected) println("Socket connected.")
        else {
            println("Socket did not connect successfully!")
            interrupt()
        }

        // Receive packets from the server
        while (true) {
            try {
                val msg = Packet.fromStream(inputStream);
                println("Received packet: " + msg.name);
                if (msg.name == "news") {
                    println("Received news: " + msg.data)
                }

            } catch (e: SocketException) {
                println("Socket error encountered, cleaning up...")
                inputStream?.close()
                outputStream?.close()
                socket!!.close()
                break
            }
        }
    }
}