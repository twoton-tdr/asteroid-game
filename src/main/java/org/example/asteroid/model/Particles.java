package org.example.asteroid.model;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Particles{
    public Particles(Pane root, Polygon charecter) throws InterruptedException {
        double x = charecter.getTranslateX();
        double y = charecter.getTranslateY();

        Random rnd = new Random();
        List<Asteroid> particles = new ArrayList<>();

        int noOfParticles = rnd.nextInt(3)+2;
        for(int i = 0 ; i < noOfParticles ; i++ ){
            Asteroid particle = new Asteroid((int)x,(int)y,true);
            particles.add(particle);
            particle.move();
            root.getChildren().add(particle.getCharecter());
        }

        new AnimationTimer(){
            private long startTime = -1;
            public void handle(long now){
                if(startTime < 0){
                    startTime = now;
                }
                if(now - startTime >= 3_000_000_000L){
                    this.stop();
                    for (Asteroid particle : particles) {
                        root.getChildren().remove(particle.getCharecter());
                    }
                    return;
                }
                for(Asteroid particle : particles){
                    particle.move();
                    particle.getCharecter().setRotate(rnd.nextDouble(15));
                }
            }
        }.start();
    }
}
