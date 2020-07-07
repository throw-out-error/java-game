package dev.throwouterror.game.object;

import dev.throwouterror.game.renderer.Mesh;
import dev.throwouterror.game.renderer.MeshRenderer;
import dev.throwouterror.util.math.Tensor;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class Cube {
    private final MeshRenderer renderer;
    private Mesh mesh = new Mesh(new Tensor[]{
            new Tensor(-0.5, 0.5f, 0.0f),
            new Tensor(0.5, 0.5f, 0.0f),
            new Tensor(0.5, -0.5f, 0.0f),
            new Tensor(-0.5, -0.5f, 0.0f),
    }, new int[]{
            0, 1, 2,
            1, 2, 3
    });

    public Cube(Transform t) {
        this.renderer = new MeshRenderer(t);
        mesh.create();
    }
    
    public void render() {
        GL11.glColor3f(1,0,0);
        this.renderer.renderMesh(mesh);
    }

    public Transform getTransform() {
        return this.renderer.getTransform();
    }
}