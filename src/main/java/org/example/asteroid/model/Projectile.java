package org.example.asteroid.model;

import javafx.scene.shape.Polygon;
import org.example.asteroid.AsteroidApplication;

/**
 *
 * @author lachu
 */
public class Projectile extends Character {

    public Projectile(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);

    }

    public void move() {
        super.getCharecter().setTranslateX(super.getCharecter().getTranslateX() + getMovement().getX());
        super.getCharecter().setTranslateY(super.getCharecter().getTranslateY() + getMovement().getY());

    }

    public boolean isOutOfBounds() {
        if (super.getCharecter().getTranslateX() < -20) {
            return true;
        }

        if (super.getCharecter().getTranslateX() > AsteroidApplication.WIDHT + 20) {
            return true;
        }

        if (super.getCharecter().getTranslateY() > AsteroidApplication.HEIGHT + 20) {
            return true;
        }

        if (super.getCharecter().getTranslateY() < -20) {
            return true;
        }
        return false;
    }
}
