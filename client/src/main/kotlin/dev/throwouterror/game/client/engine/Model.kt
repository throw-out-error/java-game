package dev.throwouterror.game.client.engine

import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.Shader
import dev.throwouterror.game.client.engine.renderer.ShaderType
import dev.throwouterror.game.client.engine.renderer.renderMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.math.Tensor
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30.glBindFragDataLocation

/**
 * @author Theo Paris
 */
open class Model(var transform: Transform, var mesh: Mesh, var color: Tensor = Tensor(0f, 0f, 0f, 1f)) {
    var parent: Model? = null
    val children: ArrayList<Model> = arrayListOf()
    val vShader: Shader = Shader("shaders/vertex.glsl", ShaderType.VERTEX)
    val fShader: Shader = Shader("shaders/fragment.glsl", ShaderType.FRAGMENT)

    init {
        useShaders()
        storeTransformations()
    }

    private fun useShaders() {
        vShader.use()
        fShader.use()
        glBindFragDataLocation(fShader.id, 0, "diffuseColor");
    }

    private fun storeTransformations() {
        GL20.glUniform4f(vShader.getUniformLocation("diffuseColor"), color.x.toFloat(), color.y.toFloat(), color.z.toFloat(), color.w.toFloat())
        GL20.glUniform3f(vShader.getUniformLocation("position"), transform.position.x.toFloat(), transform.position.y.toFloat(), transform.position.z.toFloat())
        GL20.glUniform3f(vShader.getUniformLocation("scale"), transform.scale.x.toFloat(), transform.scale.y.toFloat(), transform.scale.z.toFloat())

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
        renderMesh(mesh)
    }
}