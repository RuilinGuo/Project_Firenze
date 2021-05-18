package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.ROYAL_FLUSH;

public class RoyalFlush implements RankingInterface{
    public boolean isTrue(List<Card> cards) {
        Map<Point, Integer> map = getCardsRankCountMap(cards);
        if (isSameSuit(cards)) {
            return map.containsKey(Point.TEN)
                    && map.containsKey(Point.JACK)
                    && map.containsKey(Point.QUEEN)
                    && map.containsKey(Point.KING)
                    && map.containsKey(Point.ACE);
        }
        return false;
    }

    public Ranking getRanking() {
        return ROYAL_FLUSH;
    }
}
