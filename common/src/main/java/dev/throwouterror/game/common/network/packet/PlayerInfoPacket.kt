
package dev.throwouterror.game.common.network.packet

import dev.throwouterror.game.common.network.PlayerInfo
import xyz.baddeveloper.lwsl.Packet

/**
 * @author Theo Paris
 */
class PlayerInfoPacket(type: String, info: PlayerInfo) : Packet() {
    init {
        `object`.put("type", type)
        `object`.put("info", info.toJson())
    }
}