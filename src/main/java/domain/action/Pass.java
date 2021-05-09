package domain.action;

import domain.Game;
import domain.Player;
import domain.action.Action;

public class Pass implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        game.awaiting(activePlayer);
    }
}
