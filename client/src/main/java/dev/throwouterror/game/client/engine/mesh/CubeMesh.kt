package dev.throwouterror.game.client.engine.mesh

import dev.throwouterror.util.math.Tensor

/**
 * @author Theo Paris
 */
class CubeMesh : Mesh() {
    init {
        // Front
        vertices.add(Tensor(.5f, .5f, .5f))
        vertices.add(Tensor(.5f, .5f, .5f))
        vertices.add(Tensor(-.5f, .5f, .5f))
        vertices.add(Tensor(-.5f, -.5f, .5f))
        vertices.add(Tensor(.5f, .5f, .5f))
        vertices.add(Tensor(-.5f, -.5f, .5f))
        vertices.add(Tensor(.5f, -.5f, .5f))
        // Back
        vertices.add(Tensor(-.5f, .5f, -.5f))
        vertices.add(Tensor(.5f, .5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, -.5f))
        vertices.add(Tensor(-.5f, .5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, -.5f))
        vertices.add(Tensor(-.5f, -.5f, -.5f))
        // Left
        vertices.add(Tensor(-.5f, .5f, .5f))
        vertices.add(Tensor(-.5f, .5f, -.5f))
        vertices.add(Tensor(-.5f, -.5f, -.5f))
        vertices.add(Tensor(-.5f, .5f, .5f))
        vertices.add(Tensor(-.5f, -.5f, -.5f))
        vertices.add(Tensor(-.5f, -.5f, .5f))
        // Right
        vertices.add(Tensor(.5f, .5f, -.5f))
        vertices.add(Tensor(.5f, .5f, .5f))
        vertices.add(Tensor(.5f, -.5f, .5f))
        vertices.add(Tensor(.5f, .5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, .5f))
        vertices.add(Tensor(.5f, -.5f, -.5f))
        // Top
        vertices.add(Tensor(.5f, .5f, -.5f))
        vertices.add(Tensor(-.5f, .5f, -.5f))
        vertices.add(Tensor(-.5f, .5f, .5f))
        vertices.add(Tensor(.5f, .5f, -.5f))
        vertices.add(Tensor(-.5f, .5f, .5f))
        vertices.add(Tensor(.5f, .5f, .5f))
        // Bottom
        vertices.add(Tensor(-.5f, -.5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, .5f))
        vertices.add(Tensor(-.5f, -.5f, -.5f))
        vertices.add(Tensor(.5f, -.5f, .5f))
        vertices.add(Tensor(-.5f, -.5f, .5f))

        // Indices
        // front
        indices.addAll(arrayListOf(
                0,
                1,
                2,
                2,
                3,
                0,
                // right
                1,
                5,
                6,
                6,
                2,
                1,
                // back
                7,
                6,
                5,
                5,
                4,
                7,
                // left
                4,
                0,
                3,
                3,
                7,
                4,
                // bottom
                4,
                5,
                1,
                1,
                0,
                4,
                // top
                3,
                2,
                6,
                6,
                7,
                3
        ))
    }
}