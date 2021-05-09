package domain;

public interface Action {
    void execute(Game game, Player activePlayer);
}
