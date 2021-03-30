package dev.throwouterror.game.client

import dev.throwouterror.game.client.engine.material.Material
import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.Shader
import dev.throwouterror.game.common.Transform

/**
 * @author Theo Paris
 */
open class Model(var transform: Transform, var mesh: Mesh, var material: Material) {
    var parent: Model? = null
    val children: ArrayList<Model> = arrayListOf()
    val shader: Shader = Shader("shaders/vertex.glsl", "shaders/fragment.glsl")

    init {
        shader.create()
    }

    fun append(m: Model): Model {
        m.parent = this
        children.add(m)
        return this
    }

    private fun render() {
        if (children.size > 0)
            for (child in children)
                child.render()
        draw()
    }

    open fun draw() {
        render()
    }

    fun destroy() {
        shader.destroy()
    }
}
