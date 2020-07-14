package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.Model
import dev.throwouterror.game.client.engine.input.Window
import dev.throwouterror.game.client.engine.mesh.advanced.TerrainMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.util.math.Tensor
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.thread

/**
 * @author Theo Paris
 */
class ClientGame : Thread() {
    private val window: Window = Window(800, 800, "OpenGL Game")
    private var socketThread: ClientSocket = ClientSocket(this)
    private var cube: Model? = null
    var player: ClientPlayer? = null
    val players: HashMap<UUID, Player> = HashMap()

    override fun run() {
        socketThread.start()
        window.setBackgroundColor(1f, 1f, 1f)
        window.create()
        // Initialize game objects
        cube = Model(Transform.pos(0f, 0f, -5.0f), TerrainMesh(10, 2f, 2f, 2f), Tensor(1f, 0f, 0f, 1f))

        while (!window.shouldClose()) {
            window.update()
            // Game logic
            cube?.transform?.scale?.add(0.01)
            cube?.render()
            player?.update()
            players.values.parallelStream().forEach { it.update() }
            window.swapBuffers()
        }
        window.destroy()
    }
}

fun main(args: Array<String>) {
    thread {
        val boot = ClientGame()
        boot.start()
    }
}