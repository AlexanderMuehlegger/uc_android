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
    private TextView historyView;
    private EditText numberOne;
    private EditText numberTwo;
    private Spinner operSpinner;

    private View viewForSnackbar;

    private double operandOne;
    private double operandTwo;
    private double result;

    String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initEssentials();
    }

    public void changeToComplex(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initEssentials(){
        resultView = (TextView) findViewById(R.id.result);
        historyView = (TextView) findViewById(R.id.historyView);
        numberOne = (EditText) findViewById(R.id.numberOne);
        numberTwo = (EditText) findViewById(R.id.numberTwo);
        operSpinner = (Spinner) findViewById(R.id.operSpinner);

        viewForSnackbar = historyView.getRootView();

        operandOne = 0;
        operandTwo = 0;
        result = 0;

        history = "";
    }

    private void setResult(){
        resultView.setText(String.valueOf(result));
    }

    private void addToHistory(String toAdd){
        historyView.append(toAdd + "\n");
    }



    private void resetCalc(boolean withHistory){
        if(withHistory && historyView != null){
            historyView.setText("");
        }

        operandOne = 0;
        operandTwo = 0;
        result = 0;

        resultView.setText("");
        numberOne.setText("");
        numberTwo.setText("");
        initEssentials();
    }

    private void resetInputs(){
        numberOne.setText("");
        numberTwo.setText("");
    }

    public void calculateOnClick(View view) {
        if(checkNumbers()){
            char operator = operSpinner.getSelectedItem().toString().charAt(0);

            if(calculate(operator)){
                setResult();
            }else{
                resultView.setText("ERROR");
            }

            resetInputs();

        }
    }

    private boolean calculate(char operator){
        switch(operator){
            case '+':
                result = operandOne + operandTwo;
                addToHistory(operandOne + " + " + operandTwo + " = " + result);
                break;
            case '-':
                result = operandOne - operandTwo;
                addToHistory(operandOne + " - " + operandTwo + " = " + result);
                break;
            case '*':
                result = operandOne * operandTwo;
                addToHistory(operandOne + " * " + operandTwo + " = " + result);
                break;
            case '/':
                if(operandTwo != 0){
                    result = operandOne / operandTwo;
                    addToHistory(operandOne + " / " + operandTwo + " = " + result);
                }else{
                    addToHistory(operandOne + "/" + operandTwo + " = ERROR: Devide by Zero");
                    resetCalc(false);
                    return false;
                }
                break;

            case '^':
                try{
                    result = Math.pow(operandOne, operandTwo);
                    addToHistory(operandOne + " ^ " + operandTwo + " = " + result);
                    break;
                }catch(Exception e){
                    e.printStackTrace();
                    return false;
                }

            case '√':
                try{




                    result = Math.pow(operandOne, 1/operandTwo);
                    addToHistory(operandTwo + " √ " + operandOne + " = " + result);
                    break;
                }catch(Exception e){
                    e.printStackTrace();
                    String information = "";

                    if(operandTwo == 0){
                        information = "0√";
                    }else if(operandOne < 0){
                        information = "Number is not allowed to be negative!";
                    }

                    addToHistory(operandTwo + " √ " + operandOne + " = " + "ERROR: " + information);

                    return false;
                }
        }

        return true;
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
            System.out.println(operandOne);
            System.out.println(operandTwo);
            return true;
        }

    }




    public void resetOnClick(View view) {
        resetCalc(true);
    }
}