import domain.poker.Card;
import domain.poker.Point;
import domain.poker.Suit;
import domain.poker.TexasRule;
import domain.poker.ranking.RankingResult;
import domain.poker.rankingCompare.HighCardComparing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RankingComparingTest {
    @Test
    void should_compare_same_ranking() {
        TexasRule texasRule = new TexasRule();

        Card card1A = new Card(Suit.HEART, Point.FOUR);
        Card card2A = new Card(Suit.HEART, Point.NINE);
        Card card3A = new Card(Suit.HEART, Point.TEN);
        Card card4A = new Card(Suit.HEART, Point.KING);
        Card card5A = new Card(Suit.HEART, Point.ACE);
        List<Card> cardsA = Arrays.asList(card1A, card2A, card3A, card4A, card5A);
        texasRule.setCards(cardsA);
        RankingResult rankingResultA = texasRule.getRankingResult();

        Card card1B = new Card(Suit.HEART, Point.THREE);
        Card card2B = new Card(Suit.HEART, Point.FOUR);
        Card card3B = new Card(Suit.HEART, Point.FIVE);
        Card card4B = new Card(Suit.HEART, Point.TEN);
        Card card5B = new Card(Suit.HEART, Point.JACK);
        List<Card> cardsB = Arrays.asList(card1B, card2B, card3B, card4B, card5B);
        texasRule.setCards(cardsB);
        RankingResult rankingResultB = texasRule.getRankingResult();

        HighCardComparing highCardComparing = new HighCardComparing();
        Assertions.assertTrue(highCardComparing.compare(rankingResultA, rankingResultB) > 0);
    }
}
