package com.example.jialin_feelsbook;

/**
 * in the main activity, user can record a emotion with or without a comment
 * user can also choose to view the count of the emotion,then the grogram
 * will active the showCount activity
 * or to view the history of all emotion record, then the program
 * will active the showHistory activity
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private String selectedEmotion = "None";

    ArrayList<Emotion> emotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();
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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void loveButton(View view){
        this.selectedEmotion = "Love";
        Toast.makeText(this,"Love has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Love");
    }
    public void joyButton(View view){
        this.selectedEmotion = "Joy";
        Toast.makeText(this,"Joy has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Joy");
    }
    public void surpriseButton(View view){
        this.selectedEmotion = "Surprise";
        Toast.makeText(this,"Surprise has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Surprise");
    }
    public void angerButton(View view){
        this.selectedEmotion = "Anger";
        Toast.makeText(this,"Anger has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Anger");
    }
    public void sadnessButton(View view){
        this.selectedEmotion = "Sadness";
        Toast.makeText(this,"Sadness has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Sadness");
    }
    public void fearButton(View view){
        this.selectedEmotion = "Fear";
        Toast.makeText(this,"Fear has been recorded",Toast.LENGTH_LONG).show();
        sendComment("Fear");
    }

    public void sendComment(String emotion){
        EditText text = (EditText) findViewById(R.id.inputComment);
        String commentText = text.getText().toString();
        Emotion newEmotion = new Emotion(commentText,emotion);
        emotionList.add(newEmotion);
        saveInFile();
        text.setText("");
        //adapter.notifyDataSetChanged();
    }

    public void showCount(View view){
        Intent emotion_count = new Intent(this, ShowCount.class);
        startActivity(emotion_count);
    }

    public void showHistory(View view){
        Intent emotion_history = new Intent(this, ShowHistory.class);
        startActivity(emotion_history);
    }

}

