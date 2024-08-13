package com.example.mediaplayer;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



public class MediaPlayerUI extends Application {

    public void start(Stage window){
        int i = 0;
        Songs songs = new Songs(i);
        BorderPane layout  = new BorderPane();
        songs.add("Travis Scott - Backyard.mp3,file:Rodeo.jpg");
        songs.add("Tzanca Uraganu - Vorba Francezului.mp3,file:Vorba-Francezului.jpg");
        songs.add("Gangsta Rap - Nigga Nigga Nigga.mp3,file:Gangsta-Rap.jpg");

        layout.setCenter(songs.changeImage());
        VBox container = new VBox(5);
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setProgress(0);
        progressBar.setPrefWidth(250);

        HBox buttonsContainer = new HBox(5);
        Button prevButton = new Button("⏮️");
        Button nextButton = new Button("⏭️");
        Button playButton = new Button("▶️");
        buttonsContainer.getChildren().addAll(prevButton,playButton,nextButton);
        buttonsContainer.setAlignment(Pos.CENTER);
        container.getChildren().addAll(progressBar,buttonsContainer);

        playButton.setOnAction((actionEvent -> {
            songs.getMediaPlayer().play();
            if(songs.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                songs.getMediaPlayer().pause();
            }
            songs.getTime(progressBar);


        }));

        nextButton.setOnAction((actionEvent -> {
            songs.modifyIndex("+");
            layout.setCenter(songs.changeImage());
            songs.changeMediaPlayer();
            songs.getMediaPlayer().play();
            songs.getTime(progressBar);
        }
                ));


        prevButton.setOnAction((actionEvent -> {
            songs.modifyIndex("-");
            layout.setCenter(songs.changeImage());
            songs.changeMediaPlayer();
            songs.getMediaPlayer().play();
            songs.getTime(progressBar);
        }));
        

        layout.setBottom(container);
        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();

    }

}
