package com.example.android.tipcalculator;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView barLabel;
    private TextView title;
    private TextView outputView;
    private TextView Tipamount;
    private TextView amountOwed;

    private SeekBar seekBar2;

    private EditText input;
    private EditText splitamount;
    private EditText billInput;
    private EditText numdiners;

    private LinearLayout inputLayout;
    private LinearLayout layout2;

    private Button tipButton;

    private CheckBox split;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barLabel = findViewById(R.id.barLabel);
        input = findViewById(R.id.billInput);
        splitamount = findViewById(R.id.numdiners);

        split = findViewById(R.id.split);


        input.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String data = input.getText().toString();
                        int progress = Integer.parseInt(data);
                        seekBar2.setProgress(progress);
                        return false;
                    }
                }
        );

        seekBar2 = findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        barLabel.setText(i+"");
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




    public void buttonPressed (View v) {
        int tippercent = seekBar2.getProgress();
        String numofdiners = numdiners.getText().toString();
        double numofdiners2 = Double.parseDouble(numofdiners);

        String billamount = billInput.getText().toString();
        double billamount2 = Double.parseDouble(billamount);

        double tipamount;
        double totalbill;
        double splitbillamount;

        tipamount = billamount2 * tippercent;
        totalbill = billamount2 + tipamount;
        splitbillamount = billamount2 / numofdiners2;

        Tipamount.setText("Tip: $"+String.format("%.2f", tipamount)+"");
        amountOwed.setText("Amount owed: $"+String.format("%.2f", totalbill)+"");




    }


}
