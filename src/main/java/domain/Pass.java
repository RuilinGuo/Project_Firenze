package domain;

public class Pass implements Action{
    @Override
    public void execute(Game game, Player activePlayer) {
        game.awaiting(activePlayer);
    }
}
