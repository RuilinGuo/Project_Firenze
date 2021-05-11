package domain.poker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static domain.poker.Ranking.HIGH_CARD;
import static domain.poker.Ranking.ONE_PAIR;

public class TexasRule {
    private static final Integer HAND_CARD_NUMBERS = 5;

    private List<Card> cards;
    private int value;

    public void setCards(List<Card> cards) {
        this.cards = cards.stream().sorted(Comparator.comparing(Card::getPointNumber).reversed()).collect(Collectors.toList());
    }

    public Ranking getRanking() {
        if (isHighCard()){
            return HIGH_CARD;
        }
        if(isOnePair()){
            return ONE_PAIR;
        }
        return null;
    }

    private boolean isOnePair() {

        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isOnePair = false;

        if (cardsRankCountMap.size() == 4) {
            Iterator<Map.Entry<Point, Integer>> it = cardsRankCountMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Point, Integer> next = it.next();
                if (next.getValue() == 2 || next.getValue() == 1) {
                    isOnePair = true;
                    break;
                }
            }
        }

        return isOnePair;
    }


    public boolean isHighCard() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isHighCard = false;

        if (cardsRankCountMap.size() == HAND_CARD_NUMBERS) {
            if (!this.isSameSuit(cards)) {
                if (cardsRankCountMap.keySet().size() == HAND_CARD_NUMBERS) {
                    isHighCard = true;
                }
            }
        }

        return isHighCard;
    }

    private boolean isSameSuit(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().allMatch(item -> item.getSuit().equals(suit));
    }

    public Map<Point, Integer> getCardsRankCountMap() {
        Map<Point, Integer> rankCount = new HashMap<Point, Integer>();
        for (Card card : cards) {
            if (!rankCount.containsKey(card.getPoint())) {
                rankCount.put(card.getPoint(), 1);
            } else {
                rankCount.put(card.getPoint(), rankCount.get(card.getPoint()) + 1);
            }
        }
        return rankCount;
    }
}
