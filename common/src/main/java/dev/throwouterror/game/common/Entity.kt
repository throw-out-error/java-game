/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common

import dev.throwouterror.game.common.networking.TextPacket
import dev.throwouterror.util.data.JsonUtils
import java.io.Serializable
import java.util.*

/**
 * @author Theo Paris
 */
open class Entity : Serializable {
    var transform: Transform = Transform()
        set
    val type: String
    var id: UUID

    constructor(id: UUID, type: String, transform: Transform) {
        this.id = id
        this.type = type
        this.transform = transform
    }

    constructor(e: Entity) {
        this.id = e.id
        this.type = e.type
        this.transform = e.transform.clone()
    }

    fun getID(): String {
        return this.id.toString() + "-" + this.type
    }

    override fun toString(): String {
        return getID()
    }

    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }

    fun toPacket(name: String): TextPacket {
        return TextPacket(name, toJson())
    }
}
