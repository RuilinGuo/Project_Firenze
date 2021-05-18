package domain.poker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TexasRule {

    private List<Card> cards;
    public static List<RankingInterface> rankingList;
    static {
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

        rankingList = Arrays.asList(royalFlush, straightFlush, fourOfTheKind, fullHouse, flush, straight, threeOfTheKind,
                twoPair, onePair, highCard);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards.stream().sorted(Comparator.comparing(Card::getPointNumber).reversed()).collect(Collectors.toList());
    }

    public RankingResult getRankingResult() {
        for (RankingInterface ranking : rankingList) {
            ranking.setCards(getCards());
            if(ranking.isTrue()){
                return ranking.getRankingResult();
            }
        }
        return null;
    }
}
