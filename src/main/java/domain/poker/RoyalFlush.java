package domain.poker;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static domain.poker.Ranking.ONE_PAIR;
import static domain.poker.Ranking.ROYAL_FLUSH;

public class RoyalFlush implements RankingInterface{
    private List<Card> cards;

    @Override
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isTrue() {
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

    public RankingResult getRankingResult() {
        if (Objects.isNull(cards)) {
            return new RankingResult();
        }
        return new RankingResult(cards.get(0), ROYAL_FLUSH, cards);
    }
}
