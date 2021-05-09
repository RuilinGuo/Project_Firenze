package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Game {
    private Set<Object> playersTookAction;
    private Round currentRound;
    private LinkedList<Player> awaitingPlayers;
    private Set<Player> activePlayers;
    private int pot;
    private int currentBid;
    private Player[] players;
    private Map<Player, Integer> roundWagers;


    public Game(Player... players) {
        this.players = players;
        this.awaitingPlayers = new LinkedList<>(Arrays.asList(players));
        this.pot = 0;
        this.currentBid = 0;
        this.playersTookAction = new HashSet<>();
        this.currentRound = Round.PREFLOP;
        this.roundWagers = new HashMap<>();
        this.activePlayers = new HashSet<>(Arrays.asList(players));

        Arrays.stream(players).forEach(item -> roundWagers.put(item, 0));
    }

    public Player getActivePlayer() {
        return awaitingPlayers.peek();
    }

    public int getPot() {
        return this.pot;
    }

    public int getCurrentBid() {
        return this.currentBid;
    }

    private void nextRound() {
        if (playersTookAction.size() == players.length &&
                activePlayers.stream().allMatch(player -> getPlayerCurrentWager(player) == currentBid)) {
            currentRound = Round.values()[currentRound.ordinal() + 1];
        }
    }

    public int getMiniWager() {
        return 1;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

    public void awaiting(Player activePlayer) {
        awaitingPlayers.offer(activePlayer);
    }

    public void wage(Player activePlayer, int wager) {
        roundWagers.put(activePlayer, wager);
    }

    public Integer getPlayerCurrentWager(Player activePlayer) {
        return roundWagers.get(activePlayer);
    }

    public void putInPot(int currentBid) {
        this.pot += currentBid;
    }

    public void setCurrentBid(int wager) {
        this.currentBid = wager;
    }

    public void execute(Action action) {
        Player activePlayer = awaitingPlayers.poll();
        action.execute(this, activePlayer);
        playersTookAction.add(activePlayer);
        nextRound();
    }
    public void execute(Action action, Integer wager) {
        Player activePlayer = awaitingPlayers.poll();
        setCurrentBid(wager);
        action.execute(this, activePlayer);
        playersTookAction.add(activePlayer);
        nextRound();
    }

    public void inactive(Player activePlayer) {
        activePlayers.remove(activePlayer);
    }
}
