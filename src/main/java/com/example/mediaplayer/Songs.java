package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.Pair;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Songs {
    private ArrayList<Pair> songs = new ArrayList<>();
    private Pair <String, String> songsPair;
    private String mediaPath;
    private String photoPath = "";
    private Media media;
    private MediaPlayer mediaPlayer;
    private int index = 0;
    private ProgressBar progressBar = new ProgressBar();
    private TextField searchField = new TextField();

    public Songs() {
        this.mediaPath = Paths.get("Travis Scott - Backyard.mp3").toUri().toString();
        this.media = new Media(mediaPath);
        this.mediaPlayer = new MediaPlayer(media);
    }

    public int searchItem() {
        for (int i = 0; i < songs.size(); i++) {
            String searched = searchField.getText();
            if (songs.get(i).getKey().toString().toLowerCase().contains(searched)) {
                this.changeMediaPlayer(i);
                this.getMediaPlayer().play();
                this.getTime(progressBar);
                return i;
            }
        }
        return this.index;
    }

    public TextField getSearchField() {
        return this.searchField;
    }

    public void add(String songPath, String songPhotoPath) {
        Pair<String,String> pair = new Pair<>(songPath,songPhotoPath);
        songs.add(pair);
    }

    public String getSongName(int i) {
        this.mediaPath = Paths.get(songs.get(i).getKey().toString()).toUri().toString();
        return this.mediaPath;
    }

    public String getSongName() {
        this.mediaPath = Paths.get(songs.get(this.index).getKey().toString()).toUri().toString();
        return this.mediaPath;
    }

    public void getTime(ProgressBar progressBar) {
        progressBar.progressProperty().bind(Bindings.createDoubleBinding(() -> {
            Duration current = mediaPlayer.getCurrentTime();
            Duration totalTime = mediaPlayer.getCycleDuration();
            return current.toMillis() / totalTime.toMillis();
        }, mediaPlayer.currentTimeProperty(), mediaPlayer.cycleCountProperty()));
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public String getSongPhoto() {
        this.photoPath = songs.get(this.index).getValue().toString();
        return this.photoPath;

    }

    public String getSongPhoto(int i) {
        this.photoPath = songs.get(i).getValue().toString();
        return this.photoPath;

    }

    public ImageView changeImage(int i) {
        Image image = new Image(getSongPhoto(i), 250, 200, false, false);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public ImageView changeImage() {
        Image image = new Image(getSongPhoto(), 250, 200, false, false);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public void modifyIndex(String operation) {
        if (operation.equals("+")) {
            if (this.index < this.songs.size()) {
                this.index++;
                if (this.index >= this.songs.size()) {
                    this.index = 0;
                }
            }
            if (this.index >= this.songs.size()) {
                this.index = 0;
            }
        } else if (operation.equals("-")) {
            if (this.index > 0) {
                this.index--;
            } else if (this.index <= 0) {
                this.index = songs.size() - 1;
            }
        }
    }

    public void changeMediaPlayer(int i) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.mediaPath = getSongName(i);
        this.media = new Media(mediaPath);
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void changeMediaPlayer() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.mediaPath = getSongName(this.index);
        this.media = new Media(mediaPath);
        this.mediaPlayer = new MediaPlayer(media);
    }

    public MediaPlayer getMediaPlayer() {

        return this.mediaPlayer;
    }


}
