package dev.throwouterror.game.common.util

import java.io.InputStream

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
abstract class Resource {
    protected fun getResource(file: String, onSuccess: (stream: InputStream) -> Unit) =
            FileUtils.read(file, onSuccess)
}