import domain.poker.Card;
import domain.poker.Point;
import domain.poker.Suit;
import domain.poker.TexasRule;
import domain.poker.ranking.RankingResult;
import domain.poker.rankingCompare.HighCardComparing;
import domain.poker.rankingCompare.OnePairComparing;
import domain.poker.rankingCompare.TwoPairComparing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RankingComparingTest {
    @Test
    void should_compare_same_ranking_high_card() {
        TexasRule texasRule = new TexasRule();

        Card card1A = new Card(Suit.HEART, Point.FOUR);
        Card card2A = new Card(Suit.HEART, Point.NINE);
        Card card3A = new Card(Suit.SPADE, Point.TEN);
        Card card4A = new Card(Suit.HEART, Point.KING);
        Card card5A = new Card(Suit.DIAMOND, Point.ACE);
        List<Card> cardsA = Arrays.asList(card1A, card2A, card3A, card4A, card5A);
        texasRule.setCards(cardsA);
        RankingResult rankingResultA = texasRule.getRankingResult();

        Card card1B = new Card(Suit.HEART, Point.THREE);
        Card card2B = new Card(Suit.SPADE, Point.FOUR);
        Card card3B = new Card(Suit.HEART, Point.FIVE);
        Card card4B = new Card(Suit.HEART, Point.TEN);
        Card card5B = new Card(Suit.DIAMOND, Point.JACK);
        List<Card> cardsB = Arrays.asList(card1B, card2B, card3B, card4B, card5B);
        texasRule.setCards(cardsB);
        RankingResult rankingResultB = texasRule.getRankingResult();

        HighCardComparing highCardComparing = new HighCardComparing();
        Assertions.assertTrue(highCardComparing.compare(rankingResultA, rankingResultB) > 0);
    }

    @Test
    void should_compare_same_ranking_one_pair() {
        TexasRule texasRule = new TexasRule();

        Card card1A = new Card(Suit.HEART, Point.FOUR);
        Card card2A = new Card(Suit.HEART, Point.FOUR);
        Card card3A = new Card(Suit.SPADE, Point.TEN);
        Card card4A = new Card(Suit.HEART, Point.KING);
        Card card5A = new Card(Suit.DIAMOND, Point.ACE);
        List<Card> cardsA = Arrays.asList(card1A, card2A, card3A, card4A, card5A);
        texasRule.setCards(cardsA);
        RankingResult rankingResultA = texasRule.getRankingResult();

        Card card1B = new Card(Suit.HEART, Point.THREE);
        Card card2B = new Card(Suit.SPADE, Point.FIVE);
        Card card3B = new Card(Suit.HEART, Point.FIVE);
        Card card4B = new Card(Suit.HEART, Point.TEN);
        Card card5B = new Card(Suit.DIAMOND, Point.JACK);
        List<Card> cardsB = Arrays.asList(card1B, card2B, card3B, card4B, card5B);
        texasRule.setCards(cardsB);
        RankingResult rankingResultB = texasRule.getRankingResult();

        OnePairComparing onePairComparing = new OnePairComparing();
        Assertions.assertTrue(onePairComparing.compare(rankingResultA, rankingResultB) > 0);
    }

    @Test
    void should_compare_same_ranking_two_pair() {
        TexasRule texasRule = new TexasRule();

        Card card1A = new Card(Suit.HEART, Point.FOUR);
        Card card2A = new Card(Suit.HEART, Point.FOUR);
        Card card3A = new Card(Suit.SPADE, Point.TEN);
        Card card4A = new Card(Suit.HEART, Point.TEN);
        Card card5A = new Card(Suit.DIAMOND, Point.ACE);
        List<Card> cardsA = Arrays.asList(card1A, card2A, card3A, card4A, card5A);
        texasRule.setCards(cardsA);
        RankingResult rankingResultA = texasRule.getRankingResult();

        Card card1B = new Card(Suit.HEART, Point.THREE);
        Card card2B = new Card(Suit.SPADE, Point.THREE);
        Card card3B = new Card(Suit.HEART, Point.FIVE);
        Card card4B = new Card(Suit.HEART, Point.FIVE);
        Card card5B = new Card(Suit.DIAMOND, Point.JACK);
        List<Card> cardsB = Arrays.asList(card1B, card2B, card3B, card4B, card5B);
        texasRule.setCards(cardsB);
        RankingResult rankingResultB = texasRule.getRankingResult();

        TwoPairComparing twoPairComparing = new TwoPairComparing();
        Assertions.assertTrue(twoPairComparing.compare(rankingResultA, rankingResultB) > 0);
    }
}
