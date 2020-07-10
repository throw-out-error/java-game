/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.common.networking

import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.data.JsonUtils
import java.util.*

/**
 * @author Theo Paris
 */
data class PlayerInfo(var id: UUID, var transform: Transform) {
    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }
}