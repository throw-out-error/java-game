package dev.throwouterror.game.client.engine.input

import org.lwjgl.glfw.GLFW

object KeyboardInputHelper {
    private const val KEYBOARD_SIZE = GLFW.GLFW_KEY_LAST + 1
    private val lwjglToKeyboard: IntArray

    fun get(key: Int): Int {
        return lwjglToKeyboard[key]
    }

    private fun create() { // Map to none
        for (i in 0 until KEYBOARD_SIZE) {
            lwjglToKeyboard[i] = Keyboard.NONE.ordinal
        }
        lwjglToKeyboard[GLFW.GLFW_KEY_ESCAPE] = Keyboard.ESCAPE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F1] = Keyboard.F1.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F2] = Keyboard.F2.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F3] = Keyboard.F3.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F4] = Keyboard.F4.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F5] = Keyboard.F5.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F6] = Keyboard.F6.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F7] = Keyboard.F7.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F8] = Keyboard.F8.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F9] = Keyboard.F9.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F10] = Keyboard.F10.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F11] = Keyboard.F11.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F12] = Keyboard.F12.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_PRINT_SCREEN] = Keyboard.PRINT_SCREEN.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_SCROLL_LOCK] = Keyboard.SCROLL_LOCK.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_PAUSE] = Keyboard.PAUSE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_GRAVE_ACCENT] = Keyboard.GRAVE_ACCENT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_1] = Keyboard.NUM1.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_2] = Keyboard.NUM2.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_3] = Keyboard.NUM3.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_4] = Keyboard.NUM4.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_5] = Keyboard.NUM5.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_6] = Keyboard.NUM6.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_7] = Keyboard.NUM7.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_8] = Keyboard.NUM8.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_9] = Keyboard.NUM9.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_0] = Keyboard.NUM0.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_MINUS] = Keyboard.DASH.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_EQUAL] = Keyboard.EQUALS.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_BACKSPACE] = Keyboard.BACKSPACE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_A] = Keyboard.A.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_B] = Keyboard.B.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_C] = Keyboard.C.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_D] = Keyboard.D.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_E] = Keyboard.E.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_F] = Keyboard.F.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_G] = Keyboard.G.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_H] = Keyboard.H.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_I] = Keyboard.I.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_J] = Keyboard.J.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_K] = Keyboard.K.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_L] = Keyboard.L.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_M] = Keyboard.M.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_N] = Keyboard.N.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_O] = Keyboard.O.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_P] = Keyboard.P.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_Q] = Keyboard.Q.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_R] = Keyboard.R.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_S] = Keyboard.S.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_T] = Keyboard.T.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_U] = Keyboard.U.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_V] = Keyboard.V.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_W] = Keyboard.W.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_X] = Keyboard.X.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_Y] = Keyboard.Y.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_Z] = Keyboard.Z.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_TAB] = Keyboard.TAB.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT_BRACKET] = Keyboard.LEFT_BRACKET.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT_BRACKET] = Keyboard.RIGHT_BRACKET.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_BACKSLASH] = Keyboard.BACKSLASH.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_CAPS_LOCK] = Keyboard.CAPS_LOCK.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_SEMICOLON] = Keyboard.SEMICOLON.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_APOSTROPHE] = Keyboard.APOSTROPHE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_ENTER] = Keyboard.ENTER.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT_SHIFT] = Keyboard.RIGHT_SHIFT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_COMMA] = Keyboard.COMMA.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_PERIOD] = Keyboard.PERIOD.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_SLASH] = Keyboard.SLASH.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT_SHIFT] = Keyboard.LEFT_SHIFT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT_CONTROL] = Keyboard.LEFT_CONTROL.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT_SUPER] = Keyboard.LEFT_SUPER.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT_ALT] = Keyboard.LEFT_ALT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_SPACE] = Keyboard.SPACE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT_ALT] = Keyboard.RIGHT_ALT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT_SUPER] = Keyboard.RIGHT_SUPER.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT_CONTROL] = Keyboard.RIGHT_CONTROL.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_INSERT] = Keyboard.INSERT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_HOME] = Keyboard.HOME.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_PAGE_UP] = Keyboard.PAGE_UP.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_DELETE] = Keyboard.DELETE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_END] = Keyboard.END.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_PAGE_DOWN] = Keyboard.PAGE_DOWN.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_DOWN] = Keyboard.DOWN.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_LEFT] = Keyboard.LEFT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_RIGHT] = Keyboard.RIGHT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_UP] = Keyboard.UP.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_NUM_LOCK] = Keyboard.NUM_LOCK.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_DIVIDE] = Keyboard.KP_DIVIDE.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_MULTIPLY] = Keyboard.KP_MULTIPLY.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_SUBTRACT] = Keyboard.KP_SUBTRACT.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_ADD] = Keyboard.KP_ADD.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_0] = Keyboard.KP_0.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_1] = Keyboard.KP_1.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_2] = Keyboard.KP_2.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_3] = Keyboard.KP_3.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_4] = Keyboard.KP_4.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_5] = Keyboard.KP_5.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_6] = Keyboard.KP_6.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_7] = Keyboard.KP_7.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_8] = Keyboard.KP_8.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_9] = Keyboard.KP_9.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_DECIMAL] = Keyboard.KP_DECIMAL.ordinal
        lwjglToKeyboard[GLFW.GLFW_KEY_KP_ENTER] = Keyboard.KP_ENTER.ordinal
    }

    init {
        lwjglToKeyboard = IntArray(KEYBOARD_SIZE)
        create()
    }
}