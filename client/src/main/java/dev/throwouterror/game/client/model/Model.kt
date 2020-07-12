package dev.throwouterror.game.client.model

import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.renderMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.math.Tensor
import org.lwjgl.opengl.GL11

/**
 * @author Theo Paris
 */
open class Model(var transform: Transform, var mesh: Mesh, var color: Tensor = Tensor(0f, 0f, 0f)) {
    var parent: Model? = null
    val children: ArrayList<Model> = arrayListOf()

    fun render() {
        this.render(this.children)
    }

    fun append(m: Model): Model {
        m.parent = this
        children.add(m)
        return this
    }

    fun render(children: ArrayList<Model>) {
        for (child in children) {
            this.render(child.children)
        }
        draw()
    }

    open fun draw() {
        GL11.glColor3d(color.x, color.y, color.z)
        GL11.glTranslated(transform.position.x, transform.position.y, transform.position.z)
        GL11.glScaled(transform.scale.x, transform.scale.y, transform.scale.z)
        GL11.glRotated(transform.rotation.x, 1.0, 0.0, 0.0)
        GL11.glRotated(transform.rotation.y, 0.0, 1.0, 0.0)
        GL11.glRotated(transform.rotation.z, 0.0, 0.0, 1.0)
        renderMesh(mesh)
    }
}