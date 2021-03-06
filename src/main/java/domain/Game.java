package domain;

import domain.action.Action;
import domain.poker.Card;
import domain.poker.Poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static domain.Round.SHOWDOWN;

public class Game {
    private Round currentRound;
    private Queue<Player> awaitingPlayers;
    private Set<Player> activePlayers;
    private int pot;
    private int currentBid;
    private Player[] players;
    private Map<Player, Integer> roundWagers;
    private List<Card> publicCard;
    private Poker poker;


    public Game(Player... players) {
        this.players = players;
        this.awaitingPlayers = new LinkedList<>(Arrays.asList(players));
        this.pot = 0;
        this.currentBid = 0;
        this.currentRound = Round.PREFLOP;
        this.roundWagers = new HashMap<>();
        this.activePlayers = new HashSet<>(Arrays.asList(players));
        this.publicCard = new ArrayList<>();

        Arrays.stream(players).forEach(item -> roundWagers.put(item, 0));
    }

    public void execute(Action action) {
        Player activePlayer = awaitingPlayers.poll();
        action.execute(this, activePlayer);
        activePlayer.tookAction();
        nextRound();
    }

    public void execute(Action action, Integer wager) {
        Player activePlayer = awaitingPlayers.poll();
        setCurrentBid(wager);
        action.execute(this, activePlayer);
        activePlayer.tookAction();
        nextRound();
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
        if (activePlayers.stream().allMatch(Player::isTookAction) &&
                activePlayers.stream().allMatch(player -> getPlayerCurrentWager(player) == currentBid)) {
            if (currentRound.equals(Round.PREFLOP)) {
                addPublicCards(3);
            } else if (currentRound.equals(SHOWDOWN)) {
                addPublicCards(0);
            }else {
                addPublicCards(1);
            }
            currentRound = Round.values()[currentRound.ordinal() + 1];
            resetAllPlayersAction();
            setCurrentBid(0);
        }
    }

    public void resetAllPlayersAction() {
        activePlayers.forEach(Player::resetAction);
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

    public void inactive(Player activePlayer) {
        activePlayers.remove(activePlayer);
    }

    public void setPoker(Poker poker) {
        this.poker = poker;
    }

    public void dealCardsToAllPlayer() {
        activePlayers.forEach(item -> item.drawCards(this.poker));
    }

    public void addPublicCards(int i) {
        this.publicCard.addAll(poker.drawCards(i));
    }

    public List<Card> getPublicCard() {
        return publicCard;
    }
}
