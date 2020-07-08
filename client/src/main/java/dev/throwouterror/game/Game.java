package dev.throwouterror.game;

import dev.throwouterror.game.loader.ModelLoader;
import dev.throwouterror.game.object.Model;
import dev.throwouterror.game.renderer.Renderer;

public class Game extends Thread {
    public Thread game;
    public Window window;
    public Model cube;
    public final int WIDTH = 800, HEIGHT = 800;
    public Renderer renderer;

    public void init() {
        SharedLibraryLoader.load();

        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(0.0f, 0, 0);
        window.create();
        renderer = new Renderer(0);
        cube = ModelLoader.loadOBJModel("models/cube.obj");
    }

    public void run() {
        init();
        while (!window.shouldClose()) {
            update();
            render();
        }
        window.destroy();
    }

    private void update() {
        window.update();
    }

    private void render() {
        renderer.renderModel(cube);
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}