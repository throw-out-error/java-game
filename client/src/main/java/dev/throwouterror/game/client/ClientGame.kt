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
import dev.throwouterror.game.client.mesh.CubeMesh
import dev.throwouterror.game.client.mesh.MeshRenderer
import dev.throwouterror.game.common.Player
import dev.throwouterror.game.common.Transform
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.exitProcess


/**
 * @author Creepinson
 */
class ClientGame : Thread() {
    var glWindow: GLWindow? = null
    private var socketThread: ClientSocket = ClientSocket(this)
    private val width = 800
    private val height = 800
    private var cube: MeshRenderer? = null
    private val glu = GLU()
    var player: ClientPlayer? = null
    val players: HashMap<UUID, Player> = HashMap()

    private fun init() {
        val glp = GLProfile.get("GL2")
        val caps = GLCapabilities(glp)

        glWindow = GLWindow.create(caps)
        glWindow!!.title = "OpenGL Game"
        glWindow!!.setSize(width, height)
        glWindow!!.isVisible = true

        glWindow!!.addWindowListener(object : WindowAdapter() {
            override fun windowDestroyNotify(e: WindowEvent?) {
                exitProcess(0)
            }
        })
        glWindow!!.addGLEventListener(object : GLEventListener {
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

        cube = MeshRenderer(Transform.pos(0f, 0f, -5.0f), CubeMesh())
        glWindow!!.addGLEventListener(cube)
    }

    override fun run() {
        socketThread.start()
        if (socketThread.isAlive)
            init()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            ClientGame().start()
        }
    }
}
