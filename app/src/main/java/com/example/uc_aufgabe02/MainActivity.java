package com.example.uc_aufgabe02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView equationField;
    private TextView tempEquation;
    private TextView resultField;

    private String currentEquation;
    private String currentTempEquation;
    private String result;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
        initStrings();
    }

    private void initTextView(){
        equationField = (TextView) findViewById(R.id.equasionField);
        tempEquation = (TextView) findViewById(R.id.tempEquation);
        resultField = (TextView) findViewById(R.id.resultField);
    }

    private void initStrings(){
        currentEquation = "";
        currentTempEquation = "";
        result = "";
    }


    private void setResult(String toSet){
        result = toSet;
        currentEquation = "";
        currentTempEquation = "";
        tempEquation.setText("");
        equationField.setText("");
        resultField.setText(result);


    }

    private void addToEqaution(String equationPart){
        try{
            currentEquation += equationPart;
            equationField.setText(currentEquation);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void addToTempEquation(String toAdd, char operator){
        try{

            if(!currentTempEquation.equals("")){
                char tempOperator = currentTempEquation.charAt(currentTempEquation.length()-1);

                double result = calculate(currentEquation, currentTempEquation.substring(0, currentTempEquation.length()-1), tempOperator);
                toAdd = String.valueOf(result);
            }

            currentTempEquation = (toAdd + operator);
            tempEquation.setText(currentTempEquation);
            currentEquation = "";
            equationField.setText(currentEquation);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void setEquation(String toSet){
        currentEquation = toSet;
        equationField.setText(currentEquation);
    }

    private void clearEquation(){
        currentEquation = "";
        currentTempEquation = "";
        result = "";
        resultField.setText("");
        equationField.setText("");
        tempEquation.setText("");
    }

    public void calculateOnClick(View view) {
        if(checkEquationField()){
            if(!checkTempEquationField()){
                addToTempEquation(currentEquation, '+');
            }else{
                double result = calculate(currentEquation, currentTempEquation.substring(0, currentTempEquation.length()-1), currentTempEquation.charAt(currentTempEquation.length()-1));
                if(result % 1 == 0){
                    setResult(String.valueOf((int) result));
                }else{
                    setResult(String.valueOf(result));
                }


            }
        }else{
            if(checkTempEquationField()){
                currentTempEquation = currentTempEquation.substring(0, currentTempEquation.length()-1);
                setResult(currentTempEquation);
            }else{
                return;
            }
        }
    }


    //Numbers
    public void addZero(View view) {
        addToEqaution("0");
    }

    public void addOne(View view) {
        addToEqaution("1");
    }

    public void addTwo(View view) {
        addToEqaution("2");
    }

    public void addThree(View view) {
        addToEqaution("3");
    }

    public void addFour(View view) {
        addToEqaution("4");
    }

    public void addFive(View view) {
        addToEqaution("5");
    }

    public void addSix(View view) {
        addToEqaution("6");
    }

    public void addSeven(View view) {
        addToEqaution("7");
    }

    public void addEight(View view) {
        addToEqaution("8");
    }

    public void addNine(View view) {
        addToEqaution("9");
    }

    //Special

    public void addComma(View view) {
        addToEqaution(".");
    }

    public void addBrackets(View view) {
        addToEqaution("()");
    }

    public void clearOnClick(View view) {
        clearEquation();
    }

    public void changeNegativity(View view) {
        if(currentEquation.equals("")){
            addToEqaution("-");
        }else if(currentEquation.charAt(0) == '-'){
            setEquation(currentEquation.substring(1));

        }else{
            setEquation("-" + currentEquation);

        }
    }

    public void deleteLastChar(View view) {
        if(currentEquation.length() > 0){
            setEquation(currentEquation.substring(0, currentEquation.length()-1));
        }
    }

    public void openSimpleCalc(View view) {
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }

    //Operators

    public void addAddition(View view) {
        addToTempEquation(checkCurrentEquation(), '+');
    }

    public void addSubs(View view) {
        addToTempEquation(checkCurrentEquation(), '-');
    }

    public void addMulti(View view) {
        addToTempEquation(checkCurrentEquation(), '*');
    }

    public void addDevition(View view) {
        addToTempEquation(checkCurrentEquation(), '/');
    }

    //checking Methods

    private String checkCurrentEquation(){

        if(currentEquation == null)
            currentEquation = "";


        if(currentEquation.equals("")){
            if(!result.equals("")){
                return result;
            }else{
                return "0";
            }
        }else{
            return currentEquation;
        }

    }

    private boolean checkEquationField(){

        if(currentEquation == null)
            currentEquation = "";

        if(!currentEquation.equals("")){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkTempEquationField(){
        if(currentEquation == null)
            currentEquation = "";

        if(!currentTempEquation.equals("")){
            return true;
        }else{
            return false;
        }
    }

    private double calculate(String equation, String tempEquation, char operator){

        double firstNumber = Double.parseDouble(tempEquation);
        double secondNumber = Double.parseDouble(equation);

        switch(operator){
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                if(secondNumber == 0){
                    return -1;
                }else{
                    return firstNumber / secondNumber;
                }
        }

        return .0;
    }



}