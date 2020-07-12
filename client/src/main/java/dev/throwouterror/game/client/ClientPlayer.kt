package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.Camera
import dev.throwouterror.game.client.engine.input.Input
import dev.throwouterror.game.client.engine.input.Keyboard
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.game.common.network.PlayerInfo
import dev.throwouterror.game.common.network.packet.PlayerInfoPacket
import dev.throwouterror.util.math.Direction
import java.util.*


/**
 * @author Theo Paris
 */
class ClientPlayer(id: UUID, transform: Transform, private var socket: ClientSocket) : Player(id, transform) {

    private val movementSpeed = 0.02
    var camera: Camera = Camera(transform.clone().move(Direction.UP))

    private fun sendTransform() {
        socket.connection?.sendPacket(PlayerInfoPacket("updateTransform", PlayerInfo(id, transform)))
    }

    override fun onUpdate() {
        if (Input.isKeyDown(Keyboard.W)) move(0.0, 0.0, this.movementSpeed)
        if (Input.isKeyDown(Keyboard.S)) move(0.0, 0.0, -this.movementSpeed)
        if (Input.isKeyDown(Keyboard.A)) move(-this.movementSpeed, 0.0, 0.0)
        if (Input.isKeyDown(Keyboard.D)) move(this.movementSpeed, 0.0, 0.0)
        camera.rotate(Input.mouse)
        sendTransform()
    }

    fun move(x: Double, y: Double, z: Double) {
        this.camera.moveForward(transform, x, y, z)
    }
}