package domain;


import lombok.Getter;

@Getter
public class Player {
    private Integer holdingChips;

    public boolean isHoldingChips() {
        return holdingChips > 0;
    }

    public Player(Integer holdingChips) {
        this.holdingChips = holdingChips;
    }

    public void bet(Integer betNum, Integer currentBet) {
        this.holdingChips -= betNum - currentBet;
    }

    public void call(Integer bet, Integer currentBet) {
        this.holdingChips -= bet - currentBet;
    }
}
