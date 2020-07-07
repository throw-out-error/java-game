/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.renderer;

import dev.throwouterror.game.object.Transform;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * @author Theo Paris
 */
public class MeshRenderer {
    private Transform transform;

    public MeshRenderer() {
        this.transform = new Transform();
    }

    public MeshRenderer(Transform transform) {
        this.transform = transform;
    }

    public void renderMesh(Mesh mesh) {
        GL11.glPushMatrix();
        GL11.glTranslated(this.transform.getPosition().x(), this.transform.getPosition().y(), this.transform.getPosition().z());
        GL11.glRotated(this.transform.getRotation().x(), 1.0, 0.0, 0.0);
        GL11.glRotated(this.transform.getRotation().y(), 0.0, 1.0, 0.0);
        GL11.glRotated(this.transform.getRotation().z(), 0.0, 0.0, 1.0);
        GL11.glScaled(this.transform.getScale().x(), this.transform.getScale().y(), this.transform.getScale().z());

        GL30.glBindVertexArray(mesh.getVAO());
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        glVertexPointer(mesh.getVertices().length, GL_FLOAT, 0, 0L);
        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_FLOAT, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);

        GL11.glPopMatrix();
    }

    public Transform getTransform() {
        return transform;
    }
}
