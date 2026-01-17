package org.example.asteroid.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public abstract class Character {
    private Polygon character;
    private Point2D movement;

    public Character(Polygon poly,int x,int y){
        character = poly;
        character.setTranslateX(x);
        character.setTranslateY(y);
        movement = new Point2D(0, 0);
    }
}
