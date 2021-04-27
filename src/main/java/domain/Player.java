package domain;


public class Player {
    private Integer holdingChips;

    public boolean isHoldingChips(){
        return holdingChips > 0;
    }

    public Player(Integer holdingChips) {
        this.holdingChips = holdingChips;
    }
}
