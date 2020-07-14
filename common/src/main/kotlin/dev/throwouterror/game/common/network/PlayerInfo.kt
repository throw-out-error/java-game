
package dev.throwouterror.game.common.network

import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.util.data.JsonUtils
import java.util.*

/**
 * @author Theo Paris
 */
data class PlayerInfo(var id: UUID, var transform: Transform) {
    fun toJson(): String {
        return JsonUtils.get().toJson(this)
    }

    companion object {
        fun fromPlayer(player: Player): PlayerInfo {
            return PlayerInfo(player.id, player.transform)
        }
    }
}