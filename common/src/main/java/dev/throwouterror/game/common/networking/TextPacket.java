/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common.networking;

/**
 * @author Creepinson
 */
public class TextPacket extends Packet {
    public TextPacket(String name, String data) {
        super(name, data);
    }

    @Override
    public String getData() {
        return (String) super.getData();
    }
}
