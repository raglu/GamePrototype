package com.company;

public class GameRules {
    private float time = 300;

    /*private void checkRule1() {
        if (Player.getHealth() <= 0)
            gameOver();
    }
*/
    private void checkRule2() {
        if (time <= 0)
            gameOver();
    }


    private void gameOver() {
    }
}
