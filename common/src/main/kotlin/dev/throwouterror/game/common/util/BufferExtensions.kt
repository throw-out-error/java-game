package dev.throwouterror.game.common.util

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

fun createByteBuffer(capacity: Int): ByteBuffer {
    return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder())
}

fun createFloatBuffer(capacity: Int): FloatBuffer {
    return createByteBuffer(capacity shl 2).asFloatBuffer()
}

fun createIntBuffer(capacity: Int): IntBuffer {
    return createByteBuffer(capacity shl 2).asIntBuffer()
}

fun FloatArray.toBuffer(): FloatBuffer = createFloatBuffer(this.size).apply {
    put(this@toBuffer)
    flip()
}

fun IntArray.toBuffer(): IntBuffer = createIntBuffer(this.size).apply {
    put(this@toBuffer)
    flip()
}

fun MutableList<Float>.toIntBuffer(): IntBuffer =
        this.map { it.toInt() }.toIntArray().toBuffer()

fun MutableList<Float>.toFloatBuffer(): FloatBuffer =
        this.toFloatArray().toBuffer()