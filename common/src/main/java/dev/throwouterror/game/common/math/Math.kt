package dev.throwouterror.game.common.math

import dev.throwouterror.util.math.Tensor
import dev.throwouterror.util.math.rotation.RotationMatrix


/**
 * GLM math methods from http://forum.lwjgl.org/index.php?topic=5542.0
 */

fun lookAt(eye: Tensor, center: Tensor, up: Tensor): RotationMatrix {
    val forward: Tensor = center.sub(eye)
    forward.normalize()
    var u: Tensor = up.normalize()
    val side: Tensor = forward.cross(u)
    side.normalize()
    u = side.cross(forward)
    val m = RotationMatrix()
    m.m00 = side.x
    m.m10 = side.y
    m.m20 = side.z
    m.m01 = u.x
    m.m11 = u.y
    m.m21 = u.z
    m.m02 = -forward.x
    m.m12 = -forward.y
    m.m22 = -forward.z
//    m.translate(Tensor(-eye.x(), -eye.y(), -eye.z()))
    return m
}