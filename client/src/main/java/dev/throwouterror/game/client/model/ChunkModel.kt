package dev.throwouterror.game.client.model

import dev.throwouterror.game.client.engine.Model
import dev.throwouterror.game.client.engine.mesh.CubeMesh
import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.Chunk

/**
 * @author Theo Paris
 */
class ChunkModel(val chunk: Chunk) {
    val blocks: Array<Array<Array<Model?>>> = arrayOf()
    val mesh = Mesh()

    init {
        var x = 0
        while (x < Chunk.CHUNK_SIZE.x) {
            var y = 0
            while (y < Chunk.CHUNK_SIZE.z) {
                var z = 0
                while (z < Chunk.CHUNK_SIZE.z) {
                    val block = chunk.blocks[x][y][z]
                    if (block != null) {
                        val b = Model(Transform.pos(x, y, z), CubeMesh())
                        blocks[x][y][z] = b
                    }
                    z++;
                }
                y++
            }
            x++
        }
    }

    fun draw()
    {
        var x = 0
        while (x < Chunk.CHUNK_SIZE.x) {
            var y = 0
            while (y < Chunk.CHUNK_SIZE.z) {
                var z = 0
                while (z < Chunk.CHUNK_SIZE.z) {
                    blocks[x][y][z]?.render()
                    z++;
                }
                y++
            }
            x++
        }
    }
}