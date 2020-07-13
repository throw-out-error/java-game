
package dev.throwouterror.game.common.data.entity

import dev.throwouterror.game.common.Transform
import dev.throwouterror.game.common.network.PlayerInfo
import java.util.*

/**
 * @author Theo Paris
 */
open class Player(info: PlayerInfo) : Entity(info.id, "player", info.transform) {

}