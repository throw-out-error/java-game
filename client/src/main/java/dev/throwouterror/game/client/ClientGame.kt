/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client

import com.jogamp.newt.event.WindowAdapter
import com.jogamp.newt.event.WindowEvent
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.*
import com.jogamp.opengl.glu.GLU
import com.jogamp.opengl.util.FPSAnimator
import dev.throwouterror.game.common.Transform
import kotlin.system.exitProcess


/**
 * @author Creepinson
 */
class ClientGame : Thread() {
    private var socketThread: ClientSocket? = null
    var game: Thread? = null
    val width = 800
    val height = 800
    private val cube: Cube? = null
    private val glu = GLU()

    private fun init() {
        val glp = GLProfile.get("GL2")
        val caps = GLCapabilities(glp)

        val glWindow = GLWindow.create(caps)
        glWindow.title = "OpenGL Game"
        glWindow.setSize(width, height)
        glWindow.isVisible = true

        glWindow.addWindowListener(object : WindowAdapter() {
            override fun windowDestroyNotify(e: WindowEvent?) {
                exitProcess(0)
            }
        })
        glWindow.addGLEventListener(object : GLEventListener {
            override fun init(drawable: GLAutoDrawable) {
                val gl = drawable.gl.gL2
                gl.glShadeModel(GL2.GL_SMOOTH)
                gl.glClearColor(0f, 0f, 0f, 0f)
                gl.glClearDepth(1.0)
                gl.glEnable(GL2.GL_DEPTH_TEST)
                gl.glDepthFunc(GL2.GL_LEQUAL)
                gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST)
            }

            override fun dispose(drawable: GLAutoDrawable) {

            }

            override fun display(drawable: GLAutoDrawable) {
                val gl = drawable.gl.gL2

                gl.glClear(GL2.GL_COLOR_BUFFER_BIT or GL2.GL_DEPTH_BUFFER_BIT)
                gl.glLoadIdentity()
            }

            override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {
                val gl = drawable.gl.gL2

                val h = width.toFloat() / height.toFloat()
                gl.glViewport(0, 0, width, height)
                gl.glMatrixMode(GL2.GL_PROJECTION)
                gl.glLoadIdentity()

                glu.gluPerspective(45.0, h.toDouble(), 1.0, 20.0)
                gl.glMatrixMode(GL2.GL_MODELVIEW)
                gl.glLoadIdentity()
            }
        })

        val animator = FPSAnimator(glWindow, 60)
        animator.start()
        val cube = Cube(Transform.pos(0f, 0f, -5.0f))
        glWindow.addGLEventListener(cube)
    }

    override fun run() {
        socketThread = ClientSocket(this)
        socketThread?.start()
        init()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            ClientGame().start()
        }
    }
}
