package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.ROYAL_FLUSH;

public class RoyalFlush implements RankingInterface{
    public boolean isTrue(Map<Point, Integer> cardsRankCountMap, List<Card> cards) {
        if (isSameSuit(cards)) {
            return cardsRankCountMap.containsKey(Point.TEN)
                    && cardsRankCountMap.containsKey(Point.JACK)
                    && cardsRankCountMap.containsKey(Point.QUEEN)
                    && cardsRankCountMap.containsKey(Point.KING)
                    && cardsRankCountMap.containsKey(Point.ACE);
        }
        return false;
    }

    public Ranking getRanking() {
        return ROYAL_FLUSH;
    }
}
