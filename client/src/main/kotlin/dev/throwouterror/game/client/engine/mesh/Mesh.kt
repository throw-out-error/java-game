package dev.throwouterror.game.client.engine.mesh

import dev.throwouterror.game.common.util.Resource
import dev.throwouterror.game.common.util.toBuffer
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.io.InputStream

abstract class Mesh : Resource() {
    companion object {
        fun combine(vararg meshes: Mesh): Mesh {
            val m = EmptyMesh()
            meshes.forEach { m.append(it) }
            return m
        }
    }

    var vertices: MutableList<Float> = mutableListOf()
    var uvCoordinates: MutableList<Float> = mutableListOf()
    var normals: MutableList<Float> = mutableListOf()
    var indices: MutableList<Int> = mutableListOf()
    val vao = GL30.glGenVertexArrays()
    val pbo = GL15.glGenBuffers()
    val ibo = GL15.glGenBuffers()
    val nbo = GL15.glGenBuffers()
    val ubo = GL15.glGenBuffers()

    protected abstract fun decode(
        stream: InputStream,
        onSuccess: (
            vertices: MutableList<Float>,
            uvCoordinates: FloatArray,
            normals: FloatArray,
            indices: IntArray
        ) -> Unit
    )

    fun append(other: Mesh) {
        this.vertices.addAll(other.vertices)
        this.uvCoordinates.addAll(other.uvCoordinates)
        this.normals.addAll(other.normals)
        this.indices.addAll(other.indices)
    }

    fun bindAll() {
        GL30.glBindVertexArray(vao)

        // Bind vertices
        vertices.let {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo)
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, it.toFloatArray().toBuffer(), GL15.GL_STATIC_DRAW)
            GL20.glVertexAttribPointer(
                0, 3, GL11.GL_FLOAT,
                false, 0, 0
            )
        }

        // Bind uvCoordinates
        uvCoordinates.let {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ubo)
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, it.toFloatArray().toBuffer(), GL15.GL_STATIC_DRAW)
            GL20.glVertexAttribPointer(
                1, 2, GL11.GL_FLOAT,
                false, 0, 0
            )
        }

        // Bind normals
        normals.let {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, nbo)
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, it.toFloatArray().toBuffer(), GL15.GL_STATIC_DRAW)
            GL20.glVertexAttribPointer(
                2, 3, GL11.GL_FLOAT,
                false, 0, 0
            )
        }

        // Bind indices
        indices.let {
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo)
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, it.toIntArray().toBuffer(), GL15.GL_STATIC_DRAW)
        }

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
        GL30.glBindVertexArray(0)
    }
}
