package domain.poker;

import domain.poker.ranking.Flush;
import domain.poker.ranking.FourOfTheKind;
import domain.poker.ranking.FullHouse;
import domain.poker.ranking.HighCard;
import domain.poker.ranking.OnePair;
import domain.poker.ranking.RankingInterface;
import domain.poker.ranking.RankingResult;
import domain.poker.ranking.RoyalFlush;
import domain.poker.ranking.Straight;
import domain.poker.ranking.StraightFlush;
import domain.poker.ranking.ThreeOfTheKind;
import domain.poker.ranking.TwoPair;

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
