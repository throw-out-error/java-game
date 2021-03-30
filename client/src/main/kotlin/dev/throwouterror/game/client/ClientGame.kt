package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.Model
import dev.throwouterror.game.client.engine.input.Input
import dev.throwouterror.game.client.engine.input.Window
import dev.throwouterror.game.client.engine.material.Material
import dev.throwouterror.game.client.engine.mesh.QuadMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.util.math.Tensor
import org.lwjgl.glfw.GLFW
import java.util.UUID
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
//        socketThread.start()
        window.setBackgroundColor(1f, 1f, 1f)
        window.create()
        // Initialize game objects
        cube = Model(Transform.pos(0f, 0f, -5.0f), QuadMesh(), Material(Tensor(1f, 0f, 0f)))

        while (!window.shouldClose()) {
            window.update()
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen())

            // Game logic
            player?.update()
            players.values.parallelStream().forEach { it.update() }
            cube?.let {
                it.transform.position.z -= 0.01
                it.render()
            }
            window.swapBuffers()
        }
        // Clean up game before exiting
        cube?.destroy()
        window.destroy()
    }
}

fun main(args: Array<String>) {
    thread {
        val boot = ClientGame()
        boot.start()
    }
}
