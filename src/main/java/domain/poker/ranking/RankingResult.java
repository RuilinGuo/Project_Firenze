package domain.poker.ranking;

import domain.poker.Card;
import domain.poker.Point;
import domain.poker.Ranking;

import java.util.List;
import java.util.Map;

public class RankingResult{
    private Card highCard;
    private Ranking ranking;
    private List<Card> cards;
    private Map<Point, Integer> map;

    public RankingResult(Card highCard, Ranking ranking, List<Card> cards, Map<Point, Integer> map) {
        this.highCard = highCard;
        this.ranking = ranking;
        this.cards = cards;
        this.map = map;
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

    public Map<Point, Integer> getMap() {
        return map;
    }
}
