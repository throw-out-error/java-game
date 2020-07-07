/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game.renderer;

import dev.throwouterror.util.math.Tensor;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * @author Theo Paris
 */
public class Mesh {
    private Tensor[] vertices;
    private int[] indices;
    private int vao, pbo, ibo;

    public Mesh(Tensor[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public Mesh create() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = BufferUtils.createFloatBuffer(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionData[i * 3] = vertices[i].floatX();
            positionData[i * 3 + 1] = vertices[i].floatY();
            positionData[i * 3 + 2] = vertices[i].floatZ();
        }
        positionBuffer.put(positionData).flip();

        pbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return this;
    }

    public int getVAO() {
        return vao;
    }

    public int[] getIndices() {
        return indices;
    }

    public Tensor[] getVertices() {
        return vertices;
    }


    public int getPBo() {
        return pbo;
    }

    public int getIBO() {
        return ibo;
    }
}
