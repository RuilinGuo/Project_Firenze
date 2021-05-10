import domain.poker.Point;
import domain.poker.Poker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static domain.poker.Point.*;
import static domain.poker.Suit.DIAMOND;

public class PokerTest {
    @Test
    void should_one_poker_has_52_different_cards() {
        Poker poker = new Poker();
        Assertions.assertEquals(52, poker.getDeck().size());

        poker.exchangeShuffle();
        Assertions.assertNotEquals(DIAMOND, poker.getDeck().get(0).getSuit());
        Assertions.assertNotEquals(TWO, poker.getDeck().get(0).getPoint());
    }
}
