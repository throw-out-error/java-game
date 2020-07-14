package dev.throwouterror.game.client.engine.renderer

import dev.throwouterror.game.common.util.Resource
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL32
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

enum class ShaderType {
    GEOMETRY,
    VERTEX,
    FRAGMENT
}

class Shader(file: String, type: ShaderType) : Resource() {
    var id = 0
        private set

    private fun decode(stream: InputStream, onSuccess: (source: String) -> Unit) {
        var source = String()
        BufferedReader(InputStreamReader(stream)).also { file ->
            while (true) {
                file.readLine()?.also { line ->
                    source += "$line\n"
                } ?: break
            }
            file.close()
        }
        stream.close()
        onSuccess(source)
    }

    init {
        id = when (type) {
            ShaderType.GEOMETRY -> GL32.glCreateShader(GL32.GL_GEOMETRY_SHADER)
            ShaderType.VERTEX -> GL32.glCreateShader(GL32.GL_VERTEX_SHADER)
            ShaderType.FRAGMENT -> GL32.glCreateShader(GL32.GL_FRAGMENT_SHADER)
        }
        getResource(file) { stream ->
            decode(stream) { source ->
                compile(id, source)
            }
        }
    }

    private fun compile(id: Int, source: String) {
        GL32.glShaderSource(id, source)
        GL32.glCompileShader(id)
    }

    fun bind() {
        GL20.glUseProgram(id)
    }

    fun unbind() {
        GL20.glUseProgram(0)
    }

    fun destroy() {
        GL20.glDeleteProgram(id)
    }

    fun getUniformLocation(s: String): Int {
        return GL20.glGetUniformLocation(id, s)
    }
}