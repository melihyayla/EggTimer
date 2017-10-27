package com.melih.eggtimer;

import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    Button testButton;
    SeekBar timer;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timeText.setText("00:30");
        timer.setProgress(30);
        countDownTimer.cancel();
        testButton.setText("Go!");
        timer.setEnabled(true);
        counterIsActive = false;

    }


    public void sendCat(View view){

        ImageView cat = (ImageView) findViewById(R.id.cat);
        ImageView egg = (ImageView) findViewById(R.id.egg);
        timer = (SeekBar) findViewById(R.id.timerSeekBar);
        timeText  = (TextView) findViewById(R.id.timer);
        testButton = (Button) findViewById(R.id.startButton);


        cat.animate().translationYBy(-1500f).setDuration(1000);
        egg.animate().alpha(1f).setDuration(1000);
        timeText.animate().alpha(1f).setDuration(1000);
        timer.animate().alpha(1f).setDuration(1000);
        testButton.animate().alpha(1f).setDuration(1000);

        timer.setMax(600);
        timer.setProgress(30);

        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void startTimer(View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timer.setEnabled(false);
            testButton.setText("Stop");

            countDownTimer = new CountDownTimer(timer.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.crackegg);

                    mplayer.start();

                }
            }.start();

        } else {

            resetTimer();

        }
    }


    public void updateTimer(int time){


        int minutes = (int) (time % 3600) / 60;
        int seconds = time % 60;

        String sec ;

        if (seconds<10){
            sec = "0"+seconds;
        }
        else
            sec = ""+seconds;

        timeText.setText("0" + minutes + " : " +sec);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


/*
    public void  startTimer(View view){

        testButton.setTag(1);
        testButton.setText("Start");
        testButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                final int status =(Integer) v.getTag();
                if(status == 1) {

                    testButton.setText("Pause");
                    v.setTag(0); //pause

                } else {
                    testButton.setText("Start");
                    v.setTag(1); //pause
                }
            }
        });


        new CountDownTimer(timer.getProgress()*1000, 1000) {

            public void onTick(long millisecondsUntilDone) {

                // Coundown is counting down (every second)


                setTimer(((int)millisecondsUntilDone)/1000);

                Log.i("Value", "" +((int)millisecondsUntilDone)/1000);

                timer.setProgress(((int)millisecondsUntilDone)/1000);
            }

            public void onFinish() {

                // Counter is finished! (after 10 seconds)

                Log.i("Done!", "Coundown Timer Finished");

            }
        }.start();

    }

    public void updateTimer(int secondsLeft) {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {

            secondString = "0" + secondString;

        }

        timeText.setText(Integer.toString(minutes) + ":" + secondString);

    }

    */