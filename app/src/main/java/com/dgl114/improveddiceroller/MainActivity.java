package com.dgl114.improveddiceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//**************************************************************************************************
// MainActivity.java        Author: Unknown     Modified by: zakacat
//
// This class is the controller for the main activity.
// This class include methods for:
// - Inflating the options menu in the toolbar.
// - Receiving gesture input to roll the die.
// - Displaying the dice and altering how many dice are displayed.
// - Sending and receiving intent information to and from PickerActivity.java
// - Updating the counter that counts the face values of the dice.
//**************************************************************************************************
public class MainActivity extends AppCompatActivity {

    public static final int MAX_DICE = 3;
    private int mVisibleDice;
    private Dice[] mDice;
    private ImageView[] mDiceImageViews;
    private Menu mMenu;
    private CountDownTimer mTimer;
    private GestureDetectorCompat mDetector;
    private TextView mCounter;
    private static final int REQUEST_SETTINGS = 0;
    //**********************************************************************************************
    //These variables are to be passed back and forth from MainActivity and PickerActivity.
    //**********************************************************************************************
    private int mDieColor = 0;
    private int mRollDuration = 2000;
    private int mDieValue = 6;

    //**********************************************************************************************
    // onCreate() is always called when the activity starts. This method starts many things:
    // - Sets the view to the layout activity_main.xml
    // - Creates an array of dice objects and instantiates each die.
    // - Creates an array of image view objects and assigns the initial image (6's)
    // - Sets the counter to match the image view in layout.
    // - Creates a Fragment Manager object for the Welcome Dialog.
    // - Creates a WelcomeDialogFragment object from my modified Dialog Fragment class
    //   and shows the dialog (every time the app is started).
    // - Instantiates the Gesture Detector object
    // - Sets the visible dice to the max number of dice (3) and calls the showDice() method.
    // - Calls the updateCounter method.
    //**********************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDice = new Dice[MAX_DICE];
        mDice[0] = new Dice(6);
        mDice[1] = new Dice(6);
        mDice[2] = new Dice(6);

        mDiceImageViews = new ImageView[MAX_DICE];
        mDiceImageViews[0] = findViewById(R.id.dice1);
        mDiceImageViews[1] = findViewById(R.id.dice2);
        mDiceImageViews[2] = findViewById(R.id.dice3);

        mCounter = findViewById(R.id.counter);

        FragmentManager manager = getSupportFragmentManager();
        WelcomeDialogFragment dialog = new WelcomeDialogFragment();
        dialog.show(manager, "Some_string");

        mDetector = new GestureDetectorCompat(this, new DiceGestureListener());

        mVisibleDice = MAX_DICE;
        showDice();
        updateCounter();
    }
    //**********************************************************************************************
    // onFabClick() responds to the user clicking on the fab button. It sets the values of dieColor,
    // rollDuration, dieValue of this class to the appropriate string key (set in PickerActivity),
    // and then opens PickerActivity with this info.
    //**********************************************************************************************
    public void onFabClick(View view){
        Intent intent = new Intent(this, PickerActivity.class);
        intent.putExtra(PickerActivity.DIE_COLOR, mDieColor);
        intent.putExtra(PickerActivity.ROLL_DURATION, mRollDuration);
        intent.putExtra(PickerActivity.DIE_VALUE, mDieValue);
        startActivityForResult(intent, REQUEST_SETTINGS);
    }
    //**********************************************************************************************
    // onActivityResult() is called when the return button is clicked in the PickerActivity. It
    // receives the information for dieColor, rollDuration, and dieValue, and assigns it
    // appropriately. It then calls showDice() and updateCounter().
    //**********************************************************************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_SETTINGS) {
            mDieColor = data.getIntExtra(PickerActivity.DIE_COLOR,0);
            if(mDieColor == 0){
                for (int i = 0; i < MAX_DICE; i ++) {
                    mDiceImageViews[i].setColorFilter(ContextCompat.getColor(this, R.color.colorDice));
                }
            }
            else if(mDieColor == 1){
                for (int i = 0; i < MAX_DICE; i ++) {
                    mDiceImageViews[i].setColorFilter(ContextCompat.getColor(this, R.color.orangeDice));
                }
            }
            else{
                for (int i = 0; i < MAX_DICE; i ++) {
                    mDiceImageViews[i].setColorFilter(ContextCompat.getColor(this, R.color.yellowDice));
                }
            }

        }
            mRollDuration = data.getIntExtra(PickerActivity.ROLL_DURATION,2000);
            mDieValue = data.getIntExtra(PickerActivity.DIE_VALUE,6);
            for (int i = 0; i < MAX_DICE; i ++) {
                mDice[i] = new Dice(mDieValue);
            }
            showDice();
            updateCounter();
    }
    //**********************************************************************************************
    //onCreateOptionsMenu appears to be called whenever the activity is opened. It generates the
    //toolbar menu with appbar_menu layout file.
    //**********************************************************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }
    //**********************************************************************************************
    // onOptionsItemSelected() handles the options in the toolbar and overflow menu
    //**********************************************************************************************
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_one:
                changeDiceVisibility(1);
                showDice();
                return true;

            case R.id.action_two:
                changeDiceVisibility(2);
                showDice();
                return true;

            case R.id.action_three:
                changeDiceVisibility(3);
                showDice();
                return true;

            case R.id.action_stop:
                mTimer.cancel();
                item.setVisible(false);
                return true;

            case R.id.action_roll:
                rollDice();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //**********************************************************************************************
    // onTouchEvent() receives a motion event and applies it to the overridden gesture methods
    // in the internal DiceGestureListener class.
    //**********************************************************************************************
    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
       private class DiceGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            rollDice();
            return true;
        }
    }
    //**********************************************************************************************
    // rollDice() first sets the stop option in the toolbar to visible. If the timer is still going
    // for whatever reason, it stops it. Then it reads the current roll duration and rolls the die,
    // displaying the random output every 100 ms. When the timer is done rolling, the stop button
    // is hidden.
    //**********************************************************************************************
    private void rollDice() {
        mMenu.findItem(R.id.action_stop).setVisible(true);

        if (mTimer != null) {
            mTimer.cancel();
        }

        mTimer = new CountDownTimer(mRollDuration, 100) {
            public void onTick(long millisUntilFinished) {
                for (int i = 0; i < mVisibleDice; i++) {
                    mDice[i].roll();
                }
                showDice();
                updateCounter();
            }

            public void onFinish() {
                mMenu.findItem(R.id.action_stop).setVisible(false);
            }
        }.start();
    }
    //**********************************************************************************************
    // changeDiceVisibility() reads the number of visible dice, assigns that to mVisibleDice, and
    // then set the visibility of the image views according to the parameter.
    //**********************************************************************************************
    private void changeDiceVisibility(int numVisible) {
        mVisibleDice = numVisible;

        for (int i = 0; i < numVisible; i++) {
            mDiceImageViews[i].setVisibility(View.VISIBLE);
        }

        for (int i = numVisible; i < MAX_DICE; i++) {
            mDiceImageViews[i].setVisibility(View.GONE);
        }
    }
    //**********************************************************************************************
    // showDice() finds the imageId of the dice objects, retrieves the vector drawable from that iD
    // and then assigns it to the corresponding image views.
    //**********************************************************************************************
    private void showDice() {

        for (int i = 0; i < mVisibleDice; i++) {
            Drawable diceDrawable = ResourcesCompat.getDrawable(getResources(), mDice[i].getImageId(), getApplicationContext().getTheme());
            mDiceImageViews[i].setImageDrawable(diceDrawable);
            mDiceImageViews[i].setContentDescription(Integer.toString(mDice[i].getNumber()));
        }
    }

    //**********************************************************************************************
    // updateCounter() adds the face values of the dice objects to a total sum and then updates
    // that sum to the counter text view.
    //**********************************************************************************************
    public void updateCounter(){
        int localInt = 0;

        for (int i = 0; i < mVisibleDice; i++){
            localInt += mDice[i].getNumber();
        }
        String localText = String.valueOf(localInt);
        mCounter.setText(localText);
    }
}
