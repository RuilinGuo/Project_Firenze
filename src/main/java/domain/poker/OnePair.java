package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.ONE_PAIR;

public class OnePair implements RankingInterface {

    @Override
    public boolean isTrue(List<Card> cards) {
        Map<Point, Integer> map = getCardsRankCountMap(cards);
        if (map.size() == 4) {
            for (Map.Entry<Point, Integer> next : map.entrySet()) {
                if (next.getValue() == 2 || next.getValue() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Ranking getRanking() {
        return ONE_PAIR;
    }
}
