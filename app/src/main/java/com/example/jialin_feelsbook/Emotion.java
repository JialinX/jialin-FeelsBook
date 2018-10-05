package com.example.jialin_feelsbook;

/**
 * this is the emotion class which record the date of emotion, comment and date
 * all three value can be got and changed
 * toString gives the format value to be show on the screen
 */

import android.icu.text.AlphabeticIndex;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;


public class Emotion {

    private Date date;
    private String comment;
    //private static final Integer max_chars = 100;
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");
    private String emotion;

    Emotion(){
        this.date = new Date();
        this.comment = "";
        this.emotion = "None";
    }
    Emotion(String comment, String emotion){
        this.date = new Date();
        Calendar calendar = Calendar.getInstance();
        //this.date = DateFormat.getDateInstance().format(calendar.getTime());

        this.comment = comment;
        this.emotion = emotion;
    }
    public void setEmotion(String newEmotion){
        this.emotion = newEmotion;
    }
    public void setDate(Date newDate){
        this.date = newDate;
    }
    public Date getDate(){
        return this.date;
    }
    public String getEmotion() {
        return this.emotion;
    }
    public String getComment(){
        return this.comment;
    }
    public void setComment(String newComment){
        this.comment = newComment;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        return "Emotion: "+this.emotion+"\nComment: "+this.comment+"\nTime: "+dateFormat.format(this.date);
    }
}

