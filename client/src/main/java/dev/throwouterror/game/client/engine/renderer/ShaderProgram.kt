package dev.throwouterror.game.client.engine.renderer

import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.GL_FALSE
import org.lwjgl.opengl.GL20.*
import java.nio.FloatBuffer


/**
 * Creates a new shader from vertex and fragment source, and with the given
 * map of <Integer></Integer>, String> attrib locations
 * @param vertexShader the vertex shader source string
 * @param fragmentShader the fragment shader source string
 * @param attributes a map of attrib locations for GLSL 120
 * @throws LWJGLException if the program could not be compiled and linked
 */
class ShaderProgram(vertexShader: String, fragmentShader: String, attributes: Map<Int, String?>) {
    val program: Int
    val vertex: Int
    val fragment: Int
    private var log: String? = null

    /** Compile the shader source as the given type and return the shader object ID.  */
    @Throws(ShaderException::class)
    private fun compileShader(source: String?, type: Int): Int {
        // create a shader object
        val shader = glCreateShader(type)
        // pass the source string
        glShaderSource(shader, source)
        // compile the source
        glCompileShader(shader)
        // if info/warnings are found, append it to our shader log
        val infoLog = glGetShaderInfoLog(shader,
                glGetShaderi(shader, GL_INFO_LOG_LENGTH))
        if (infoLog != null && infoLog.trim { it <= ' ' }.isNotEmpty()) log += getName(type) + ": " + infoLog + "\n"
        // if the compiling was unsuccessful, throw an exception
        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) throw ShaderException("Failure in compiling " + getName(type)
                + ". Error log:\n" + infoLog)
        return shader
    }

    private fun getName(shaderType: Int): String {
        if (shaderType == GL_VERTEX_SHADER) return "GL_VERTEX_SHADER"
        return if (shaderType == GL_FRAGMENT_SHADER) "GL_FRAGMENT_SHADER" else "shader"
    }

    /**
     * Make this shader the active program.
     */
    fun use() {
        glUseProgram(program)
    }

    /**
     * Destroy this shader program.
     */
    fun destroy() {
        glDeleteProgram(program)
    }

    /**
     * Gets the location of the specified uniform name.
     * @param str the name of the uniform
     * @return the location of the uniform in this program
     */
    fun getUniformLocation(str: String?): Int {
        return glGetUniformLocation(program, str)
    }
    /* ------ UNIFORM SETTERS/GETTERS ------ */
    /**
     * Sets the uniform data at the specified location (the uniform type may be int, bool or sampler2D).
     * @param loc the location of the int/bool/sampler2D uniform
     * @param i the value to set
     */
    fun setUniformi(loc: Int, i: Int) {
        if (loc == -1) return
        glUniform1i(loc, i)
    }


    /**
     * Sends a 4x4 matrix to the shader program.
     * @param loc the location of the mat4 uniform
     * @param transposed whether the matrix should be transposed
     * @param mat the matrix to send
     */
    fun setUniformMatrix(loc: Int, transposed: Boolean, mat: Matrix4f) {
        if (loc == -1) return
        if (buf16Pool == null) buf16Pool = BufferUtils.createFloatBuffer(16)
        buf16Pool!!.clear()
        mat.get(buf16Pool)
        buf16Pool!!.flip()
        glUniformMatrix4fv(loc, transposed, buf16Pool)
    }

    companion object {
        private var buf16Pool: FloatBuffer? = null
        /**
         * Makes the "default shader" (0) the active program. In GL 3.1+ core profile,
         * you may run into glErrors if you try rendering with the default shader.
         */
        fun unbind() {
            glUseProgram(0)
        }
    }

    init { //compile the String source
        vertex = compileShader(vertexShader, GL_VERTEX_SHADER)
        fragment = compileShader(fragmentShader, GL_FRAGMENT_SHADER)
        //create the program
        program = glCreateProgram()
        //attach the shaders
        glAttachShader(program, vertex)
        glAttachShader(program, fragment)
        //bind the attrib locations for GLSL 120
        for ((key, value) in attributes) glBindAttribLocation(program, key, value)
        //link our program
        glLinkProgram(program)
        //grab our info log
        val infoLog = glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH))
        //if some log exists, append it
        if (infoLog != null && infoLog.trim { it <= ' ' }.isNotEmpty()) log += infoLog
        //if the link failed, throw some sort of exception
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) throw ShaderException(
                "Failure in linking program. Error log:\n$infoLog")
        //detach and delete the shaders which are no longer needed
        glDetachShader(program, vertex)
        glDetachShader(program, fragment)
        glDeleteShader(vertex)
        glDeleteShader(fragment)
    }
}

class ShaderException(override val message: String) : RuntimeException(message) {
}