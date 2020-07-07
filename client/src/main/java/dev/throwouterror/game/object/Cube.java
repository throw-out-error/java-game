package dev.throwouterror.game.object;

import org.lwjgl.opengl.GL11;

public class Cube {
    private Transform transform;

    public Cube() {
        this.transform = new Transform();
    }

    public Cube(Transform transform) {
        this.transform = transform;
    }

    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslated(this.transform.getPosition().x(), this.transform.getPosition().y(), this.transform.getPosition().z());
        GL11.glRotated(this.transform.getRotation().x(), 1.0, 0.0, 0.0);
        GL11.glRotated(this.transform.getRotation().y(), 0.0, 1.0, 0.0);
        GL11.glRotated(this.transform.getRotation().z(), 0.0, 0.0, 1.0);
        GL11.glScaled(this.transform.getScale().x(), this.transform.getScale().y(), this.transform.getScale().z());
        
        GL11.glColor3f(0.0f, 0.0f, 0.0f);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glColor3f(1.0f, 0.5f, 0.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
        GL11.glColor3f(1.0f, 0.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

	public Transform getTransform() {
        return this.transform;
    }
}