package domain.action;

import domain.Game;
import domain.Player;

public interface Action {
    void execute(Game game, Player activePlayer);
}
