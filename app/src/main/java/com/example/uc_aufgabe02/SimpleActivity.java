package com.example.uc_aufgabe02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SimpleActivity extends AppCompatActivity {

    private TextView resultView;
    private EditText numberOne;
    private EditText numberTwo;
    private Spinner operSpinner;

    private double operandOne;
    private double operandTwo;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    public void changeToComplex(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        initEssentials();
    }

    private void initEssentials(){
        resultView = (TextView) findViewById(R.id.result);
        numberOne = (EditText) findViewById(R.id.numberOne);
        numberTwo = (EditText) findViewById(R.id.numberTwo);
        operSpinner = (Spinner) findViewById(R.id.operSpinner);
        operandOne = 0;
        operandTwo = 0;
        result = 0;
    }

    public void calculateOnClick(View view) {
        if(checkNumbers()){
            char operator = operSpinner.getSelectedItem().toString().charAt(0);

            switch(operator){
                case '+':
                    result = operandOne + operandTwo;
                case '-':
                    result = operandOne - operandTwo;
                case '*':
                    result = operandOne * operandTwo;
                case '/':
                    if(operandTwo != 0){
                        result = operandOne / operandTwo;
                    }else{
                        Snackbar.make(view,"Division durch 0 nicht möglich", Snackbar.LENGTH_LONG).show();
                    }

            }
        }
    }


    //checking Methods
    private boolean checkNumbers(){
        String textOne = numberOne.getText().toString();
        String textTwo = numberTwo.getText().toString();

        if((textOne == null || textTwo == null) && (!textOne.equals("") || !textOne.equals(""))){
            return false;
        }else{
            operandOne = Double.parseDouble(textOne);
            operandTwo = Double.parseDouble(textTwo);
            return true;
        }

    }



}