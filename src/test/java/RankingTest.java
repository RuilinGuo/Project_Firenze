import domain.poker.Card;
import domain.poker.Point;
import domain.poker.ranking.RankingResult;
import domain.poker.Suit;
import domain.poker.TexasRule;
import domain.poker.rankingCompare.HighCardComparing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static domain.poker.Ranking.FLUSH;
import static domain.poker.Ranking.FOUR_OF_THE_KIND;
import static domain.poker.Ranking.FULL_HOUSE;
import static domain.poker.Ranking.HIGH_CARD;
import static domain.poker.Ranking.ONE_PAIR;
import static domain.poker.Ranking.ROYAL_FLUSH;
import static domain.poker.Ranking.STRAIGHT;
import static domain.poker.Ranking.STRAIGHT_FLUSH;
import static domain.poker.Ranking.THREE_OF_THE_KIND;
import static domain.poker.Ranking.TWO_PAIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingTest {

    @Test
    void should_give_HIGH_CARD_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.HEART, Point.SEVEN);
        Card card3 = new Card(Suit.HEART, Point.EIGHT);
        Card card4 = new Card(Suit.SPADE, Point.KING);
        Card card5 = new Card(Suit.DIAMOND, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(HIGH_CARD, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_ONE_PAIR_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.HEART, Point.FOUR);
        Card card3 = new Card(Suit.HEART, Point.EIGHT);
        Card card4 = new Card(Suit.SPADE, Point.KING);
        Card card5 = new Card(Suit.DIAMOND, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(ONE_PAIR, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_TWO_PAIR_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.DIAMOND, Point.FOUR);
        Card card3 = new Card(Suit.HEART, Point.EIGHT);
        Card card4 = new Card(Suit.SPADE, Point.EIGHT);
        Card card5 = new Card(Suit.DIAMOND, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(TWO_PAIR, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_THREE_OF_THE_KIND_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.DIAMOND, Point.FOUR);
        Card card3 = new Card(Suit.SPADE, Point.FOUR);
        Card card4 = new Card(Suit.SPADE, Point.EIGHT);
        Card card5 = new Card(Suit.DIAMOND, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(THREE_OF_THE_KIND, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_STRAIGHT_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.DIAMOND, Point.FIVE);
        Card card3 = new Card(Suit.SPADE, Point.SIX);
        Card card4 = new Card(Suit.SPADE, Point.SEVEN);
        Card card5 = new Card(Suit.DIAMOND, Point.EIGHT);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(STRAIGHT, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_FLUSH_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.HEART, Point.FIVE);
        Card card3 = new Card(Suit.HEART, Point.ACE);
        Card card4 = new Card(Suit.HEART, Point.SEVEN);
        Card card5 = new Card(Suit.HEART, Point.EIGHT);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(FLUSH, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_FULL_HOUSE_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.DIAMOND, Point.FOUR);
        Card card3 = new Card(Suit.SPADE, Point.FOUR);
        Card card4 = new Card(Suit.SPADE, Point.SEVEN);
        Card card5 = new Card(Suit.DIAMOND, Point.SEVEN);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(FULL_HOUSE, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_FOUR_OF_THE_KIND_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.DIAMOND, Point.FOUR);
        Card card3 = new Card(Suit.SPADE, Point.FOUR);
        Card card4 = new Card(Suit.CLUB, Point.FOUR);
        Card card5 = new Card(Suit.DIAMOND, Point.SEVEN);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(FOUR_OF_THE_KIND, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_STRAIGHT_FLUSH_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.HEART, Point.FIVE);
        Card card3 = new Card(Suit.HEART, Point.SIX);
        Card card4 = new Card(Suit.HEART, Point.SEVEN);
        Card card5 = new Card(Suit.HEART, Point.EIGHT);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(STRAIGHT_FLUSH, texasRule.getRankingResult().getRanking());
    }

    @Test
    void should_give_ROYAL_FLUSH_ranking() {
        Card card1 = new Card(Suit.HEART, Point.TEN);
        Card card2 = new Card(Suit.HEART, Point.JACK);
        Card card3 = new Card(Suit.HEART, Point.QUEEN);
        Card card4 = new Card(Suit.HEART, Point.KING);
        Card card5 = new Card(Suit.HEART, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(ROYAL_FLUSH, texasRule.getRankingResult().getRanking());
    }
}
