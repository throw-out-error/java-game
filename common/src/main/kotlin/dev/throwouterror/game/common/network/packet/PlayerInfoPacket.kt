package dev.throwouterror.game.common.network.packet

import dev.throwouterror.game.common.network.PlayerInfo
import xyz.baddeveloper.lwsl.packet.Packet

/**
 * @author Theo Paris
 */
class PlayerInfoPacket(type: String, info: PlayerInfo) : Packet() {
    init {
        jsonObject.put("type", type)
        jsonObject.put("info", info.toJson())
    }
}