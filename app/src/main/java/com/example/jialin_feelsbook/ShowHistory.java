package com.example.jialin_feelsbook;

/**
 * for this activity, the program show all the history to the screen
 * when user select any history, it will activity viewAndEdit acttivity
 */

import android.content.Context;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SimpleTimeZone;

public class ShowHistory extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView History;
    ArrayList<Emotion> emotionList;
    ArrayAdapter<Emotion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        sortEmotions();
        saveInFile();
        //reload for new sorted emotionList
        loadFromFile();
        showHistory();

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

    //show all the history to the screen
    private void showHistory(){
        adapter = new ArrayAdapter<Emotion>(this,R.layout.list_item ,emotionList );
        History = (ListView)findViewById(R.id.commentHistory);
        History.setAdapter(adapter);
        History.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Intent viewAndEdit = new Intent(ShowHistory.this, ViewAndEdit.class);
                viewAndEdit.putExtra("position",position);
                startActivity(viewAndEdit);
            }
        });
    }
    public void sortEmotions(){
        Collections.sort(emotionList, new Comparator<Emotion>() {
            public int compare(Emotion firstEmotion, Emotion secondEmotion) {
                return firstEmotion.getDate().compareTo(secondEmotion.getDate());
            }
        });
    }

    public void clearAll(View view){
        emotionList = new ArrayList<Emotion>();
        saveInFile();
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

}
