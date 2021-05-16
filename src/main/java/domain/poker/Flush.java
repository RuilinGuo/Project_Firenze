package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.FLUSH;

public class Flush implements RankingInterface {

    @Override
    public boolean isTrue(Map<Point, Integer> map, List<Card> cards) {
        return TexasRule.isSameSuit(cards);
    }

    @Override
    public Ranking getRanking() {
        return FLUSH;
    }
}
