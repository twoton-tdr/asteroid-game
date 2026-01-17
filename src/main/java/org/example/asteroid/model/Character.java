package org.example.asteroid.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import org.example.asteroid.AsteroidApplication;

public abstract class Character {
    private Polygon character;
    private Point2D movement;

    public Character(Polygon poly, int x, int y) {
        character = poly;
        character.setTranslateX(x);
        character.setTranslateY(y);
        movement = new Point2D(0, 0);
    }

    public Polygon getCharecter() {
        return character;
    }

    public void turnleft() {
        character.setRotate(character.getRotate() - 5);
    }

    public void turnRight() {
        character.setRotate(character.getRotate() + 5);
    }

    public void move() {
        character.setTranslateX(character.getTranslateX() + movement.getX());
        character.setTranslateY(character.getTranslateY() + movement.getY());

        if (this.character.getTranslateX() < -20) {
            this.character.setTranslateX(this.character.getTranslateX() + AsteroidApplication.WIDHT);
        }

        if (character.getTranslateX() > AsteroidApplication.WIDHT + 20) {
            character.setTranslateX(this.character.getTranslateX() % AsteroidApplication.WIDHT);
        }

        if (this.character.getTranslateY() > AsteroidApplication.HEIGHT + 20) {
            this.character.setTranslateY(this.character.getTranslateY() % AsteroidApplication.HEIGHT);
        }

        if (this.character.getTranslateY() < -20) {
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidApplication.HEIGHT);
        }
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    public boolean collide(Character other) {
        Shape collisionArea = Shape.intersect(this.character, other.getCharecter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;

    }

    public void accelerate() {
        double angle = Math.toRadians(this.character.getRotate());
        double changeX = Math.cos(angle);
        double changeY = Math.sin(angle);

        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX, changeY);

    }
}
