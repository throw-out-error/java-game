
package dev.throwouterror.game.common.data.entity

import dev.throwouterror.game.common.network.PlayerInfo

/**
 * @author Theo Paris
 */
open class Player(info: PlayerInfo) : Entity(info.id, "player", info.transform) {

}