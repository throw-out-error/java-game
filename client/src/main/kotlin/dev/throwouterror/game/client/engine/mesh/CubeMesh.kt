package dev.throwouterror.game.client.engine.mesh

import java.io.InputStream

/**
 * @author Theo Paris
 */
class CubeMesh : Mesh() {
    init {
        // Front
        vertices.addAll(
                arrayListOf(.5f, .5f, .5f,
                        .5f, .5f, .5f,
                        -.5f, .5f, .5f,
                        -.5f, -.5f, .5f,
                        .5f, .5f, .5f,
                        -.5f, -.5f, .5f,
                        .5f, -.5f, .5f,
                        // Bac
                        -.5f, .5f, -.5f,
                        .5f, .5f, -.5f,
                        .5f, -.5f, -.5f,
                        -.5f, .5f, -.5f,
                        .5f, -.5f, -.5f,
                        -.5f, -.5f, -.5f,
                        // Left
                        -.5f, .5f, .5f,
                        -.5f, .5f, -.5f,
                        -.5f, -.5f, -.5f,
                        -.5f, .5f, .5f,
                        -.5f, -.5f, -.5f,
                        -.5f, -.5f, .5f,
                        // Right
                        .5f, .5f, -.5f,
                        .5f, .5f, .5f,
                        .5f, -.5f, .5f,
                        .5f, .5f, -.5f,
                        .5f, -.5f, .5f,
                        .5f, -.5f, -.5f,
                        // Top
                        .5f, .5f, -.5f,
                        -.5f, .5f, -.5f,
                        -.5f, .5f, .5f,
                        .5f, .5f, -.5f,
                        -.5f, .5f, .5f,
                        .5f, .5f, .5f,
                        // Bottom
                        -.5f, -.5f, -.5f,
                        .5f, -.5f, -.5f,
                        .5f, -.5f, .5f,
                        -.5f, -.5f, -.5f,
                        .5f, -.5f, .5f,
                        -.5f, -.5f, .5f
                ))

        // Indices
        indices.addAll(arrayListOf(
                // front
                0,
                1,
                2,
                2,
                3,
                0,
                // right
                1,
                5,
                6,
                6,
                2,
                1,
                // back
                7,
                6,
                5,
                5,
                4,
                7,
                // left
                4,
                0,
                3,
                3,
                7,
                4,
                // bottom
                4,
                5,
                1,
                1,
                0,
                4,
                // top
                3,
                2,
                6,
                6,
                7,
                3
        ))
    }

    override fun decode(stream: InputStream, onSuccess: (vertices: MutableList<Float>, uvCoordinates: FloatArray, normals: FloatArray, indices: IntArray) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}