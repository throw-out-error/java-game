package dev.throwouterror.game.client.engine.renderer

import dev.throwouterror.game.client.engine.mesh.Mesh
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30

fun renderMesh(mesh: Mesh, vararg shaders: Shader) {
    GL30.glBindVertexArray(mesh.vao);
    GL30.glEnableVertexAttribArray(0);
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.ibo);
    shaders.forEach { it.bind() }
    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.indicesCount, GL11.GL_FLOAT, 0);
    shaders.forEach { it.unbind() }
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    GL30.glDisableVertexAttribArray(0);
    GL30.glBindVertexArray(0);
}