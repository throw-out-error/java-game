package dev.throwouterror.game.common


import dev.throwouterror.util.data.JsonUtils
import dev.throwouterror.util.math.Direction
import dev.throwouterror.util.math.Tensor
import java.io.Serializable

class Transform @JvmOverloads constructor(val position: Tensor = Tensor.ZERO_VECTOR.clone(), val rotation: Tensor = Tensor.ZERO_VECTOR.clone(), val scale: Tensor = Tensor(1.0, 1.0, 1.0)) : Serializable {

    fun clone(): Transform {
        return Transform(this.position.clone(), this.rotation.clone(), this.scale.clone())
    }

    override fun toString(): String {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}'.toString()
    }

    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }

    fun move(t: Tensor): Transform {
        this.position.add(t)
        return this

    }

    fun move(t: Direction): Transform {
        return this.move(t.facingVec)
    }

    companion object {
        fun fromJson(json: String): Transform {
            return JsonUtils.get().fromJson(json, Transform::class.java)
        }

        fun pos(x: Double, y: Double, z: Double): Transform {
            return Transform(Tensor(x, y, z), Tensor.ZERO_VECTOR.clone(), Tensor(1.0, 1.0, 1.0))
        }

        fun pos(x: Float, y: Float, z: Float): Transform {
            return pos(x.toDouble(), y.toDouble(), z.toDouble())
        }

        fun pos(x: Int, y: Int, z: Int): Transform {
            return pos(x.toDouble(), y.toDouble(), z.toDouble())
        }
    }
}