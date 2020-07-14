
package dev.throwouterror.game.common

import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.system.exitProcess

/**
 * @author Theo Paris
 */

class ErrorDialog {
    companion object {
        fun create(type: ErrorType, error: Any) {
            val f = JFrame()
            JOptionPane.showMessageDialog(f, error.toString(),
                    type.message, JOptionPane.ERROR_MESSAGE)
            exitProcess(0)
        }

    }
}

enum class ErrorType(val message: String) {
    SOCKET("Connection Error")
}