/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common;

import org.joml.Matrix4f;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Theo Paris
 */
public class Entity implements Serializable {
    private Transform transform;
    private String type;
    private UUID id;

    public Entity(UUID id, String type, Transform transform) {
        this.id = id;
        this.type = type;
        this.transform = transform;
    }

    public Entity(Entity e) {
        this.transform = e.transform.clone();
    }

    public Matrix4f getScaleMatrix() {
        return new Matrix4f().scale(transform.getScale());
    }

    public Matrix4f getTranslationMatrix() {
        return new Matrix4f().translate(transform.getPosition());
    }

    public Matrix4f getRotation() {
        return transform.getRotation();
    }

    public Transform getTransform() {
        return this.transform;
    }

    public String getType() {
        return this.type;
    }

    public String getID() {
        return this.id.toString() + "-" + this.type;
    }


}
