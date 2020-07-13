package dev.throwouterror.game.server

import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.game.common.network.PlayerInfo
import xyz.baddeveloper.lwsl.server.SocketHandler

/**
 * @author Theo Paris
 */
class ServerPlayer(val socket: SocketHandler, info: PlayerInfo) : Player(info) {

}