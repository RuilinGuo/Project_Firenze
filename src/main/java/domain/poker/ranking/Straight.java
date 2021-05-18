package domain.poker.ranking;

import domain.poker.Card;

import java.util.List;
import java.util.Objects;

import static domain.poker.Ranking.STRAIGHT;

public class Straight implements RankingInterface {

    private List<Card> cards;

    @Override
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean isTrue() {
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
    public RankingResult getRankingResult() {
        if (Objects.isNull(cards)) {
            return new RankingResult();
        }
        return new RankingResult(cards.get(0), STRAIGHT, cards, getCardsRankCountMap(cards));
    }
}
