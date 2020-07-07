package dev.throwouterror.game;

import dev.throwouterror.game.object.Cube;
import dev.throwouterror.game.object.Transform;
import org.lwjgl.glfw.GLFW;

public class Game extends Thread {
    public Thread game;
    public Window window;
    public Cube cube;
    public final int WIDTH = 1280, HEIGHT = 760;

    public void init() {
        SharedLibraryLoader.load();

        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(0.0f, 0, 0);
        window.create();
        cube = new Cube(Transform.pos(0, 0, 0));
    }

    public void run() {
        init();
        while (!window.shouldClose()) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
        }
        window.destroy();
    }

    private void update() {
        window.update();
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
    }

    private void render() {
        cube.render();
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}