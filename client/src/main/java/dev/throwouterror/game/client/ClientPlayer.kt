/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.client

import com.jogamp.newt.event.KeyEvent
import com.jogamp.newt.event.KeyListener
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import dev.throwouterror.game.common.Player
import dev.throwouterror.game.common.Transform
import java.util.*

/**
 * @author Theo Paris
 */
class ClientPlayer(id: UUID, transform: Transform, private var socket: ClientSocket) : Player(id, transform), GLEventListener, KeyListener {

    private val movementSpeed = 0.02f
    private var camera: Camera = Camera(transform, 800, 800)

    private fun sendTransform() {
        socket.send(transform.toPacket("updateTransform"))
    }

    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {
        camera.reshape(drawable, x, y, width, height)
    }

    override fun display(drawable: GLAutoDrawable) {
        camera.display(drawable)
        sendTransform()
    }

    override fun init(drawable: GLAutoDrawable) {
        camera.init(drawable)
    }

    override fun dispose(drawable: GLAutoDrawable) {

    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyChar.toLowerCase()) {
            'w' -> transform.position.add(0f, 0f, this.movementSpeed)
            's' -> transform.position.add(0f, 0f, -this.movementSpeed)
            'a' -> transform.position.add(-this.movementSpeed, 0f, 0f)
            'd' -> transform.position.add(this.movementSpeed, 0f, 0f)
        }
    }

    override fun keyReleased(e: KeyEvent) {

    }
}