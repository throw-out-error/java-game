package dev.throwouterror.game.client.model

import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.ShaderProgram
import dev.throwouterror.game.client.engine.renderer.renderMesh
import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.math.Tensor
import org.lwjgl.opengl.GL20

/**
 * @author Theo Paris
 */
open class Model(var transform: Transform, var mesh: Mesh, var color: Tensor = Tensor(0f, 0f, 0f, 1f)) {
    var parent: Model? = null
    val children: ArrayList<Model> = arrayListOf()
    val shader: ShaderProgram = ShaderProgram("#version 330 core\n" +
            "#extension GL_ARB_separate_shader_objects : enable\n" +
            "// Input vertex data, different for all executions of this shader.\n" +
            "layout (location = 0) in vec3 position;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = vec4(position, 1.0);\n" +
            "}", "#version 330 core\n" +
            "\n" +
            "layout(location = 0) out vec4 diffuseColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    \n" +
            "}", mapOf())

    init {
        shader.use()
        storeTransformations()
    }

    private fun storeTransformations() {
        GL20.glUniform4f(shader.getUniformLocation("diffuseColor"), color.x.toFloat(), color.y.toFloat(), color.z.toFloat(), color.w.toFloat())
        GL20.glUniform3f(shader.getUniformLocation("position"), transform.position.x.toFloat(), transform.position.y.toFloat(), transform.position.z.toFloat())
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