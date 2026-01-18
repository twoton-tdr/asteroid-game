module org.example.asteroid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens org.example.asteroid to javafx.fxml;
    exports org.example.asteroid;
}