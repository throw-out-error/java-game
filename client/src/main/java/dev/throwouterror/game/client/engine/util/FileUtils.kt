package dev.throwouterror.game.client.engine.util


/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
object FileUtils {
    fun read(path: String): String {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream = classloader.getResourceAsStream(path)
        if (inputStream === null) {
            println("Error reading resource at $path")
            return ""
        }

        return String(inputStream.readBytes())
    }
}