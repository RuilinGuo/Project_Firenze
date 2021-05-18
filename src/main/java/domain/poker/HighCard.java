package domain.poker;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static domain.poker.Ranking.HIGH_CARD;

public class HighCard implements RankingInterface {

    private List<Card> cards;

    @Override
    public boolean isTrue() {
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
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public RankingResult getRankingResult() {
        if (Objects.isNull(cards)) {
            return new RankingResult();
        }
        return new RankingResult(cards.get(0), HIGH_CARD, cards);
    }
}
