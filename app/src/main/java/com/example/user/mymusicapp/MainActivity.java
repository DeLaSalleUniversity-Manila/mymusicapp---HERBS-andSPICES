package com.example.user.mymusicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupPlaylist();
    }


    private void setupPlaylist() {
        ListView songs = (ListView) findViewById(R.id.listView);
        files = getResources().getStringArray(R.array.files);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.layout, files);
        songs.setAdapter(adapter);

        songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                Intent intent = new Intent(MainActivity.this, Music.class);
                intent.putExtra("filename", files[index]);
                intent.setAction(Music.ACTION_PLAY);
                startService(intent);

            }
        });

        Button stop=(Button)findViewById(R.id.button);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStop();
            }
        });
    }


    public void onClickStop() {
        Intent intent = new Intent(this, Music.class);
        intent.setAction(Music.ACTION_STOP);
        startService(intent);
    }
}