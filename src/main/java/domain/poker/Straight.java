package domain.poker;

import java.util.List;
import java.util.Map;

import static domain.poker.Ranking.STRAIGHT;

public class Straight implements RankingInterface {

    @Override
    public boolean isTrue(Map<Point, Integer> map, List<Card> cards) {
        if (!isSameSuit(cards)) {
            Card previousCard = null;
            for (Card card : cards) {
                if (previousCard != null) {
                    if (card.getPointNumber() - previousCard.getPointNumber() != -1) {
                        return false;
                    }
                }
                previousCard = card;
            }
            return true;
        }
        return false;
    }

    @Override
    public Ranking getRanking() {
        return STRAIGHT;
    }
}
