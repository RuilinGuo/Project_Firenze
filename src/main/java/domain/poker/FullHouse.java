package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.FULL_HOUSE;

public class FullHouse implements RankingInterface {

    @Override
    public boolean isTrue(List<Card> cards) {
        Map<Point, Integer> map = getCardsRankCountMap(cards);
        if (map.size() == 2) {
            for (Map.Entry<Point, Integer> next : map.entrySet()) {
                if (next.getValue() == 2 || next.getValue() == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Ranking getRanking() {
        return FULL_HOUSE;
    }
}
