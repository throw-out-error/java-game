package dev.throwouterror.game.common.data

import SimplexNoise
import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.Transform.Companion.pos
import dev.throwouterror.util.data.JsonUtils
import dev.throwouterror.util.math.Tensor

/**
 * @author Theo Paris
 */
class Chunk @JvmOverloads constructor(val origin: Transform, private val seed: Int = (0..Int.MAX_VALUE).random()) {
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
                val y = SimplexNoise(seed).noise(x.toDouble(), z.toDouble()) * CHUNK_SIZE.y
                addBlock(Block("dirt", pos(x.toDouble(), y, z.toDouble())))
                x++
            }
            x++
        }
    }

    fun toJson(): String {
        return JsonUtils.builder.toJson(this)
    }

    companion object {
        var CHUNK_SIZE = Tensor(16, 16, 16)
        fun fromJson(s: String): Chunk {
            return JsonUtils.builder.fromJson(s, Chunk::class.java)
        }
    }

    init {
        blocks = Array(CHUNK_SIZE.x.toInt()) {
            Array(CHUNK_SIZE.y.toInt()) {
                arrayOfNulls(CHUNK_SIZE.z.toInt())
            }
        }
    }
}
