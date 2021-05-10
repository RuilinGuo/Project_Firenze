import domain.Game;
import domain.Player;
import domain.action.Pass;
import domain.poker.Card;
import domain.poker.Poker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static domain.poker.Point.TWO;
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

    @Test
    void should_deal_card_to_all_player() {
        Poker poker = new Poker();
        poker.exchangeShuffle();

        Game game = new Game(new Player("a"), new Player("b"));

        game.setPoker(poker);
        game.dealCardsToAllPlayer();

        Assertions.assertEquals(2, game.getActivePlayer().getHandCards().size());
    }

    @Test
    void should_add_public_card_when_enter_next_round() {
        Poker poker = new Poker();
        poker.exchangeShuffle();

        Game game = new Game(new Player("a"), new Player("b"));

        game.setPoker(poker);
        game.dealCardsToAllPlayer();
        Assertions.assertEquals(0, game.getPublicCard().size());

        game.execute(new Pass());
        game.execute(new Pass());
        Assertions.assertEquals(3, game.getPublicCard().size());

        game.execute(new Pass());
        game.execute(new Pass());
        Assertions.assertEquals(4, game.getPublicCard().size());

        game.execute(new Pass());
        game.execute(new Pass());
        Assertions.assertEquals(5, game.getPublicCard().size());
    }

}
