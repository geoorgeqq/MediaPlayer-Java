module com.example.mediaplayer {
    requires javafx.controls;

    requires java.desktop;
    requires javafx.media;
    requires java.sql;

    opens com.example.mediaplayer to javafx.fxml;
    exports com.example.mediaplayer;
}