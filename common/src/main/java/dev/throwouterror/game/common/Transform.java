/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.common;


import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.io.Serializable;

public class Transform implements Serializable {
    private final Vector3f position;
    private final Matrix4f rotation;
    private final Vector3f scale;

    public Transform() {
        this(new Vector3f(0, 0, 0), new Matrix4f(), new Vector3f(1, 1, 1));
    }

    public Transform(Vector3f position, Matrix4f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public static Transform pos(float x, float y, float z) {
        return new Transform(new Vector3f(x, y, z), new Matrix4f(), new Vector3f(1, 1, 1));
    }

    public Transform clone() {
        return new Transform(new Vector3f(this.position), new Matrix4f(this.rotation), new Vector3f(this.scale));
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public Matrix4f getRotation() {
        return this.rotation;
    }

    public Vector3f getScale() {
        return this.scale;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}';
    }
}