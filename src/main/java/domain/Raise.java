package domain;

public class Raise implements Action{
    @Override
    public void execute(Game game, Player activePlayer) {
        game.putInPot(game.getCurrentBid());
        game.wage(activePlayer, game.getPlayerCurrentWager(activePlayer) + game.getCurrentBid());
        game.awaiting(activePlayer);
        game.resetAllPlayersAction();
    }
}
