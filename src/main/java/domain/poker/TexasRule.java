package domain.poker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static domain.poker.Ranking.FLUSH;
import static domain.poker.Ranking.FOUR_OF_THE_KIND;
import static domain.poker.Ranking.FULL_HOUSE;
import static domain.poker.Ranking.HIGH_CARD;
import static domain.poker.Ranking.ONE_PAIR;
import static domain.poker.Ranking.STRAIGHT;
import static domain.poker.Ranking.THREE_OF_THE_KIND;
import static domain.poker.Ranking.TWO_PAIR;

public class TexasRule {
    private static final Integer HAND_CARD_NUMBERS = 5;

    private List<Card> cards;
    private int value;

    public void setCards(List<Card> cards) {
        this.cards = cards.stream().sorted(Comparator.comparing(Card::getPointNumber).reversed()).collect(Collectors.toList());
    }

    public Ranking getRanking() {
        if(isFourOfTheKind()){
            return FOUR_OF_THE_KIND;
        }
        if(isFullHouse()){
            return FULL_HOUSE;
        }
        if(isFlush()){
            return FLUSH;
        }
        if(isStraight()){
            return STRAIGHT;
        }
        if(isThreeOfTheKind()){
            return THREE_OF_THE_KIND;
        }
        if(isTwoPair()){
            return TWO_PAIR;
        }
        if(isOnePair()){
            return ONE_PAIR;
        }
        if (isHighCard()){
            return HIGH_CARD;
        }
        return null;
    }

    private boolean isFourOfTheKind() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();
        boolean isFourOfTheKind = false;

        Iterator<Map.Entry<Point, Integer>> it = cardsRankCountMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == HAND_CARD_NUMBERS - 1) {
                isFourOfTheKind = true;
                break;
            }
        }

        return isFourOfTheKind;
    }

    private boolean isFullHouse() {
        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isFullHouse = false;

        if (cardsRankCountMap.size() == 2) {
            Iterator<Map.Entry<Point, Integer>> it = cardsRankCountMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Point, Integer> next = it.next();
                if (next.getValue() == 2 || next.getValue() == 3) {
                    isFullHouse = true;
                    break;
                }
            }
        }
        return isFullHouse;
    }

    private boolean isFlush() {
        // 如果是同色
        return this.isSameSuit(cards);
    }

    private boolean isStraight() {
        if (!this.isSameSuit(cards)) { // 如果是同色
            boolean isStraight = true;
            Card previousCard = null;
            for (Card card : cards) {
                if (previousCard != null) {
                    if (card.getPointNumber() - previousCard.getPointNumber() != -1) {
                        isStraight = false;
                        break;
                    }
                }
                previousCard = card;
            }
            if (isStraight == true) {
                return true;
            }
        }

        return false;
    }

    private boolean isThreeOfTheKind() {

        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isThreeOfTheKind = false;

        Iterator<Map.Entry<Point, Integer>> it = cardsRankCountMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 3) {
                isThreeOfTheKind = true;
                break;
            }
        }
        return isThreeOfTheKind;
    }

    private boolean isTwoPair() {

        Map<Point, Integer> cardsRankCountMap = getCardsRankCountMap();

        boolean isTwoPair = false;

        if (cardsRankCountMap.size() == 3) {
            Iterator<Map.Entry<Point, Integer>> it = cardsRankCountMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Point, Integer> next = it.next();
                if (next.getValue() == 2 || next.getValue() == 1) {
                    isTwoPair = true;
                    break;
                }
            }
        }

        return isTwoPair;
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