package dev.throwouterror.game.client.engine.renderer

import dev.throwouterror.game.client.engine.Model
import dev.throwouterror.game.common.Transform
import dev.throwouterror.util.math.Tensor
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30


private fun storeTransformations(shader: Shader, transform: Transform, color: Tensor) {
    shader.setUniform("position", transform.position)
    shader.setUniform("scale", transform.scale)
    shader.setUniform("color", color)
}

fun renderMesh(model: Model) {
    GL30.glBindVertexArray(model.mesh.vao)
    GL30.glEnableVertexAttribArray(0)
    GL30.glEnableVertexAttribArray(1)
    GL30.glEnableVertexAttribArray(2)
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.mesh.ibo)
    if (model.material.hasTexture()) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, model.material.textureID)
    }
    model.shader.bind()
    storeTransformations(model.shader, model.transform, model.material.color)
    GL11.glDrawElements(GL11.GL_TRIANGLES, model.mesh.indices.size, GL11.GL_UNSIGNED_INT, 0)
    model.shader.unbind()
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
    GL30.glDisableVertexAttribArray(0)
    GL30.glDisableVertexAttribArray(1)
    GL30.glDisableVertexAttribArray(2)
    GL30.glBindVertexArray(0)
}