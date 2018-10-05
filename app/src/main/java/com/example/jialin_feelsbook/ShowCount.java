package com.example.jialin_feelsbook;

/**
 * for this activity, the program will get all emotion from file
 * then count for each emotion
 * after count all emotions, it will show the output to the screen
 */

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowCount extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    ArrayList<Emotion> emotionList;
    int loveCount = 0;
    int joyCount = 0;
    int surpriseCount = 0;
    int angerCount = 0;
    int sadnessCount = 0;
    int fearCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_count);
        loadFromFile();
        getCount();
        showCount();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotionList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            emotionList = new ArrayList<Emotion>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void getCount(){
        //count the number of each emotion
        for ( int i = 0;i < emotionList.size(); i++ ){
            String eachEmotion = emotionList.get(i).getEmotion();
            switch (eachEmotion){
                case "Love":
                    this.loveCount++;
                    break;
                case "Joy":
                    this.joyCount++;
                    break;
                case "Surprise":
                    this.surpriseCount++;
                    break;
                case "Anger":
                    this.angerCount++;
                    break;
                case "Sadness":
                    this.sadnessCount++;
                    break;
                case "Fear":
                    this.fearCount++;
                    break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void showCount(){
        //change the name of the textView in the layout
        TextView love = (TextView)findViewById(R.id.loveCount);
        love.setText    ("Love:                           "+this.loveCount);
        TextView joy = (TextView)findViewById(R.id.joyCount);
        joy.setText     ("Joy:                              "+this.joyCount);
        TextView surprise = (TextView)findViewById(R.id.surpriseCount);
        surprise.setText("Surprise:                     "+this.surpriseCount);
        TextView anger = (TextView)findViewById(R.id.angerCount);
        anger.setText   ("Anger:                        "+this.angerCount);
        TextView sadness = (TextView)findViewById(R.id.sadnessCount);
        sadness.setText ("Sadness:                    "+this.sadnessCount);
        TextView fear = (TextView)findViewById(R.id.fearCount);
        fear.setText    ("Fear:                           "+this.fearCount);
    }
}
