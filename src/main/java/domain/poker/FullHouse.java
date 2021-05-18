package domain.poker;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static domain.poker.Ranking.FULL_HOUSE;

public class FullHouse implements RankingInterface {

    private List<Card> cards;

    @Override
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean isTrue() {
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
    public RankingResult getRankingResult() {
        if (Objects.isNull(cards)) {
            return new RankingResult();
        }
        return new RankingResult(cards.get(0), FULL_HOUSE, cards);
    }
}
