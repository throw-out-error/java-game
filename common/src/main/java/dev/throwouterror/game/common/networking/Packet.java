/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common.networking;

import dev.throwouterror.util.data.JsonUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Creepinson
 */
public class Packet {
    protected String name;
    protected Object data;

    public Packet(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public Object getData() {
        return this.data;
    }

    public String toJson() {
        return JsonUtils.get().toJson(this);
    }

    public static Packet fromJson(String json) {
        return JsonUtils.get().fromJson(json, Packet.class);
    }

    public Packet toStream(ObjectOutputStream stream) throws IOException {
        stream.writeUTF(JsonUtils.get().toJson(this));
        return this;
    }

    public static Packet fromStream(ObjectInputStream stream) throws IOException {
        return JsonUtils.get().fromJson(stream.readUTF(), Packet.class);
    }
}
