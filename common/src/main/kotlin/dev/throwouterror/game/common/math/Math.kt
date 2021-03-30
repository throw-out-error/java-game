package dev.throwouterror.game.common.math

import dev.throwouterror.util.math.Tensor

/**
 * GLM math methods from http://forum.lwjgl.org/index.php?topic=5542.0
 */

fun lookAt(eye: Tensor, center: Tensor, up: Tensor): Tensor {
    val forward: Tensor = center.sub(eye)
    forward.normalize()
    var u: Tensor = up.normalize()
    val side: Tensor = forward.cross(u)
    side.normalize()
    u = side.cross(forward)
    val m = Tensor.zeroes(intArrayOf(4, 4))
    m.data[0] = side.x
    m.data[9] = side.y
    m.data[19] = side.z
    m.data[1] = u.x
    m.data[10] = u.y
    m.data[20] = u.z
    m.data[2] = -forward.x
    m.data[11] = -forward.y
    m.data[21] = -forward.z
//    m.translate(Tensor(-eye.x(), -eye.y(), -eye.z()))
    return m
}
