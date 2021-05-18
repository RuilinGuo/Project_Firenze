package domain.poker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        StraightFlush straightFlush = new StraightFlush();
        FourOfTheKind fourOfTheKind = new FourOfTheKind();
        FullHouse fullHouse = new FullHouse();
        Flush flush = new Flush();
        Straight straight = new Straight();
        ThreeOfTheKind threeOfTheKind = new ThreeOfTheKind();
        TwoPair twoPair = new TwoPair();
        OnePair onePair = new OnePair();
        HighCard highCard = new HighCard();

        List<RankingInterface> rankingList = Arrays.asList(royalFlush, straightFlush, fourOfTheKind, fullHouse, flush, straight, threeOfTheKind,
                twoPair, onePair, highCard);
        for (RankingInterface ranking : rankingList) {
            if(ranking.isTrue(getCards())){
                return ranking.getRanking();
            }
        }
        return null;
    }
}
