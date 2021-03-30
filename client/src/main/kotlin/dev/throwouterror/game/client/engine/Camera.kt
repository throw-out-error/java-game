package dev.throwouterror.game.client.engine

import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.math.lookAt
import dev.throwouterror.util.math.Tensor
import kotlin.math.cos
import kotlin.math.sin

class Camera(val transform: Transform) {
    var aspectRatio = 0.0f
        private set
    var fieldOfView = 0.0f
        private set
    val heading = 0.0f
    val pitch = 0.0f
    val roll = 0.0f
    private var zFar = 0.0f
        private set
    private var zNear = 0.0f
        private set
    private val rotationMatrix: Tensor = Tensor.zeroes(intArrayOf(4, 4))

    fun getPickRay(
        ray: Ray,
        screenX: Float,
        screenY: Float,
        viewportX: Float,
        viewportY: Float,
        viewportWidth: Float,
        viewportHeight: Float
    ): Ray {
        ray.setOrigin(screenX, screenY, 0.0f)
        ray.setDirection(screenX, screenY, 1.0f)
        unproject(ray.origin, viewportX, viewportY, viewportWidth, viewportHeight)
        unproject(ray.direction, viewportX, viewportY, viewportWidth, viewportHeight)
        ray.direction.sub(ray.origin).normalize()
        return ray
    }

    fun lookAt(target: Tensor, up: Tensor) {
        lookAt(transform.position, target, up)
        transform.rotation.set(rotationMatrix.x.toDouble(), rotationMatrix.y.toDouble(), rotationMatrix.z.toDouble())
    }

    fun moveForward(transform: Transform, dx: Double, dy: Double, dz: Double) {
        transform.position.x -= rotationMatrix.z * cos(Math.toRadians(dx)).toFloat()
        transform.position.y += rotationMatrix.y * sin(Math.toRadians(dy)).toFloat()
        transform.position.z += rotationMatrix.z * sin(Math.toRadians(dz)).toFloat()
    }

    fun rotate(direction: Tensor) {
//        rotationMatrix.tra(direction)
    }

    fun unproject(
        windowCoords: Tensor,
        viewportX: Float,
        viewportY: Float,
        viewportWidth: Float,
        viewportHeight: Float
    ): Tensor {
        var x: Double = (windowCoords.x - viewportX) / viewportWidth
        var y: Double = (windowCoords.y - viewportY) / viewportHeight
        var z: Double = windowCoords.z
        x = x * 2.0 - 1.0
        y = y * 2.0 - 1.0
        z = z * 2.0 - 1.0
        windowCoords.set(x, y, z)
        return windowCoords
    }
}
