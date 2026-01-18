package org.example.asteroid.model;

import java.util.Random;
import javafx.scene.shape.Polygon;

/**
 *
 * @author lachu
 */
public class Asteroid extends Character {

    private double rotationalMovement;

    public Asteroid(int x, int y) {
        super(new AsteroidFactory().createPolygon(), x, y);

        Random rnd = new Random();

        super.getCharecter().setRotate(rnd.nextInt(360));


        //Decides the speed
        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - new Random().nextDouble();

    }

    public void move() {
        super.move();

        super.getCharecter().setRotate(super.getCharecter().getRotate() + rotationalMovement);

    }
}
