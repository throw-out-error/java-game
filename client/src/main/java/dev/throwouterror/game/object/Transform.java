package dev.throwouterror.game.object;

import dev.throwouterror.util.math.Tensor;

public class Transform {
    private final Tensor position;
    private final Tensor rotation;
    private final Tensor scale;

    public Transform() {
        this(new Tensor(0, 0, 0), new Tensor(0, 0, 0), new Tensor(1, 1, 1));
    }

    public Transform(Tensor position, Tensor rotation, Tensor scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public static Transform pos(double... data) {
        return new Transform(new Tensor(data), new Tensor(0, 0, 0), new Tensor(1, 1, 1));
    }

    public Transform clone() {
        return new Transform(this.position.clone(), this.rotation.clone(), this.scale.clone());
    }

    public Tensor getPosition() {
        return this.position;
    }

    public Tensor getRotation() {
        return this.rotation;
    }

    public Tensor getScale() {
        return this.scale;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}';
    }
}