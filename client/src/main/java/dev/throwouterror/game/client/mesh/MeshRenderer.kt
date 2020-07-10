/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client.mesh

import com.jogamp.opengl.GL2
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import dev.throwouterror.game.common.Transform

/**
 * @author Creepinson
 */
class MeshRenderer(var transform: Transform, private var mesh: Mesh) : GLEventListener {
    override fun display(drawable: GLAutoDrawable) {
        val gl = drawable.gl.gL2
        gl.glTranslatef(transform.position.x, transform.position.y, transform.position.z)
        gl.glScalef(transform.scale.x, transform.scale.y, transform.scale.z)

        // Rotate The Cube On X, Y & Z
        gl.glRotatef(transform.rotation.x, 1.0f, 0.0f, 0.0f)
        gl.glRotatef(transform.rotation.y, 0.0f, .0f, 0.0f)
        gl.glRotatef(transform.rotation.z, 0.0f, 0.0f, 1.0f)

        //giving different colors to different sides
        gl.glBegin(GL2.GL_QUADS) // Start Drawing The Cube
        for (v in mesh.vertices)
            gl.glVertex3f(v.x, v.y, v.z)


        gl.glEnd() // Done Drawing The Quad
        gl.glFlush()

        transform.rotation.sub(0.15f, 0f, 0f)
    }

    override fun init(drawable: GLAutoDrawable) {

    }

    override fun dispose(drawable: GLAutoDrawable) {

    }


    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {}

}
