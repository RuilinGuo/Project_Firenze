import domain.poker.Card;
import domain.poker.Point;
import domain.poker.Suit;
import domain.poker.TexasRule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static domain.poker.Ranking.HIGH_CARD;
import static domain.poker.Ranking.ONE_PAIR;
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
        assertEquals(HIGH_CARD, texasRule.getRanking());
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
        assertEquals(ONE_PAIR, texasRule.getRanking());
    }

    @Test
    void should_give_TWO_PAIR_ranking() {
        Card card1 = new Card(Suit.HEART, Point.FOUR);
        Card card2 = new Card(Suit.HEART, Point.FOUR);
        Card card3 = new Card(Suit.HEART, Point.EIGHT);
        Card card4 = new Card(Suit.SPADE, Point.EIGHT);
        Card card5 = new Card(Suit.DIAMOND, Point.ACE);

        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5);

        TexasRule texasRule = new TexasRule();
        texasRule.setCards(cards);
        assertEquals(TWO_PAIR, texasRule.getRanking());
    }
}
