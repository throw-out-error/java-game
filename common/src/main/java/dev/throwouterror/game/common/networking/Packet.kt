/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.common.networking

import dev.throwouterror.util.data.JsonUtils
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * @author Creepinson
 */
open class Packet(var name: String, protected var data: Any) {
    open fun getPacketData(): Any? {
        return data
    }

    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }

    @Throws(IOException::class)
    fun toStream(stream: ObjectOutputStream): Packet {
        stream.writeUTF(JsonUtils.get().toJson(this))
        return this
    }

    companion object {
        fun fromJson(json: String?): Packet {
            return JsonUtils.get().fromJson(json, Packet::class.java)
        }

        @Throws(IOException::class)
        fun fromStream(stream: ObjectInputStream): Packet {
            return JsonUtils.get().fromJson(stream.readUTF(), Packet::class.java)
        }
    }

}