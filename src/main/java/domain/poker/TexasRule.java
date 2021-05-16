package domain.poker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.poker.Ranking.FLUSH;
import static domain.poker.Ranking.FOUR_OF_THE_KIND;
import static domain.poker.Ranking.FULL_HOUSE;
import static domain.poker.Ranking.HIGH_CARD;
import static domain.poker.Ranking.ONE_PAIR;
import static domain.poker.Ranking.STRAIGHT;
import static domain.poker.Ranking.STRAIGHT_FLUSH;
import static domain.poker.Ranking.THREE_OF_THE_KIND;
import static domain.poker.Ranking.TWO_PAIR;

public class TexasRule {

    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards.stream().sorted(Comparator.comparing(Card::getPointNumber).reversed()).collect(Collectors.toList());
    }

    public Ranking getRanking() {
        RoyalFlush royalFlush = new RoyalFlush();
        if (royalFlush.isTrue(getCardsRankCountMap(), getCards())) {
            return royalFlush.getRanking();
        }

        StraightFlush straightFlush = new StraightFlush();
        if (straightFlush.isTrue(getCardsRankCountMap(), getCards())) {
            return straightFlush.getRanking();
        }
        if (isFourOfTheKind()) {
            return FOUR_OF_THE_KIND;
        }
        if (isFullHouse()) {
            return FULL_HOUSE;
        }
        if (isFlush()) {
            return FLUSH;
        }
        if (isStraight()) {
            return STRAIGHT;
        }
        if (isThreeOfTheKind()) {
            return THREE_OF_THE_KIND;
        }
        if (isTwoPair()) {
            return TWO_PAIR;
        }
        if (isOnePair()) {
            return ONE_PAIR;
        }
        if (isHighCard()) {
            return HIGH_CARD;
        }
        return null;
    }

    private boolean isStraight() {
        List<Card> cards = this.cards;

        if (!this.isSameSuit(cards)) {
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

    private boolean isFourOfTheKind() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        for (Map.Entry<Point, Integer> pointIntegerEntry : cardsRankCountMap.entrySet()) {
            if (pointIntegerEntry.getValue() == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        if (cardsRankCountMap.size() == 2) {
            for (Map.Entry<Point, Integer> next : cardsRankCountMap.entrySet()) {
                if (next.getValue() == 2 || next.getValue() == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFlush() {
        List<Card> cards = this.cards;

        return this.isSameSuit(cards);
    }

    private boolean isThreeOfTheKind() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        for (Map.Entry<Point, Integer> pointIntegerEntry : cardsRankCountMap.entrySet()) {
            if (pointIntegerEntry.getValue() == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isTwoPair = false;
        if (cardsRankCountMap.size() == 3) {
            isTwoPair = isPair(cardsRankCountMap);
        }
        return isTwoPair;
    }

    private boolean isOnePair() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isOnePair = false;
        if (cardsRankCountMap.size() == 4) {
            isOnePair = isPair(cardsRankCountMap);
        }

        return isOnePair;
    }

    public boolean isHighCard() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        if (cardsRankCountMap.size() == 5) {
            if (!this.isSameSuit(cards)) {
                if (cardsRankCountMap.keySet().size() == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPair(Map<Point, Integer> cardsRankCountMap) {
        for (Map.Entry<Point, Integer> next : cardsRankCountMap.entrySet()) {
            if (next.getValue() == 2 || next.getValue() == 1) {
                return true;
            }
        }
        return false;
    }

    public Map<Point, Integer> getCardsRankCountMap() {
        Map<Point, Integer> rankCount = new HashMap<>();
        List<Card> cards = this.cards;

        for (Card card : cards) {
            if (!rankCount.containsKey(card.getPoint())) {
                rankCount.put(card.getPoint(), 1);
            } else {
                rankCount.put(card.getPoint(), rankCount.get(card.getPoint()) + 1);
            }
        }
        return rankCount;
    }

    public static boolean isSameSuit(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().allMatch(item -> item.getSuit().equals(suit));
    }
}
