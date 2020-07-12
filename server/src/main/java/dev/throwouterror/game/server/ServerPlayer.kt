
package dev.throwouterror.game.server

import dev.throwouterror.game.common.data.entity.Player
import dev.throwouterror.game.common.Transform
import xyz.baddeveloper.lwsl.server.SocketHandler
import java.util.*

/**
 * @author Theo Paris
 */
class ServerPlayer(val socket: SocketHandler, id: UUID, transform: Transform) : Player(id, transform) {

}