package dev.throwouterror.game.client.engine.mesh

import dev.throwouterror.util.math.Tensor
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

open class Mesh(val vertices: ArrayList<Tensor>, val indices: ArrayList<Int>) {
    var vao = 0
        private set
    var pbo = 0
        private set
    var ibo = 0
        private set

    constructor() :
            this(arrayListOf<Tensor>(), arrayListOf())

    /**
     * Appends another mesh to the current mesh.
     * @return The current mesh
     */
    fun append(m: Mesh): Mesh {
        this.vertices.addAll(m.vertices)
        this.indices.addAll(m.indices)
        return this
    }

    fun create() {
        vao = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vao)
        val positionBuffer = BufferUtils.createFloatBuffer(vertices.size * 3)
        val positionData = FloatArray(vertices.size * 3)
        for (i in vertices.indices) {
            positionData[i * 3] = vertices[i].x.toFloat()
            positionData[i * 3 + 1] = vertices[i].y.toFloat()
            positionData[i * 3 + 2] = vertices[i].z.toFloat()
        }
        positionBuffer.put(positionData).flip()
        pbo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
        val indicesBuffer = BufferUtils.createIntBuffer(indices.size)
        indicesBuffer.put(indices.toIntArray()).flip()
        ibo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    companion object {
        /**
         * Combines several meshes into a single NEW mesh and returns it.
         */
        fun combine(vararg meshes: Mesh): Mesh {
            val m = Mesh()
            meshes.forEach { m.append(it) }
            return m
        }
    }

}