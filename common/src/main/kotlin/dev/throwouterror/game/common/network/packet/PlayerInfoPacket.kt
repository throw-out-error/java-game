package dev.throwouterror.game.common.network.packet

import com.firenodes.lwsl.packet.Packet
import dev.throwouterror.game.common.network.PlayerInfo

/**
 * @author Theo Paris
 */
class PlayerInfoPacket(type: String, info: PlayerInfo) : Packet() {
    init {
        jsonObject.addProperty("type", type)
        jsonObject.addProperty("info", info.toJson())
    }
}
