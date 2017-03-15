package com.felight.musicplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> songList;
    ArrayList<String> songPath;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = new ArrayList<String >();
        songPath = new ArrayList<String >();
        mp = new MediaPlayer();
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!= null && musicCursor.moveToFirst()){
            do{
                String title = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                songList.add(title);
                String path = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                songPath.add(path);
            }while(musicCursor.moveToNext());
        }

        ArrayAdapter<String> Adapter =new ArrayAdapter<String>(this,R.layout.songelement, songList);

        ListView lv=(ListView)findViewById(R.id.lvSongsList);

        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView =(TextView)view;
                String msg= songPath.get(i).toString();
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();

                if(mp.isPlaying()) {
                    mp.stop();
                }
                try {
                    mp.reset();
                    File filePath ;
                    filePath = new File((songPath.get(i).toString()).toString());
                    FileInputStream is = new FileInputStream(filePath);
                    mp.setDataSource(is.getFD());
                    mp.prepare();
                    is.close();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
