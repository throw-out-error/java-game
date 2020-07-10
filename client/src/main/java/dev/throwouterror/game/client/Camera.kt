/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client

import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW
import com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION
import dev.throwouterror.game.common.Transform

/**
 * @author Theo Paris
 */
class Camera(var transform: Transform, width: Int?, height: Int?) : GLEventListener {
    private var width: Int = 800
    private var height: Int = 800

    init {
        if (width != null) {
            this.width = width
        }
        if (height != null) {
            this.height = height
        }
    }

    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {

    }

    override fun display(drawable: GLAutoDrawable) {
        val gl = drawable.gl.gL2

        gl.glLoadIdentity();
        gl.glRotatef(transform.rotation.x, 1.0F, 0.0F, 0.0F);
        gl.glRotatef(transform.rotation.y, 0.0F, 1.0F, 0.0F);
        gl.glRotatef(transform.rotation.z, 0.0F, 0.0F, 1.0F);
        gl.glTranslatef(transform.position.x, transform.position.y, transform.position.z);
    }

    override fun init(drawable: GLAutoDrawable) {
        val gl = drawable.gl.gL2
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustum(0.0, width.toDouble(), height.toDouble(), 0.0, 0.1, 1000.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    override fun dispose(drawable: GLAutoDrawable) {
    }
}