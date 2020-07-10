/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.client.mesh;

import org.joml.Vector3f;

/**
 * @author Theo Paris
 */
public class CubeMesh extends Mesh {
    public CubeMesh() {
        super(
                new Vector3f(1.0f, 1.0f, -1.0f), // Top Right Of The Quad (Top)
                new Vector3f(-1.0f, 1.0f, -1.0f), // Top Left Of The Quad (Top)
                new Vector3f(-1.0f, 1.0f, 1.0f), // Bottom Left Of The Quad (Top)
                new Vector3f(1.0f, 1.0f, 1.0f), // Bottom Right Of The Quad (Top)

                new Vector3f(1.0f, -1.0f, 1.0f), // Top Right Of The Quad
                new Vector3f(-1.0f, -1.0f, 1.0f), // Top Left Of The Quad
                new Vector3f(-1.0f, -1.0f, -1.0f), // Bottom Left Of The Quad
                new Vector3f(1.0f, -1.0f, -1.0f),// Bottom Right Of The Quad

                new Vector3f(1.0f, 1.0f, 1.0f),// Top Right Of The Quad (Front)
                new Vector3f(-1.0f, 1.0f, 1.0f),// Top Left Of The Quad (Front)
                new Vector3f(-1.0f, -1.0f, 1.0f),// Bottom Left Of The Quad
                new Vector3f(1.0f, -1.0f, 1.0f),// Bottom Right Of The Quad

                new Vector3f(1.0f, -1.0f, -1.0f), // Bottom Left Of The Quad
                new Vector3f(-1.0f, -1.0f, -1.0f), // Bottom Right Of The Quad
                new Vector3f(-1.0f, 1.0f, -1.0f), // Top Right Of The Quad (Back)
                new Vector3f(1.0f, 1.0f, -1.0f), // Top Left Of The Quad (Back)

                new Vector3f(-1.0f, 1.0f, 1.0f),// Top Right Of The Quad (Left)
                new Vector3f(-1.0f, 1.0f, -1.0f), // Top Left Of The Quad (Left)
                new Vector3f(-1.0f, -1.0f, -1.0f), // Bottom Left Of The Quad
                new Vector3f(-1.0f, -1.0f, 1.0f), // Bottom Right Of The Quad

                new Vector3f(1.0f, 1.0f, -1.0f), // Top Right Of The Quad (Right)
                new Vector3f(1.0f, 1.0f, 1.0f), // Top Left Of The Quad
                new Vector3f(1.0f, -1.0f, 1.0f),// Bottom Left Of The Quad
                new Vector3f(1.0f, -1.0f, -1.0f) // Bottom Right Of The Quad
        );
    }
}
