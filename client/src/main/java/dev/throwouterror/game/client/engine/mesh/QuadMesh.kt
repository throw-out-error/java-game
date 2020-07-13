package dev.throwouterror.game.client.engine.mesh

import dev.throwouterror.util.math.Tensor

/**
 * @author Throw Out Error (https://throw-out-error.dev)
 */
class QuadMesh : Mesh() {
    init {
        vertices.addAll(arrayListOf(
                Tensor(-0.5, 0.5, 0.0),
                Tensor(0.5, 0.5, 0.0),
                Tensor(-0.5, -0.5, 0.0),
                Tensor(0.5, -0.5, 0.0)
        ))

        indices.addAll(arrayListOf(
                0, 1, 2,
                2, 3, 1
        ))
    }
}