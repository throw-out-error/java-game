package dev.throwouterror.game.client.engine.mesh.advanced

import SimplexNoise
import dev.throwouterror.game.client.engine.mesh.Mesh
import java.io.InputStream

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */

class TerrainMesh(size: Int, faceScale: Float, noiseScale: Float, textureScale: Float) : Mesh() {
    init {
        // Generate faces
        repeat(size) { z ->
            repeat(size) { x ->
                val index = vertices.size / 3
                val xPoint = x * faceScale
                val zPoint = -z * faceScale
                val yPoint = SimplexNoise(7).noise(x / 24.0, z / 24.0) * noiseScale

                // Vertices
                vertices.addAll(arrayListOf(xPoint, yPoint.toFloat(), zPoint))

                // Uv coordinates
                uvCoordinates.addAll(
                    arrayListOf(
                        x / (size - 1f) * textureScale,
                        z / (size - 1f) * textureScale
                    )
                )

                // Normals
                normals.addAll(
                    arrayListOf(
                        0f, 1f, 0f
                    )
                )

                // Box indices
                if (x < size - 1 && z < size - 1) {
                    indices.addAll(
                        arrayListOf(
                            index, (index + 1), (index + size),
                            (index + size + 1), (index + size), (index + 1)
                        )
                    )
                }
            }
        }

        bindAll()
    }

    override fun decode(
        stream: InputStream,
        onSuccess: (vertices: MutableList<Float>, uvCoordinates: FloatArray, normals: FloatArray, indices: IntArray) -> Unit
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
