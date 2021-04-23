package gameDSL;

public class GameRules {

    private final float time = 300;
    private final float startTime;

    private final Game game;

    boolean winCondition = false;

    public GameRules(Game game) {
        this.game = game;
        startTime = System.currentTimeMillis() / 1000L;
    }

    public void checkRules() {
        checkWinCondition1();
        checkRule1();
        checkRule2();
        checkRule3();
        checkRule4();
    }

    private void checkWinCondition1() {
        for (Player player : game.players) {
            if (player.getCurrentRoom() instanceof Northern_tower_level_2 && player.hasItem("key")) {
                winCondition = true;
                game.winGame();
            }
        }
    }

    private void checkRule1() {
        for (Player player : game.players) {
            if (player.getHealth() <= 0)
                game.gameOver();
        }
    }

    private void checkRule2() {
        if (time <= System.currentTimeMillis() / 1000L - startTime)
            game.gameOver();
    }

    private void checkRule3() {
        if (winCondition)
            game.gameOver();
    }

    private void checkRule4() {
        for (Player player : game.players) {
            Weapon weapon = (Weapon) player.getEquipped();
            if (weapon != null)
                if (weapon.getDurability() <= 0)
                    weapon = null;
        }
    }

}
