package dev.throwouterror.game.client.engine

import dev.throwouterror.util.math.Tensor

class Ray {
    var direction = Tensor.VECTOR_ZERO.clone()
        private set
    var origin = Tensor.VECTOR_ZERO.clone()
        private set

    fun setDirection(x: Float, y: Float, z: Float): Ray {
        this.direction.set(x, y, z)
        return this
    }

    fun setDirection(direction: Tensor): Ray {
        this.direction = direction.clone()
        return this
    }

    fun setOrigin(x: Float, y: Float, z: Float): Ray {
        this.origin.set(x, y, z)
        return this
    }

    fun setOrigin(origin: Tensor): Ray {
        this.origin = origin.clone()
        return this
    }

    public override fun toString(): String {
        return "(origin: $origin direction: $direction)"
    }
}
