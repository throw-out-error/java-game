package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.Camera
import dev.throwouterror.game.client.engine.input.Input
import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.math.Direction
import org.lwjgl.glfw.GLFW

/**
 * @author Theo Paris
 */
class ClientPlayer(info: PlayerInfo, private var socket: ClientSocket) : Player(info) {

    private val movementSpeed = 0.02
    var camera: Camera = Camera(transform.clone().move(Direction.UP))

    private fun sendTransform() {
        socket.connection.sendPacket(PlayerInfoPacket("updateTransform", PlayerInfo(id, transform)))
    }

    override fun onUpdate() {
        //        camera.rotate(Input.mouse)
        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) move(0.0, 0.0, this.movementSpeed)
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) move(0.0, 0.0, -this.movementSpeed)
        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) move(-this.movementSpeed, 0.0, 0.0)
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) move(this.movementSpeed, 0.0, 0.0)
    }

    fun move(x: Double, y: Double, z: Double) {
        this.camera.moveForward(transform, x, y, z)
//        sendTransform()
    }
}
