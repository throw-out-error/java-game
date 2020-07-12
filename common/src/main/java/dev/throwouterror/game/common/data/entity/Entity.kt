package dev.throwouterror.game.common.data.entity

import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.data.JsonUtils
import java.io.Serializable
import java.util.*

/**
 * @author Theo Paris
 */
open class Entity : Serializable {
    var transform: Transform = Transform()
        set
    var parent: Entity? = null
    val children: ArrayList<Entity> = arrayListOf()

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

    fun update() {
        this.update(this.children)
    }

    /**
     * This function is meant to be overridden by other entities.
     */
    fun update(children: ArrayList<Entity>) {
        for (child in children) {
            this.update(child.children)
        }
        onUpdate()
    }

    open fun onUpdate() {

    }
}
