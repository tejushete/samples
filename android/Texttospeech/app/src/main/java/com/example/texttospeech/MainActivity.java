package com.example.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t2s;
    Boolean isTextToSpeechInitialized;
    TextView tvSpeechToText;
    Boolean isQstnAsked;

    HashMap<String,String>qnsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button speechtoText = (Button)findViewById(R.id.speechToText);
        Button btnSpeak = (Button)findViewById(R.id.btnSpeak);
        Button btnAskQuestion = (Button)findViewById(R.id.btnAskQstn);

        tvSpeechToText = (TextView)findViewById(R.id.tvSpeechToText);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                EditText etSpeech = (EditText)findViewById(R.id.etSpeakText);
                String speech = etSpeech.getText().toString();
                if(speech == null || speech.length()==0) return;

                if(isTextToSpeechInitialized == false){
                    Toast.makeText(MainActivity.this, "Text to Speech module is not initialized.", Toast.LENGTH_LONG).show();
                    return;
                }

                t2s.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        speechtoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isQstnAsked=false;
                promptSpeechInput();
            }
        });
        btnAskQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isQstnAsked = true;
                promptSpeechInput();
            }
        });
        t2s = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
           if(status != TextToSpeech.ERROR){
               isTextToSpeechInitialized = true;
               t2s.setLanguage(Locale.UK);
           }
            }
        });

        qnsMap=new HashMap<String, String>();
        qnsMap.put("what is your name","Tejashree");
        qnsMap.put("what is my name","You should know that");
        qnsMap.put("I love you","Thank you");
        qnsMap.put("how old are you", "You very well know how old I am.");
        qnsMap.put("do you love me", "yes Off course");
        qnsMap.put("where am I","I dont tell about locations");
        qnsMap.put("what is current temperature","I dont know about whether");
    }

    private final int REQ_CODE_SPEECH_INPUT = 100;

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something:");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "speech not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.d("TAG", result.get(0));
                    if(isQstnAsked==false) {
                        tvSpeechToText.setText("You Spoke:" + result.get(0));
                    }else{
                        String qns = result.get(0);
                        String ans = qnsMap.get(qns);
                        if(ans == null || ans.length()==0){
                            ans = "Your question is out of scope for me.";
                        }
                        Log.d("TAG", "ans:"+ans);
                        t2s.speak(ans, TextToSpeech.QUEUE_FLUSH,null,null);
                    }
                }
                break;
            }

        }
    }
}
