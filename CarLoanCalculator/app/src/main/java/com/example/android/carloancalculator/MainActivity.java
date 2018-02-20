package com.example.android.carloancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView carcost;
    private TextView downpayment;
    private TextView APR;
    private TextView monthlypayment;
    private TextView seekbarlabel;

    private EditText carcostedit;
    private EditText downpaymentedit;
    private EditText apredit;
    private EditText calculationedit;

    private RadioGroup radiogroup1;
    private RadioButton loanbutton;
    private RadioButton leasebutton;

    private Button calculatebutton;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextViews
        seekbarlabel = findViewById(R.id.seekbarlabel);
        downpayment = findViewById(R.id.downpayment);
        APR = findViewById(R.id.APR);
        monthlypayment = findViewById(R.id.monthlypayment);

        //EditTexts
        carcostedit = findViewById(R.id.carcostedit);
        downpaymentedit = findViewById(R.id.downpaymentedit);
        apredit = findViewById(R.id.apredit);
        calculationedit = findViewById(R.id.calculationedit);

        //Radio
        radiogroup1 = findViewById(R.id.radiogroup1);
        loanbutton = findViewById(R.id.loanbutton);
        leasebutton = findViewById(R.id.leasebutton);
        calculatebutton = findViewById(R.id.calculatebutton);


        //Listeners
        carcostedit.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String data = carcostedit.getText().toString();
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
                        seekbarlabel.setText(i+" Months");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        radiogroup1.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        buttonPressed(radioGroup);
                            }
                        }
                );


        loanbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buttonPressed(compoundButton);
            }
        });


    }



    public void buttonPressed (View v) {
        int months = seekBar.getProgress();

        String carcost = carcostedit.getText().toString();
        double carcost2 = Double.parseDouble(carcost);

        String downpayment = downpaymentedit.getText().toString();
        double downpayment2 = Double.parseDouble(downpayment);

        String APR = apredit.getText().toString();
        double APR2 = Double.parseDouble(APR);


        double monthlypayment;
        double loanamount = carcost2-downpayment2;
        double leaseamount = ((carcost2/3)-downpayment2);

        //Interest Rate
        double ir = APR2/100;
        double interestrate = ir/12;

        double numofpayments = seekBar.getProgress();

       // monthlypayment = interestrate*loanamount/(1-Math.pow(1+interestrate,-numofpayments));

        if(loanbutton.isChecked()){

        monthlypayment = (interestrate*loanamount)/(1-Math.pow(1+interestrate,-numofpayments));

        } else {
            monthlypayment =(interestrate*leaseamount)/(1-Math.pow(1+interestrate, -36));
        }

        calculationedit.setText(String.format("($)%.2f", monthlypayment));

    }

}
