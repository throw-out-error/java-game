package dev.throwouterror.game.client.engine.input

import dev.throwouterror.util.math.Tensor
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWWindowSizeCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class Window(var width: Int,
             var height: Int, val title: String) {
    var window: Long = 0
        private set
    private var frames = 0
    private var input: Input? = null
    private val background = Tensor(0.0, 0.0, 0.0)
    private var sizeCallback: GLFWWindowSizeCallback? = null
    private var isResized = false
    private var isFullscreen = false
    private val windowPosX = IntArray(1)
    private val windowPosY = IntArray(1)
    fun create() {
        if (!GLFW.glfwInit()) {
            System.err.println("ERROR: GLFW wasn't initializied")
            return
        }
        input = Input()
        window = GLFW.glfwCreateWindow(width, height, title, if (isFullscreen) GLFW.glfwGetPrimaryMonitor() else 0, 0)
        if (window == 0L) {
            System.err.println("ERROR: Window wasn't created")
            return
        }
        val videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
        val mode = Tensor(videoMode.width().toDouble(), videoMode.height().toDouble())
        windowPosX[0] = ((mode.x - width) / 2).toInt()
        windowPosY[0] = ((mode.x - height) / 2).toInt()
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0])
        GLFW.glfwMakeContextCurrent(window)
        GL.createCapabilities(true)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        createCallbacks()
        GLFW.glfwShowWindow(window)
        GLFW.glfwSwapInterval(1)
        time = System.currentTimeMillis()
    }

    private fun createCallbacks() {
        sizeCallback = object : GLFWWindowSizeCallback() {
            override fun invoke(window: Long, w: Int, h: Int) {
                width = w
                height = h
                isResized = true
            }
        }
        GLFW.glfwSetKeyCallback(window, input!!.keyboardCallback)
        GLFW.glfwSetCursorPosCallback(window, input!!.mouseMoveCallback)
        GLFW.glfwSetMouseButtonCallback(window, input!!.mouseButtonsCallback)
        GLFW.glfwSetScrollCallback(window, input!!.mouseScrollCallback)
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback)
    }

    fun update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height)
            isResized = false
        }
        GL11.glClearColor(background.x.toFloat(), background.y.toFloat(), background.z.toFloat(), 1.0f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        GLFW.glfwPollEvents()
        frames++
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, "$title | FPS: $frames")
            time = System.currentTimeMillis()
            frames = 0
        }
    }

    fun swapBuffers() {
        GLFW.glfwSwapBuffers(window)
    }

    fun shouldClose(): Boolean {
        return GLFW.glfwWindowShouldClose(window)
    }

    fun destroy() {
        input!!.destroy()
        sizeCallback!!.free()
        GLFW.glfwWindowShouldClose(window)
        GLFW.glfwDestroyWindow(window)
        GLFW.glfwTerminate()
    }

    fun setBackgroundColor(r: Float, g: Float, b: Float) {
        background.setValues(r.toDouble(), g.toDouble(), b.toDouble())
    }

    fun setFullscreen(isFullscreen: Boolean) {
        this.isFullscreen = isFullscreen;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

    companion object {
        private var time: Long = 0
    }

}