package org.example.asteroid.model;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(
                -30, -10, // left corner
                0, 0, // nose (origin)
                -30, 10 // right corner
        ), x, y);

    }

    public void move() {
        super.move();
        setMovement(getMovement().multiply(0.98));//friction
    }

}
