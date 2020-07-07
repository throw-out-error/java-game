/*
 * Copyright (c) Creepinson
 */

package dev.throwouterror.game;

import dev.throwouterror.util.math.Tensor;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;

public class Window {
    private int width, height;
    private String title;
    private long window;
    private int frames;
    private static long time;
    private Input input;
    private Tensor background = new Tensor(0, 0, 0);
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (GLFW.glfwInit() == GL_FALSE) {
            System.err.println("ERROR: GLFW wasn't initializied");
            return;
        }

        input = new Input();
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.err.println("ERROR: Window wasn't created");
            return;
        }

        ByteBuffer videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX[0] = (GLFWvidmode.width(videoMode) - width) / 2;
        windowPosY[0] = (GLFWvidmode.height(videoMode) - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GLContext.createFromCurrent();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }
        GL11.glClearColor(background.floatX(), background.floatY(), background.floatZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window) != GL_FALSE;
    }

    public void destroy() {
        input.destroy();
        sizeCallback.release();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(float r, float g, float b) {
        background = new Tensor(r, g, b);
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwSetWindowSize(window, windowPosX[0], windowPosY[0]);
        }
        GLFW.glfwSetWindowSize(window, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }
}