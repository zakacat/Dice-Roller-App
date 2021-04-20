package com.dgl114.improveddiceroller;

import java.util.Random;
//**************************************************************************************************
// Dice.java            Author: Unknown     Modified by: zakacat
//
//This is the model section of the app. This class contains the methods needed to create and alter
//the dice objects used in the MainActivity.java class.
//A die object has three attributes:
//  - mLargestNumber is set (only) with the constructor and holds the value for the max value of the
//      die.
//  - mNumber holds the current showing face value of the die.
//  - mImageId is the id of the corresponding vector drawable that represents mNumber.
//**************************************************************************************************

public class Dice {

    private final int LARGEST_NUMBER;
    private int mNumber;
    private int mImageId;
    private final Random random = new Random();

    //**********************************************************************************************
    //This constructor takes in a number as a parameter to set the max value of the die (i.e.
    // whether it is a d4, d6, d8, d10, d12, or d20) and then assigns that passed value to
    // mLargestNumber. Then setNumber() is called so that when the dice are displayed before
    // rolling, they always show the highest value.
    //**********************************************************************************************
    public Dice(int number) {
    LARGEST_NUMBER = number;
    setNumber(number);
    }

    //**********************************************************************************************
    // roll() generates a random number, with the help of the random class, that is between 1
    //and the largest number of the die (set at instantiation). It then calls the setNumber() method
    //with this newly generated (rolled) number.
    //**********************************************************************************************
    public void roll() { setNumber(random.nextInt(LARGEST_NUMBER) + 1); }

    //**********************************************************************************************
    // setNumber() will take the number in the parameter and match it with the corresponding vector
    //drawable file. This method also updates mImageId attribute of the object to this file.
    //**********************************************************************************************
    public void setNumber(int number) {
        int SMALLEST_NUM = 1;
        if (number >= SMALLEST_NUM && number <= LARGEST_NUMBER) {
            mNumber = number;
            switch (mNumber) {
                case 1:
                    mImageId = R.drawable.dice_1;
                    break;
                case 2:
                    mImageId = R.drawable.dice_2;
                    break;
                case 3:
                    mImageId = R.drawable.dice_3;
                    break;
                case 4:
                    mImageId = R.drawable.dice_4;
                    break;
                case 5:
                    mImageId = R.drawable.dice_5;
                    break;
                case 6:
                    mImageId = R.drawable.dice_6;
                    break;
                case 7:
                    mImageId = R.drawable.dice_7;
                    break;
                case 8:
                    mImageId = R.drawable.dice_8;
                    break;
                case 9:
                    mImageId = R.drawable.dice_9;
                    break;
                case 10:
                    mImageId = R.drawable.dice_10;
                    break;
                case 11:
                    mImageId = R.drawable.dice_11;
                    break;
                case 12:
                    mImageId = R.drawable.dice_12;
                    break;
                case 13:
                    mImageId = R.drawable.dice_13;
                    break;
                case 14:
                    mImageId = R.drawable.dice_14;
                    break;
                case 15:
                    mImageId = R.drawable.dice_15;
                    break;
                case 16:
                    mImageId = R.drawable.dice_16;
                    break;
                case 17:
                    mImageId = R.drawable.dice_17;
                    break;
                case 18:
                    mImageId = R.drawable.dice_18;
                    break;
                case 19:
                    mImageId = R.drawable.dice_19;
                    break;
                case 20:
                    mImageId = R.drawable.dice_20;
                    break;
            }
        }
    }
    //**********************************************************************************************
    //Getters
    //**********************************************************************************************
    public int getNumber() {
        return mNumber;
    }
    public int getImageId() {
        return mImageId;
    }

}
