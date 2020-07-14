package dev.throwouterror.game.client.engine;

import dev.throwouterror.util.math.Tensor;

public class Ray {

    private Tensor direction = new Tensor();
    private Tensor origin = new Tensor();

    public Tensor getDirection() {
        return direction;
    }

    public Ray setDirection(float x, float y, float z) {
        this.direction.setValues(x, y, z);
        return this;
    }

    public Ray setDirection(Tensor direction) {
        this.direction = direction.clone();
        return this;
    }

    public Tensor getOrigin() {
        return origin;
    }

    public Ray setOrigin(float x, float y, float z) {
        this.origin.setValues(x, y, z);
        return this;
    }

    public Ray setOrigin(Tensor origin) {
        this.origin = origin.clone();
        return this;
    }

    @Override
    public String toString() {
        return "(origin: " + origin + " direction: " + direction + ")";
    }

}