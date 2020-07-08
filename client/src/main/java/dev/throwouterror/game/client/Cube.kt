/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client

import com.jogamp.opengl.GL2
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.glu.GLU
import dev.throwouterror.game.common.Transform

import java.awt.*

/**
 * @author Creepinson
 */
class Cube(private val transform: Transform) : GLEventListener {
    private val glu = GLU()

    override fun display(drawable: GLAutoDrawable) {
        val gl = drawable.gl.gL2
        gl.glTranslatef(transform.position.x, transform.position.y, transform.position.z)

        // Rotate The Cube On X, Y & Z
        gl.glRotatef(transform.rotation.x, 1.0f, 0.0f, 0.0f)
        gl.glRotatef(transform.rotation.y, 0.0f, .0f, 0.0f)
        gl.glRotatef(transform.rotation.z, 0.0f, 0.0f, 1.0f)

        //giving different colors to different sides
        gl.glBegin(GL2.GL_QUADS) // Start Drawing The Cube
        gl.glColor3f(1f, 0f, 0f) //red color
        gl.glVertex3f(1.0f, 1.0f, -1.0f) // Top Right Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f) // Top Left Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f) // Bottom Left Of The Quad (Top)
        gl.glVertex3f(1.0f, 1.0f, 1.0f) // Bottom Right Of The Quad (Top)

        gl.glColor3f(0f, 1f, 0f) //green color
        gl.glVertex3f(1.0f, -1.0f, 1.0f) // Top Right Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, 1.0f) // Top Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, -1.0f) // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, -1.0f) // Bottom Right Of The Quad

        gl.glColor3f(0f, 0f, 1f) //blue color
        gl.glVertex3f(1.0f, 1.0f, 1.0f) // Top Right Of The Quad (Front)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f) // Top Left Of The Quad (Front)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f) // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, 1.0f) // Bottom Right Of The Quad

        gl.glColor3f(1f, 1f, 0f) //yellow (red + green)
        gl.glVertex3f(1.0f, -1.0f, -1.0f) // Bottom Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, -1.0f) // Bottom Right Of The Quad
        gl.glVertex3f(-1.0f, 1.0f, -1.0f) // Top Right Of The Quad (Back)
        gl.glVertex3f(1.0f, 1.0f, -1.0f) // Top Left Of The Quad (Back)

        gl.glColor3f(1f, 0f, 1f) //purple (red + green)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f) // Top Right Of The Quad (Left)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f) // Top Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f) // Bottom Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, 1.0f) // Bottom Right Of The Quad

        gl.glColor3f(0f, 1f, 1f) //sky blue (blue +green)
        gl.glVertex3f(1.0f, 1.0f, -1.0f) // Top Right Of The Quad (Right)
        gl.glVertex3f(1.0f, 1.0f, 1.0f) // Top Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, 1.0f) // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, -1.0f) // Bottom Right Of The Quad
        gl.glEnd() // Done Drawing The Quad
        gl.glFlush()

        transform.rotation.sub(0.15f, 0f, 0f)
    }

    override fun init(drawable: GLAutoDrawable) {

    }

    override fun dispose(drawable: GLAutoDrawable) {

    }


    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {}

    companion object {

        var dm: DisplayMode? = null
        var dm_old: DisplayMode? = null
    }
}
