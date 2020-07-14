package dev.throwouterror.game.client.engine

import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.Shader
import dev.throwouterror.game.client.engine.renderer.renderMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.math.Tensor

/**
 * @author Theo Paris
 */
open class Model(var transform: Transform, var mesh: Mesh, var color: Tensor = Tensor(0f, 0f, 0f)) {
    var parent: Model? = null
    val children: ArrayList<Model> = arrayListOf()
    val shader: Shader = Shader("shaders/vertex.glsl", "shaders/fragment.glsl")

    init {
        storeTransformations()
    }

    private fun storeTransformations() {
        shader.setUniform("position", transform.position)
        shader.setUniform("scale", transform.scale)
        shader.setUniform("color", color)
    }

    fun append(m: Model): Model {
        m.parent = this
        children.add(m)
        return this
    }

    fun render() {
        for (child in children) {
            child.render()
        }
        draw()
    }

    open fun draw() {
        storeTransformations()
        renderMesh(mesh, shader)
    }
}