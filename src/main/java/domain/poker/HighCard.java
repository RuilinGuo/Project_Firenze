package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.HIGH_CARD;

public class HighCard implements RankingInterface {

    @Override
    public boolean isTrue(List<Card> cards) {
        Map<Point, Integer> map = getCardsRankCountMap(cards);
        if (map.size() == 5) {
            if (!isSameSuit(cards)) {
                if (map.keySet().size() == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Ranking getRanking() {
        return HIGH_CARD;
    }
}
