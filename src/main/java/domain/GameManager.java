package domain;

public class GameManager {

    public static void playerBet(Game game, Integer betNum){
        if(game.getCurrentPlayer().getHoldingChips() < betNum){
            throw new RuntimeException("请正确选择下注金额");
        }
        game.getCurrentPlayer().bet(betNum);
        game.playerBet(betNum);
    }
}
