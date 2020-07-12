package dev.throwouterror.game.client.engine.renderer

import dev.throwouterror.game.client.engine.mesh.Mesh
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30

fun renderMesh(mesh: Mesh) {
    GL30.glBindVertexArray(mesh.vao)
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.ibo)
    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.indices.size, GL11.GL_UNSIGNED_INT, 0)
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
    GL30.glBindVertexArray(0)
}