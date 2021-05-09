package domain;

public class Fold implements Action{
    @Override
    public void execute(Game game, Player activePlayer) {
        game.inactive(activePlayer);
    }
}
