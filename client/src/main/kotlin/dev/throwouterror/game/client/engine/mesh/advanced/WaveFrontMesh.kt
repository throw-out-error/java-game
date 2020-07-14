package dev.throwouterror.game.client.engine.mesh.advanced

import dev.throwouterror.game.client.engine.mesh.Mesh
import org.joml.Vector3i
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */

class WaveFront(file: String) : Mesh() {
    init {
        if (!file.endsWith(".obj")) throw Exception("This file not is a WaveFront.")
        getResource(file) { stream ->
            decode(stream) { vertices, uvCoordinates, normals, indices ->
                indicesCount = indices.size
                this.vertices = vertices
                this.uvCoordinates = uvCoordinates.toMutableList()
                this.normals = normals.toMutableList()
                this.indices = indices.toMutableList()
                bindAll()
            }
        }
    }

    override fun decode(stream: InputStream, onSuccess: (vertices: MutableList<Float>,
                                                         uvCoordinates: FloatArray,
                                                         normals: FloatArray,
                                                         indices: IntArray) -> Unit) {
        // [0] uvCoordinates [1] normal
        val buffer = mutableListOf<FloatArray>()
        val vertices = mutableListOf<Float>()
        val uvCoordinates = mutableListOf<Float>()
        val normals = mutableListOf<Float>()
        val faces = arrayOf<MutableList<Int>>(
                mutableListOf(),    // [0] vertex_indices
                mutableListOf(),    // [1] uvCoordinates_indices
                mutableListOf()     // [2] normals_indices
        )

        BufferedReader(InputStreamReader(stream)).also { file ->
            while (true) {
                file.readLine()?.also { line ->
                    when {
                        line.startsWith("v ") ->
                            line.substring(2)
                                    .split(' ')
                                    .filter { it.isNotEmpty() }
                                    .map { str ->
                                        str.toFloat()
                                    }.also { vertices.addAll(it) }

                        line.startsWith("vt ") ->
                            line.substring(2)
                                    .split(' ')
                                    .filter { it.isNotEmpty() }
                                    .map { str ->
                                        str.toFloat()
                                    }.also { uvCoordinates.addAll(it) }

                        line.startsWith("vn ") ->
                            line.substring(2)
                                    .split(' ')
                                    .filter { it.isNotEmpty() }
                                    .map { str ->
                                        str.toFloat()
                                    }.also { normals.addAll(it) }

                        line.startsWith("f ") ->
                            line.substring(2).split(' ')
                                    .filter { it.isNotEmpty() }
                                    .forEach { values ->
                                        values.split("/")
                                                .filter { it.isNotEmpty() }
                                                .map { str -> str.toInt() }.also { face ->
                                                    repeat(3) { index ->
                                                        faces[index]
                                                                .add(face[index] - 1)
                                                    }
                                                }
                                    }
                    }
                } ?: break
            }

            repeat(2) {
                buffer.add(FloatArray(vertices.size))
            }

            repeat(faces[0].size) { index ->
                val face = Vector3i(
                        faces[0][index],
                        faces[1][index],
                        faces[2][index]
                )
                with(buffer[0]) {
                    val col = face.x * 2

                    this[col] = uvCoordinates[face.y * 2]
                    this[(col + 1)] =
                            1 - uvCoordinates[(face.y * 2 + 1)]
                }

                with(buffer[1]) {
                    val col = face.x * 3

                    this[col] = normals[face.z * 3]
                    this[col + 1] = normals[face.z * 3 + 1]
                    this[col + 2] = normals[face.z * 3 + 2]
                }
            }
            file.close()
        }
        stream.close()
        onSuccess(vertices, buffer[0], buffer[1], faces[0].toIntArray())
    }
}
