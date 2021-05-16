package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.THREE_OF_THE_KIND;

public class ThreeOfTheKind implements RankingInterface {

    @Override
    public boolean isTrue(Map<Point, Integer> map, List<Card> cards) {
        for (Map.Entry<Point, Integer> pointIntegerEntry : map.entrySet()) {
            if (pointIntegerEntry.getValue() == 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Ranking getRanking() {
        return THREE_OF_THE_KIND;
    }
}
