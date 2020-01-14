package com.kulartist.texttospeechenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextToSpeech mtts;
    private Button listen;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listen = findViewById(R.id.listenText);
        text=findViewById(R.id.enterText);


        mtts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                int result = mtts.setLanguage(new Locale("en"));

                if(status==TextToSpeech.SUCCESS)
                {
                    int result1 = mtts.setEngineByPackageName("com.svox.classic");
                    if( result1 == TextToSpeech.ERROR ) {
                        Toast.makeText(getApplicationContext(), "Could not load svox", Toast.LENGTH_LONG).show();


                    }
                    listen.setEnabled(true);
                    mtts.setPitch((float) 1.5);
                   // int result = mtts.setLanguage(Locale.ENGLISH);

                    if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(getApplicationContext(), "Lang NOT SUPPORTED", Toast.LENGTH_LONG).show();

                    }
                    else
                        listen.setEnabled(true);

                }
                else {
                    Toast.makeText(getApplicationContext(), "INITIALIZATION FAILED", Toast.LENGTH_LONG).show();
                }

            }
        },"com.svox.classic");


        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });


    }

    public void speak()
    {
        String text1 =text.getText().toString();

        mtts.speak(text1,TextToSpeech.QUEUE_FLUSH,null);

    }
}
