package domain.poker.ranking;

import domain.poker.Card;
import domain.poker.Ranking;

import java.util.List;

public class RankingResult{
    private Card highCard;
    private Ranking ranking;
    private List<Card> cards;

    public RankingResult(Card highCard, Ranking ranking, List<Card> cards) {
        this.highCard = highCard;
        this.ranking = ranking;
        this.cards = cards;
    }

    public RankingResult() {
    }

    public Card getHighCard() {
        return highCard;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public List<Card> getCards() {
        return cards;
    }
}
