package domain.poker;

import java.util.List;
import java.util.Map;

public interface RankingInterface {
    boolean isTrue(Map<Point, Integer> map, List<Card> cards);
    Ranking getRanking();

    default boolean isSameSuit(List<Card> cards){
        Suit suit = cards.get(0).getSuit();
        return cards.stream().allMatch(item -> item.getSuit().equals(suit));
    }
}
