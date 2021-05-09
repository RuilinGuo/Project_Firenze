package domain.action;

import domain.Game;
import domain.Player;

public class Fold implements Action{
    @Override
    public void execute(Game game, Player activePlayer) {
        game.inactive(activePlayer);
    }
}
