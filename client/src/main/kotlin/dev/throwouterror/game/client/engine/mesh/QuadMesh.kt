package dev.throwouterror.game.client.engine.mesh

import java.io.InputStream

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
class QuadMesh : Mesh() {
    init {
        vertices.addAll(arrayListOf(
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f
        ))

        indices.addAll(arrayListOf(
                0, 1, 2,
                0, 3, 2
        ))

        bindAll()
    }

    override fun decode(stream: InputStream, onSuccess: (vertices: MutableList<Float>, uvCoordinates: FloatArray, normals: FloatArray, indices: IntArray) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}