package com.example.jialin_feelsbook;

/**
 * for the activity, the program let the user change any component of the emotion
 * after the user apply the changes, it will change the emotion record
 * after the user delete the emotion record, the selected emotion will be deleted from the history
 * whenever user apply or delete emotions, the program will activity main activity
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ViewAndEdit extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    ArrayList<Emotion> emotionList;
    Emotion emotion;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_edit);
        Intent intent = getIntent();
        this.position = intent.getIntExtra("position",0);
        loadFromFile();
        this.emotion = emotionList.get(this.position);
        loadOldInfo();
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

    @SuppressLint("SetTextI18n")
    private void loadOldInfo(){

        String oldEmotion = this.emotion.getEmotion();
        String oldComment = this.emotion.getComment();
        Date oldDate = this.emotion.getDate();
        TextView emotionPromote = (TextView)findViewById(R.id.oldEmotion);
        emotionPromote.setText("Old Emotion: "+oldEmotion);

        TextView commentPromote = (TextView)findViewById(R.id.oldComment);
        commentPromote.setText("Old Comment: "+oldComment);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        TextView datePromote = (TextView)findViewById(R.id.oldDate);
        datePromote.setText("Old Date: "+dateFormat.format(oldDate));
    }

    public void updateEmotionLove(View view){
        this.emotion.setEmotion("Love");
        Toast.makeText(this,"Emotion changed to love",Toast.LENGTH_LONG).show();
    }
    public void updateEmotionJoy(View view){
        this.emotion.setEmotion("Joy");
        Toast.makeText(this,"Emotion changed to Joy",Toast.LENGTH_LONG).show();
    }
    public void updateEmotionSurprise(View view){
        this.emotion.setEmotion("Surprise");
        Toast.makeText(this,"Emotion changed to Surprise",Toast.LENGTH_LONG).show();
    }
    public void updateEmotionAnger(View view){
        this.emotion.setEmotion("Anger");
        Toast.makeText(this,"Emotion changed to Anger",Toast.LENGTH_LONG).show();
    }
    public void updateEmotionSadness(View view){
        this.emotion.setEmotion("Sadness");
        Toast.makeText(this,"Emotion changed to Sadness",Toast.LENGTH_LONG).show();
    }
    public void updateEmotionFear(View view){
        this.emotion.setEmotion("Fear");
        Toast.makeText(this,"Emotion changed to Fear",Toast.LENGTH_LONG).show();
    }
    public void updateComment(View view){
        EditText text = (EditText) findViewById(R.id.newComment);
        String newComment = text.getText().toString();
        this.emotion.setComment(newComment);
        Toast.makeText(this,"Comment changed",Toast.LENGTH_LONG).show();
    }
    public void updateDate(View view){
        EditText text = (EditText) findViewById(R.id.newDate);
        String newDate = text.getText().toString();

        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        try {
            Date date = format.parse(newDate);
            this.emotion.setDate(date);
            Toast.makeText(this,"Date changed",Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            //e.printStackTrace();
            Toast.makeText(this,"Invalid Date, please correct your date",Toast.LENGTH_LONG).show();
        }
    }
    public void applyChange(View view){
        emotionList.set(this.position,this.emotion);
        saveInFile();
        Toast.makeText(this,"Applied changes",Toast.LENGTH_LONG).show();
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
    public void deleteEmotion(View view){
        emotionList.remove(this.emotion);
        saveInFile();
        Toast.makeText(this,"Deleted this emotion",Toast.LENGTH_LONG).show();
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

}
