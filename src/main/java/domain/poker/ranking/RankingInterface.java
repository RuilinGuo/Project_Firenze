package domain.poker.ranking;

import domain.poker.Card;
import domain.poker.Point;
import domain.poker.Suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RankingInterface {
    void setCards(List<Card> cards);
    boolean isTrue();
    RankingResult getRankingResult();

    default boolean isSameSuit(List<Card> cards){
        Suit suit = cards.get(0).getSuit();
        return cards.stream().allMatch(item -> item.getSuit().equals(suit));
    }

    default Map<Point, Integer> getCardsRankCountMap(List<Card> cards) {
        Map<Point, Integer> rankCount = new HashMap<>();

        for (Card card : cards) {
            if (!rankCount.containsKey(card.getPoint())) {
                rankCount.put(card.getPoint(), 1);
            } else {
                rankCount.put(card.getPoint(), rankCount.get(card.getPoint()) + 1);
            }
        }
        return rankCount;
    }
}
