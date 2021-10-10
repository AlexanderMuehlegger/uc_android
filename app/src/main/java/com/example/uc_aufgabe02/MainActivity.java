package com.example.uc_aufgabe02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView equationField;
    private TextView tempEquation;
    private TextView resultField;

    private String currentEquation;
    private String currentTempEquation;


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
    }


    private void setResult(String result){
        currentEquation = "";
        currentTempEquation = "";
        tempEquation.setText("");
        equationField.setText("");
        resultField.setText(result);
    }

    private void setEquation(String equationPart){
        try{
            currentEquation += equationPart;
            equationField.setText(currentEquation);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setEquationTemp(String toSet, char operator){
        currentTempEquation += (toSet + operator);
        tempEquation.setText(currentTempEquation);
        currentEquation = "";
        equationField.setText(currentEquation);
    }

    private void clearEquation(){
        currentEquation = "";
        currentTempEquation = "";
        resultField.setText("");
        equationField.setText("");
        tempEquation.setText("");
    }

    public void calculateOnClick(View view) {
        if(checkEquationField()){
            if(!checkTempEquationField()){
                setResult(currentEquation);
            }
        }else{
            if(checkTempEquationField()){
                currentTempEquation = currentTempEquation.substring(0, currentTempEquation.length()-1);
                setResult(currentTempEquation);
            }
        }
    }


    //Numbers
    public void addZero(View view) {
        setEquation("0");
    }

    public void changeNegativity(View view) {

    }
    public void addOne(View view) {
        setEquation("1");
    }

    public void addTwo(View view) {
        setEquation("2");
    }

    public void addThree(View view) {
        setEquation("3");
    }

    public void addFour(View view) {
        setEquation("4");
    }

    public void addFive(View view) {
        setEquation("5");
    }

    public void addSix(View view) {
        setEquation("6");
    }

    public void addSeven(View view) {
        setEquation("7");
    }

    public void addEight(View view) {
        setEquation("8");
    }

    public void addNine(View view) {
        setEquation("9");
    }

    //Special

    public void addComma(View view) {
        setEquation(".");
    }

    public void addBrackets(View view) {
        setEquation("()");
    }

    public void clearOnClick(View view) {
        clearEquation();
    }

    //Operators

    public void addAddition(View view) {
        setEquationTemp(checkCurrentEquation(), '+');
    }

    public void addSubs(View view) {
        setEquationTemp(checkCurrentEquation(), '-');
    }

    public void addMulti(View view) {
        setEquationTemp(checkCurrentEquation(), '*');
    }

    public void addDevition(View view) {
        setEquationTemp(checkCurrentEquation(), '/');
    }

    public void addProcentage(View view) {
        setEquationTemp(checkCurrentEquation(), '%');
    }


    //checking Methods

    private String checkCurrentEquation(){

        if(currentEquation == null)
            currentEquation = "";


        if(currentEquation.equals("")){
            return "0";
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
}