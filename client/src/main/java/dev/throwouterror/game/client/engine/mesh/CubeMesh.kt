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
    }
}