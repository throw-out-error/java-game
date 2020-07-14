package dev.throwouterror.game.common.util

import java.io.InputStream


/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
object FileUtils {
    fun read(path: String, onSuccess: (stream: InputStream) -> Unit) {
        Thread.currentThread().contextClassLoader.getResourceAsStream(path)?.also {
            onSuccess(it)
        } ?: throw Exception("Error reading resource at $path")
    }

    fun readStr(path: String, onSuccess: (stream: String) -> Unit) {
        Thread.currentThread().contextClassLoader.getResourceAsStream(path)?.also {
            onSuccess(String(it.readBytes()))
        } ?: throw Exception("Error reading resource at $path")
    }
}
