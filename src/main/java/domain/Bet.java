package domain;

public class Bet implements Action{
    @Override
    public void execute(Game game, Player activePlayer) {
        if (game.getCurrentBid() < game.getMiniWager()) {
            game.setCurrentBid(game.getMiniWager());
        }
        game.putInPot(game.getCurrentBid() - game.getPlayerCurrentWager(activePlayer));
        game.wage(activePlayer, game.getCurrentBid());
        game.awaiting(activePlayer);
    }
}
