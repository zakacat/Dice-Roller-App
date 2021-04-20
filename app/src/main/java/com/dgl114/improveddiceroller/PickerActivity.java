package com.dgl114.improveddiceroller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//**************************************************************************************************
// PickerActivity.java          Author: zakacat
//
// This class in the controller for the picker activity (aka "Dice Settings").
// This class includes methods for:
// - Reading the current values of the settings.
// - Reading the radio buttons and image views and setting corresponding values.
// - Returning to main activity with info via intents
//
//* There are soft warnings in this file for duplicating code in the default case of my switch
// statements, but best practice dictates to always have a default so I am keepin' 'em.
//**************************************************************************************************
public class PickerActivity extends AppCompatActivity {

    private int mDieColor = 0; //0= red, 1 = orange, 2 = yellow
    private int mRollDuration = 2000; //1000= short, 2000= medium, long= 3000
    private int mDieValue = 6; //4 = 4, 6 = 6, 8 = 8, 10 = 10, 12 = 12, 20 = 20

    public static final String DIE_COLOR = "super secret color code";
    public static final String ROLL_DURATION = "super secret roll code";
    public static final String DIE_VALUE = "super secret die value";

    public RadioButton redRadio, orangeRadio, yellowRadio;
    public RadioButton roll1000, roll2000, roll3000;
    public final int MAX_OPTIONS = 6;
    public ImageView [] dieOptions = new ImageView[MAX_OPTIONS];

    //**********************************************************************************************
    // onCreate() reads all the info from the intent and assigns it to the corresponding variable.
    // It then assigns the layout objects to the java objects and calls setActivity().
    //**********************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        Intent intent = getIntent();
        mDieColor = intent.getIntExtra(DIE_COLOR, 0);
        mRollDuration = intent.getIntExtra(ROLL_DURATION, 2000);
        mDieValue = intent.getIntExtra(DIE_VALUE, 6);

        redRadio = findViewById(R.id.redRadio);
        orangeRadio = findViewById(R.id.orangeRadio);
        yellowRadio = findViewById(R.id.yellowRadio);

        roll1000 = findViewById(R.id.roll1000);
        roll2000 = findViewById(R.id.roll2000);
        roll3000 = findViewById(R.id.roll3000);

        dieOptions[0] = findViewById(R.id.die4);
        dieOptions[1] = findViewById(R.id.die6);
        dieOptions[2] = findViewById(R.id.die8);
        dieOptions[3] = findViewById(R.id.die10);
        dieOptions[4] = findViewById(R.id.die12);
        dieOptions[5] = findViewById(R.id.die20);

        setActivity();

    }
    //**********************************************************************************************
    // onColorRadioClick() reads the radio buttons in the color group, assigns the value to
    //mDieColor and then calls setActivity().
    //**********************************************************************************************
    @SuppressLint("NonConstantResourceId")
    public void onColorRadioClick(View view){
    switch(view.getId()){
        case R.id.redRadio:
            mDieColor = 0;
            break;
        case R.id.orangeRadio:
            mDieColor = 1;
            break;
        case R.id.yellowRadio:
            mDieColor = 2;
            break;
        default:
            mDieColor = 0;
        }
        setActivity();
    }
    //**********************************************************************************************
    // onRollRadioClick() reads the radio buttons in the roll duration group, assigns the value to
    // mRollDuration and then calls setActivity().
    //**********************************************************************************************
    @SuppressLint("NonConstantResourceId")
    public void onRollRadioClick(View view) {
        switch(view.getId()){
            case R.id.roll1000:
                mRollDuration = 1000;
                break;
            case R.id.roll2000:
                mRollDuration = 2000;
                break;
            case R.id.roll3000:
                mRollDuration = 3000;
                break;
            default:
                mRollDuration = 2000;
        }
        setActivity();

    }
    //**********************************************************************************************
    // onDiceValueClick() reads the image views in the die grid, assigns the die value to
    // mDieValue and then calls setActivity().
    //**********************************************************************************************
    @SuppressLint("NonConstantResourceId")
    public void onDieValueClick(View view) {
        switch(view.getId()){
            case R.id.die4:
                mDieValue = 4;
                break;
            case R.id.die6:
                mDieValue = 6;
                break;
            case R.id.die8:
                mDieValue = 8;
                break;
            case R.id.die10:
                mDieValue = 10;
                break;
            case R.id.die12:
                mDieValue = 12;
                break;
            case R.id.die20:
                mDieValue = 20;
                break;
            default:
                mDieValue = 6;
        }
        setActivity();
    }
    //**********************************************************************************************
    // onReturnClick() stores settings info and finishes the activity, which calls onActivityResult()
    //in MainActivity.
    //**********************************************************************************************
    public void onReturnClick(View view){

        Intent intent = new Intent();
        intent.putExtra(DIE_COLOR, mDieColor);
        intent.putExtra(ROLL_DURATION, mRollDuration);
        intent.putExtra(DIE_VALUE, mDieValue);
        setResult(RESULT_OK, intent);
        finish();
    }
    //**********************************************************************************************
    // setActivity() is called when anything happens and updates the view fields with the current
    // information.
    //**********************************************************************************************
    public void setActivity(){
    switch(mDieColor){
        case 0:
            redRadio.toggle();
            for(int i = 0; i < MAX_OPTIONS; i ++){
                dieOptions[i].setColorFilter(ContextCompat.getColor(this, R.color.colorDice));
            }
            break;
        case 1:
            orangeRadio.toggle();
            for(int i = 0; i < MAX_OPTIONS; i ++){
                dieOptions[i].setColorFilter(ContextCompat.getColor(this, R.color.orangeDice));
            }
            break;
        case 2:
            yellowRadio.toggle();
            for(int i = 0; i < MAX_OPTIONS; i ++){
                dieOptions[i].setColorFilter(ContextCompat.getColor(this, R.color.yellowDice));
            }
            break;
        default :
            redRadio.toggle();
    }
    switch(mRollDuration){
        case 1000:
            roll1000.toggle();
            break;
        case 2000:
            roll2000.toggle();
            break;
        case 3000:
            roll3000.toggle();
            break;
        default:
            roll2000.toggle();
    }
    for(int i = 0; i < MAX_OPTIONS; i ++){
                dieOptions[i].setBackgroundColor(ContextCompat.getColor(this, R.color.burgundy));
            }

    switch(mDieValue){
        case 4:
            dieOptions[0].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        case 6:
            dieOptions[1].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        case 8:
            dieOptions[2].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        case 10:
            dieOptions[3].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        case 12:
            dieOptions[4].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        case 20:
            dieOptions[5].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            break;
        default :
            dieOptions[1].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
    }



    }
}
