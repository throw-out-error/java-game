/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common


import dev.throwouterror.game.common.networking.TextPacket
import dev.throwouterror.util.data.JsonUtils
import org.joml.Vector3f
import java.io.Serializable

class Transform @JvmOverloads constructor(val position: Vector3f = Vector3f(0f, 0f, 0f), val rotation: Vector3f = Vector3f(0f, 0f, 0f), val scale: Vector3f = Vector3f(1f, 1f, 1f)) : Serializable {

    fun clone(): Transform {
        return Transform(Vector3f(this.position), Vector3f(this.rotation), Vector3f(this.scale))
    }

    override fun toString(): String {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}'.toString()
    }

    fun toPacket(name: String): TextPacket {
        return TextPacket(name, toJson())
    }

    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Transform {
            return JsonUtils.get().fromJson(json, Transform::class.java)
        }

        fun pos(x: Float, y: Float, z: Float): Transform {
            return Transform(Vector3f(x, y, z), Vector3f(0f, 0f, 0f), Vector3f(1f, 1f, 1f))
        }
    }
}