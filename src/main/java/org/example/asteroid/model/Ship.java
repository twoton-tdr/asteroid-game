package org.example.asteroid.model;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(
                0,0,
                0,-2,
                -10,-5,
                -15,-5,
                -25,-20,
                -10,-20,
                -20,-25,
                -27.5,-25,
                -30,-30,
                -35,-30,
                -35,-25,
                -40,-22.5,
                -35,-20,
                -35,-10,
                -40,-10,
                -50,-20,
                -50,-10,
                -45,-5,
                -50,-5,
                -55,-10,
                -55,10,
                -50,5,
                -45,5,
                -50,10,
                -50,5,
                -45,5,
                -50,10,
                -50,20,
                -40,10,
                -35,10,
                -35,20,
                -40,22.5,
                -35,25,
                -35,30,
                -30,30,
                -27.5,25,
                -20,25,
                -10,20,
                -25,20,
                -15,5,
                -10,5,
                0,2
        ), x, y);

    }

    public void move() {
        super.move();
        setMovement(getMovement().multiply(0.98));//friction
    }

}
