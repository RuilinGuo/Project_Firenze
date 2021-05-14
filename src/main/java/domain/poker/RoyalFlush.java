package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.ROYAL_FLUSH;

public class RoyalFlush {
    public boolean isTrue(TexasRule texasRule) {
        Map<Point, Integer> cardsRankCountMap = texasRule.getCardsRankCountMap();
        List<Card> cards = texasRule.getCards();
        if (texasRule.isSameSuit(cards)) {
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
