package org.example.asteroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.example.asteroid.model.Asteroid;
import org.example.asteroid.model.Projectile;
import org.example.asteroid.model.Ship;


public class AsteroidApplication extends Application {
    public static int WIDHT = 600;
    public static int HEIGHT = 400;
    @Override
    public void start(Stage stage) throws IOException {
        AtomicInteger point = new AtomicInteger();
        AtomicInteger highestPoint = new AtomicInteger();
        highestPoint.set(file.readHighestPoint());

        Pane scrn = new Pane();
        Label highPointLabel = new Label("Highest Point : "+highestPoint.get());
        highPointLabel.setTranslateY(20);
        Label label = new Label("Points scored : 0");
        scrn.setPrefSize(WIDHT, HEIGHT);
        scrn.getChildren().add(label);
        scrn.getChildren().add(highPointLabel);
        Ship ship = new Ship(WIDHT / 2, HEIGHT / 2);

        List<Projectile> projectiles = new ArrayList<>();
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(WIDHT / 3), rnd.nextInt(400));
            asteroids.add(asteroid);
        }

        asteroids.forEach(asteroid -> scrn.getChildren().add(asteroid.getCharecter()));
        scrn.getChildren().add(ship.getCharecter());

        Scene scene = new Scene(scrn);

        Set<KeyCode> pressedKeys = new HashSet<>();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.SPACE) {
                pressedKeys.add(event.getCode());
            }

        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });

        new AnimationTimer() {
            public void handle(long now) {
                if (pressedKeys.contains(KeyCode.LEFT)) {
                    ship.turnleft();
                }
                if (pressedKeys.contains(KeyCode.RIGHT)) {
                    ship.turnRight();
                }
                if (pressedKeys.contains(KeyCode.UP)) {
                    ship.accelerate();

                }
                if (pressedKeys.contains(KeyCode.SPACE) && projectiles.size() < 6) {
                    pressedKeys.remove(KeyCode.SPACE);

                    Point2D noseInScene = ship.getCharecter().localToScene(0,0);

                    Projectile projectile = new Projectile((int) noseInScene.getX(), (int) noseInScene.getY());
                    projectiles.add(projectile);
                    projectile.getCharecter().setRotate(ship.getCharecter().getRotate());

                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    scrn.getChildren().add(projectile.getCharecter());
                }

                projectiles.forEach(Projectile::move);

                ship.move();

                if (asteroids.size() < 3) {
                    for (int i = 0; i < 5; i++) {
                        Random rnd = new Random();
                        Asteroid asteroid = new Asteroid(rnd.nextInt(WIDHT / 3), rnd.nextInt(400));
                        asteroids.add(asteroid);
                        scrn.getChildren().add(asteroid.getCharecter());
                    }


                }
                asteroids.forEach(asteroid -> asteroid.move());

                List<Projectile> projectilesOutOfBound = projectiles.stream().filter(projectile -> projectile.isOutOfBounds()).collect(Collectors.toList());
                List<Projectile> projectilesToRemove = projectiles.stream().filter(projectile -> {
                    List<Asteroid> asteroidsToRemove = asteroids.stream().filter(asteroid -> {
                        if(asteroid.collide(projectile)){

                            label.setText("Points scored: "+point.incrementAndGet());
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    if (asteroidsToRemove.isEmpty()) {
                        return false;
                    }

                    asteroidsToRemove.stream().forEach(collided -> {
                        asteroids.remove(collided);
                        scrn.getChildren().remove(collided.getCharecter());
                    });

                    return true;

                }).collect(Collectors.toList());

                projectilesOutOfBound.forEach(projectile -> {
                    scrn.getChildren().remove(projectile.getCharecter());
                    projectiles.remove(projectile);
                });

                projectilesToRemove.forEach(projectile -> {
                    scrn.getChildren().remove(projectile.getCharecter());
                    projectiles.remove(projectile);
                });

                asteroids.forEach(asteroid -> {
                    if (ship.collide(asteroid)) {
                        if(point.get() > highestPoint.get()){
                            file.writeHighestPoint(point.get());
                        }
                        stop();
                    }
                });
            }
        }.start();
        stage.setOnCloseRequest((event)->{
            if(point.get() > highestPoint.get()){
                file.writeHighestPoint(point.get());
            }
        });
        stage.setScene(scene);
        stage.setTitle("Asteroids!");
        stage.show();
    }
}
