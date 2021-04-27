package domain;

public class GameManager {

    public static void playerBet(Game game, Player player,Integer betNum){
        if(player.getHoldingChips() < betNum &&
            game.getCurrentBet() > betNum){
            throw new RuntimeException("请正确选择下注金额");
        }
        player.bet(betNum, game.getPlayerCurrentBet(player));
        game.playerBet(betNum);
    }
}
