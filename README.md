# **Deckard Cain's Dice Rolling App**
## **Purpose**

This is the source code necessary to make a dice rolling app with android studio for android phones. This was an assignment for a college course DGL-114 Intro to App Development. The purpose of this assignment was to familiarize the student ("Hey, that's me.") with:

- Developing apps with multiple activities
- Using Intents to transfer data to and from activities
- Recognizing and reacting to user gestures
- More practice with scaling in XML
- More practice with MVC and java programming

and personally:

- Creating functional dialogs
- Working with fragments

## **About The App**

As the dice values that were presented to us via the assignment were also common values used in Dungeons and Dragons, I attempted to make the app have a D&D theme. I have been a dungeon master on a handful of occasions and the handle I use as DM is Deckard Cain. There also just so happens to be a remaster of Diablo 2 coming out, so the naming and design of this app was actually not just a roll of the dice.

This app presents the user with virtual dice that they can roll. The total of the face values is displayed at the bottom left corner of the screen. There are options to change various aspects of dice and the rolling process. There are options to change the amount of dice visible (from 1 - 3). The user can roll the dice using a button or a fling gesture. The user can also change the color of the dice, set the duration for the roll, and change the total die value. Most of this information is provided in the welcome dialog.

## **Contributors**
The initial set of files was provided by the instructor of the course. Most of the files were taken from the text with our instructions being - to improve and modify the app. Outside of the initial file set, I worked solo, but several modifications were still made.

## **Modifications**

Dice.java
- It was necessary to expand the constructor of this class to set the value of the die.
- I removed the add one and subtract one methods as they did not seem to have much utility.

MainActivity.java
- I removed the context menu on long click as not only did it interfere with the gesture listener, it also displayed features that I no longer wanted (add one/ subtract one) and there were already two other superior ways of rolling the dice.

PickerActivity.java was all of my own design and I felt it exceeded the assignment requirements.

I made several other conscious decisions to improve the app in my opinion.

- I decided on an appealing theme that carried throughout the entire app.
- I introduced a welcome dialog to explain the workings of the app.
- I used a FAB button for settings because I figured that if someone were to use the app for D&D that they would want to change the dice values quickly. And the return button was placed in the same location for ease of use. I also intentionally removed the up arrow from the PickerActivity.
- I did not bother with landscape mode for such a short use app.
- I did not bother with save state, onPause, onResume, etc... as the app is short and sweet and full of random rolls. There is not much benefit to remembering settings.

## **License**
 Currently none.
