package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.input.Window
import dev.throwouterror.game.client.engine.mesh.CubeMesh
import dev.throwouterror.game.client.model.Model
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.entity.Player
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.thread

/**
 * @author Theo Paris
 */
class ClientGame : Thread() {
    val window: Window = Window(800, 800, "OpenGL Game")
    private var socketThread: ClientSocket = ClientSocket(this)
    private val width = 800
    private val height = 800
    private var cube: Model? = null
    var player: ClientPlayer? = null
    val players: HashMap<UUID, Player> = HashMap()

    override fun run() {
        socketThread.start()

        window.create()
        cube = Model(Transform.pos(0f, 0f, -5.0f), CubeMesh())
        while (!window.shouldClose()) {
            window.update()
            player?.update()
            for (p in players.values) p.update()
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            thread {
                val boot = ClientGame()
                boot.start()
            }
        }
    }
}
