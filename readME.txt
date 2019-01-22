/******** The University of Melbourne ********/
/******** Project 2 SWEN20003 // Semester 2 2018 ********/
Project Done at Thursday, 11th October, 14:49 PM
Student Name: Gilbert Vincenta
Student ID: 941088
Workshop time: Friday, 9 AM


/*********** Basic specs: ***********/
1 kind of object - 1 class (or subclass) paradigm. 
1 extralife crate<disappearable>. 
1 frog player.
Obstacles: Bus, RaceCar, <reversable> Bike, Bulldozer <solid>.
Rideables: Turtle <disappearable>, Log, LongLog. 
Trees<solid>, water, grasses, 5 goals.


After filling 5 goals, player can go to next level. 
In each level, resets player's state, so life is reset to 3.  
In each level, resets ExtraLife delay time.

P.S.:
1. The term Frog and Player is interchangeable 
2. The term ExtraLife and crate is interchangeable 


Move with up, left, down, and right button.
Press ESC to escape.

Additional features:
screen blinks. Uncomment the code in "SpriteController.java", render() function section to see this feature. 
Levels 0-5, loadable by default. 
Level 0 and 1 were given, and the other levels were added by myself.



Player loses a live when  player hits any Obstacles, OR player hits water, OR player fills the filled goal.
Player gains a live when player hits ExtraLife. 

Please make sure you have "built" the suitable slick library with your OS before running this game. 
Game ends when player has < 0 remaining lives.


Enjoy the game! XOXO