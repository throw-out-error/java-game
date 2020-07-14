package dev.throwouterror.game.client.engine.renderer

import dev.throwouterror.game.client.engine.mesh.Mesh
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

fun renderMesh(mesh: Mesh) {
    GL11.glDepthMask(false)
    GL30.glBindVertexArray(mesh.id)
    GL20.glEnableVertexAttribArray(0)
    GL20.glEnableVertexAttribArray(1)
    GL20.glEnableVertexAttribArray(2)
    GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.indicesCount)
    GL11.glDepthMask(true)
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    GL30.glBindVertexArray(0);
}