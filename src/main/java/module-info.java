module org.example.asteroid {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.asteroid to javafx.fxml;
    exports org.example.asteroid;
}