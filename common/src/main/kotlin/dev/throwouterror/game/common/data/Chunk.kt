
package dev.throwouterror.game.common.data

import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.Transform.Companion.pos
import dev.throwouterror.util.data.JsonUtils
import org.joml.Random
import org.joml.SimplexNoise
import org.joml.Vector3i

/**
 * @author Theo Paris
 */
class Chunk @JvmOverloads constructor(val origin: Transform, private val seed: Long = Random.newSeed()) {
    val blocks: Array<Array<Array<Block?>>>

    fun addBlock(b: Block) {
        val x = b.transform.position.x.toInt()
        val y = b.transform.position.y.toInt()
        val z = b.transform.position.z.toInt()
        blocks[x][y][z] = b
    }

    fun generate() {
        var x = 0
        while (x < CHUNK_SIZE.x) {
            val z = 0
            while (z < CHUNK_SIZE.z) {
                val y = SimplexNoise.noise(x.toFloat(), z.toFloat(), seed.toFloat()) * CHUNK_SIZE.y
                addBlock(Block("dirt", pos(x.toFloat(), y, z.toFloat())))
                x++
            }
            x++
        }
    }

    fun toJson(): String {
        return JsonUtils.get().toJson(this);
    }

    companion object {
        var CHUNK_SIZE = Vector3i(16, 16, 16)
        fun fromJson(s: String): Chunk {
            return JsonUtils.get().fromJson(s, Chunk::class.java);
        }
    }

    init {
        blocks = Array(CHUNK_SIZE.x) { Array(CHUNK_SIZE.y) { arrayOfNulls<Block>(CHUNK_SIZE.z) } }
    }
}