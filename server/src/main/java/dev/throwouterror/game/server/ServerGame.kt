/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.server

import dev.throwouterror.game.common.networking.TextPacket
import java.io.*
import java.net.ServerSocket
import java.net.SocketException
import java.net.SocketTimeoutException

class ServerGame(port: Int) : Thread() {
    private val serverSocket: ServerSocket = ServerSocket(port)
    override fun run() {
        while (true) {
            try {
                println("Waiting for client on port " +
                        serverSocket.localPort + "...")
                val server = serverSocket.accept()
                println("Just connected to " + server.remoteSocketAddress)
                val inputStream = DataInputStream(server.getInputStream())
                val `in` = ObjectInputStream(inputStream)
                println(`in`.readUTF())
                val outputStream = DataOutputStream(server.getOutputStream())
                val out = ObjectOutputStream(outputStream)
                TextPacket("news", "Hello, World!").toStream(out)
            } catch (e: SocketException) {
                println("Client has closed the connection.")
            } catch (e: SocketTimeoutException) {
                // do nothing
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                if (args.isNotEmpty() && args[0] != "") {
                    val port = args[0].toInt()
                    val t: Thread = ServerGame(port)
                    t.start()
                } else {
                    val t: Thread = ServerGame(3000)
                    t.start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    init {
        serverSocket.soTimeout = 10000
    }
}