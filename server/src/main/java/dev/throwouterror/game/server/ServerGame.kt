/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.server

import dev.throwouterror.game.common.Player
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.networking.Packet
import dev.throwouterror.game.common.networking.PlayerInfo
import dev.throwouterror.game.common.networking.TextPacket
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class ServerGame(port: Int) : Thread() {
    private val players: ArrayList<ConnectionToClient> = arrayListOf()
    private val messages: LinkedBlockingQueue<Packet> = LinkedBlockingQueue()
    private val serverSocket: ServerSocket = ServerSocket(port)
    override fun run() {
        while (true) {
            try {
                println("Waiting for client on port " +
                        serverSocket.localPort + "...")
                val client = serverSocket.accept()
                println("Just connected to " + client.remoteSocketAddress)
                val info = PlayerInfo(UUID.randomUUID(), Transform())
                players.add(ConnectionToClient(client, messages, info.id, info.transform))
                sendToOne(info.id, TextPacket("player-info", info.toJson()))

                val packet = messages.poll()
                if (packet != null) {
                    if (packet.name == "updateTransform") {
                        val msg = packet as TextPacket
                        sendToAllExcept(info.id, TextPacket("playerTransform", PlayerInfo(info.id, Transform.fromJson(msg.getPacketData())).toJson()))
                    }
                }
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

    @Throws(IndexOutOfBoundsException::class)
    fun sendToOne(index: Int, message: Packet) {
        players[index].write(message)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun sendToOne(id: UUID, message: Packet) {
        players.filter { p -> p.id == id }[0].write(message)
    }

    fun sendToAll(message: Packet) {
        for (client in players) client.write(message)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun sendToAllExcept(id: UUID, message: Packet) {
        for (client in players.filter { p -> p.id != id }) client.write(message)
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

    private class ConnectionToClient internal constructor(var socket: Socket, messages: LinkedBlockingQueue<Packet>, id: UUID, transform: Transform) : Player(id, transform) {
        var `in`: ObjectInputStream = ObjectInputStream(socket.getInputStream())
        var out: ObjectOutputStream = ObjectOutputStream(socket.getOutputStream())

        fun write(obj: Packet) {
            try {
                obj.toStream(out)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        init {
            val read: Thread = object : Thread() {
                override fun run() {
                    while (true) {
                        try {
                            val obj = Packet.fromStream(`in`)
                            messages.put(obj)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
            read.isDaemon = true // terminate when main ends
            read.start()
        }
    }

    init {
        serverSocket.soTimeout = 10000
    }

}