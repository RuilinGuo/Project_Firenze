package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.FLUSH;

public class Flush implements RankingInterface {

    @Override
    public boolean isTrue(List<Card> cards) {
        return isSameSuit(cards);
    }

    @Override
    public Ranking getRanking() {
        return FLUSH;
    }
}
