package domain;

public class Raise {
    public void execute(Game game, Player activePlayer, int wager) {
        game.setCurrentBid(wager);
        game.putInPot(game.getCurrentBid());
        game.wage(activePlayer, game.getPlayerCurrentWager(activePlayer) + game.getCurrentBid());
        game.awaiting(activePlayer);
    }
}
