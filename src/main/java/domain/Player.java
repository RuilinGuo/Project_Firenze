package domain;

import domain.poker.Card;
import domain.poker.Poker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Player {
    private String name;
    private boolean tookAction;
    private List<Card> handCards;

    public Player(String name) {
        this.name = name;
        this.tookAction = false;
        this.handCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void tookAction() {
        this.tookAction = true;
    }

    public boolean isTookAction() {
        return tookAction;
    }

    public void resetAction(){
        this.tookAction = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void drawCards(Poker poker) {
        this.handCards.addAll(poker.drawCards(2));
    }

    public List<Card> getHandCards() {
        return this.handCards;
    }
}
