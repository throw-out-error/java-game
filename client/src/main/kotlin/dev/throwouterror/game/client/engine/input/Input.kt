package dev.throwouterror.game.client.engine.input

import dev.throwouterror.util.math.Tensor
import org.lwjgl.glfw.*

class Input {
    val keyboardCallback: GLFWKeyCallback
    val mouseMoveCallback: GLFWCursorPosCallback
    val mouseButtonsCallback: GLFWMouseButtonCallback
    val mouseScrollCallback: GLFWScrollCallback

    fun destroy() {
        keyboardCallback.free()
        mouseMoveCallback.free()
        mouseButtonsCallback.free()
        mouseScrollCallback.free()
    }

    companion object {
        private val keys = BooleanArray(GLFW.GLFW_KEY_LAST)
        private val buttons = BooleanArray(GLFW.GLFW_MOUSE_BUTTON_LAST)
        private val mouse: Tensor = Tensor(0.0, 0.0)
        val scroll: Tensor = Tensor(0.0, 0.0)

        fun getMouse2(): Tensor = mouse.clone()

        fun getMouse3(): Tensor = Tensor(mouse.x, mouse.y, 0.0)

        fun isKeyDown(key: Int): Boolean {
            return keys[key]
        }

        fun isButtonDown(button: Int): Boolean {
            return buttons[button]
        }

    }

    init {
        keyboardCallback = object : GLFWKeyCallback() {
            override fun invoke(window: kotlin.Long, key: kotlin.Int, scancode: kotlin.Int, action: kotlin.Int, mods: kotlin.Int) {
                keys[key] = action != GLFW.GLFW_RELEASE
            }
        }
        mouseMoveCallback = object : GLFWCursorPosCallback() {
            override fun invoke(window: kotlin.Long, xpos: kotlin.Double, ypos: kotlin.Double) {
                mouse.x = xpos
                mouse.y = ypos
            }
        }
        mouseButtonsCallback = object : GLFWMouseButtonCallback() {
            override fun invoke(window: kotlin.Long, button: kotlin.Int, action: kotlin.Int, mods: kotlin.Int) {
                buttons[button] = action != GLFW.GLFW_RELEASE
            }
        }
        mouseScrollCallback = object : GLFWScrollCallback() {
            override fun invoke(window: kotlin.Long, offsetx: kotlin.Double, offsety: kotlin.Double) {
                scroll.x += offsetx
                scroll.y += offsety
            }
        }
    }
}