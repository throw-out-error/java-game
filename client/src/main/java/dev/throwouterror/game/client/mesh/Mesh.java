/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client.mesh;

import org.joml.Vector3f;

/**
 * @author Theo Paris
 */
public class Mesh {
    public Vector3f[] vertices;

    public Mesh(Vector3f... vertices) {
        this.vertices = vertices;
    }


}
