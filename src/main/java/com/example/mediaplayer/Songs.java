package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

import java.util.ArrayList;


public class Songs {
   private ArrayList<String> songs = new ArrayList<>();
   private String mediaPath;
   private String photoPath="";
   private Media media;
   private MediaPlayer mediaPlayer;
   private int index = 0;
   private ProgressBar progressBar = new ProgressBar();

    public Songs(int i){
    this.mediaPath = Paths.get("Travis Scott - Backyard.mp3").toUri().toString();
    this.media = new Media(mediaPath);
    this.mediaPlayer = new MediaPlayer(media);
    this.index = i;
    }

    public void add(String song){
        songs.add(song);
    }

    public void pula(){
        System.out.println(songs.get(1));
    }

    public String getSongName(){
            this.mediaPath = Paths.get(songs.get(this.index).split(",")[0]).toUri().toString();
            return this.mediaPath;
    }

    public void getTime(ProgressBar progressBar){
        progressBar.progressProperty().bind(
                Bindings.createDoubleBinding(()->{
                    Duration current = mediaPlayer.getCurrentTime();
                    Duration totalTime = mediaPlayer.getCycleDuration();
                    return current.toMillis() / totalTime.toMillis();
                },
                        mediaPlayer.currentTimeProperty(),
                        mediaPlayer.cycleCountProperty())
        );
    }

    public ProgressBar getProgressBar(){
        return this.progressBar;
    }

    public String getSongPhoto(){
            this.photoPath = songs.get(this.index).split(",")[1];
            return this.photoPath;

    }

    public ImageView changeImage(){
        Image image = new Image(getSongPhoto(), 250,200, false,false);
        ImageView imageView = new ImageView(image);
        return imageView;
    }


    public void modifyIndex(String operation){
        if(operation.equals("+")){
            if(this.index<this.songs.size()){
                this.index++;
                if(this.index >=this.songs.size()){
                    this.index=0;
                }
            }
            if(this.index >=this.songs.size()){
                this.index=0;
            }
        }else if(operation.equals("-")){
            if(this.index>0){
                this.index --;
            }else if(this.index <= 0){
                this.index = songs.size()-1;
            }
        }
    }

    public int getIndex(){
        return this.index;
    }

    public void changeMediaPlayer(){
        if(this.mediaPlayer !=null){
            this.mediaPlayer.stop();
        }
        this.mediaPath = getSongName();
        this.media = new Media(mediaPath);
        this.mediaPlayer = new MediaPlayer(media);

    }

    public MediaPlayer getMediaPlayer(){

        return this.mediaPlayer;
    }



}
