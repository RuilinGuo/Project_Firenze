package domain.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poker {
    private List<Card> deck;

    public Poker() {
        this.deck = generateDeckOfCards();
    }

    private List<Card> generateDeckOfCards() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Point point : Point.values()) {
                deck.add(new Card(suit, point));
            }
        }
        return deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void exchangeShuffle() {
        for (int i = deck.size() - 1; i > 0; i--) {
            exchange(i, new Random().nextInt(i));
        }
    }

    private void exchange(int indexOfCard1, int indexOfCard2) {
        Card temp;
        temp = deck.get(indexOfCard1);
        deck.set(indexOfCard1,deck.get(indexOfCard2));
        deck.set(indexOfCard2,temp);
    }
}
