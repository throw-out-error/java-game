/*
 * Copyright (c) Creepinson
 */
package dev.throwouterror.game.common.networking

/**
 * @author Creepinson
 */
class TextPacket(name: String, data: String) : Packet(name, data) {
    override fun getPacketData(): String {
        return super.getPacketData() as String
    }
}