package com.example.android.seekbarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView barLabel;
    private SeekBar seekBar;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barLabel = findViewById(R.id.barLabel);
        input = findViewById(R.id.input);
        input.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String data = input.getText().toString();
                        int progress = Integer.parseInt(data);
                        seekBar.setProgress(progress);
                        return false;
                    }
                }
        );
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        barLabel.setText(i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void buttonPressed(View v) {
        int progress = seekBar.getProgress();
        Toast.makeText(this, "Progress"+progress, Toast.LENGTH_SHORT).show();

    }
}
