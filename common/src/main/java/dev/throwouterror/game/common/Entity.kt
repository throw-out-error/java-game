/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common

import java.io.Serializable
import java.util.*

/**
 * @author Theo Paris
 */
class Entity : Serializable {
    var transform: Transform? = null
        private set
    val type: String
    val id: UUID

    constructor(id: UUID, type: String, transform: Transform) {
        this.id = id
        this.type = type
        this.transform = transform
    }

    constructor(e: Entity) {
        this.id = e.id
        this.type = e.type
        this.transform = e.transform!!.clone()
    }

    fun getID(): String {
        return this.id.toString() + "-" + this.type
    }


}
