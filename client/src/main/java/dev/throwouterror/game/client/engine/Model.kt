package dev.throwouterror.game.client.engine

import dev.throwouterror.game.client.engine.mesh.Mesh
import dev.throwouterror.game.client.engine.renderer.ShaderProgram
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
    val shader: ShaderProgram = ShaderProgram("#version 330 core\n" +
            "\n" +
            "in vec3 position;\n" +
            "in vec4 color;\n" +
            "in vec3 scale;\n" +
            "\n" +
            "uniform mat4 model;\n" +
            "uniform mat4 projection;\n" +
            "uniform mat4 view;\n" +
            "\n" +
            "out vec4 vertexColor;\n" +
            "\n" +
            "void main() {\n" +
            "    // Just pass the color down to the fragment shader\n" +
            "    vertexColor = color;\n" +
            "\n" +
            "    // Calculate the Model, View, Projection matrix into one ( in this order, right to left )\n" +
            "    mat4 mvp = projection * view * model;\n" +
            "\n" +
            "    // Calculate the position of the vertex\n" +
            "    gl_Position = mvp * vec4(position,1)*vec4(scale,1);\n" +
            "}", "#version 330 core\n" +
            "\n" +
            "in vec4 vertexColor;\n" +
            "\n" +
            "out vec4 fragColor;\n" +
            "\n" +
            "void main() {\n" +
            "    fragColor = vec4(vertexColor.xyz,1);\n" +
            "}", mapOf())

    init {
        useShader()
        storeTransformations()
        mesh.create()
    }

    private fun useShader() {
        shader.use()
        glBindFragDataLocation(shader.program, 0, "fragColor");
    }

    private fun storeTransformations() {
        GL20.glUniform4f(shader.getUniformLocation("color"), color.x.toFloat(), color.y.toFloat(), color.z.toFloat(), color.w.toFloat())
        GL20.glUniform3f(shader.getUniformLocation("position"), transform.position.x.toFloat(), transform.position.y.toFloat(), transform.position.z.toFloat())
        GL20.glUniform3f(shader.getUniformLocation("scale"), transform.scale.x.toFloat(), transform.scale.y.toFloat(), transform.scale.z.toFloat())

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