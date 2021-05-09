import domain.action.Bet;
import domain.action.Fold;
import domain.Game;
import domain.action.Pass;
import domain.Player;
import domain.action.Raise;
import domain.Round;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void should_player_a_be_the_first() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        assertEquals("a", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(0, game.getCurrentBid());
    }

    @Test
    void should_player_b_should_be_next_player_if_player_a_pass() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        game.execute(new Pass());

        assertEquals("b", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(1, game.getMiniWager());
        assertEquals(0, game.getCurrentBid());
    }

    @Test
    void should_set_current_bid_to_min_wager_when_player_a_bet() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        game.execute(new Bet());

        assertEquals("b", game.getActivePlayer().getName());
        assertEquals(1, game.getPot());
        assertEquals(1, game.getCurrentBid());
    }

    @Test
    void should_enter_next_round_when_all_player_pass() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));
        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("a", game.getActivePlayer().getName());
        game.execute(new Pass());
        assertEquals("b", game.getActivePlayer().getName());
        game.execute(new Pass());
        assertEquals("c", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
    }

    @Test
    void should_enter_next_round_when_all_player_bet() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));
        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("a", game.getActivePlayer().getName());
        game.execute(new Bet());
        assertEquals("b", game.getActivePlayer().getName());
        game.execute(new Bet());
        assertEquals("c", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals("a",game.getActivePlayer().getName());
        assertEquals(false, game.getActivePlayer().isTookAction());
    }

    @Test
    void should_not_enter_next_round_if_anyone_raise_the_wager() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("a", game.getActivePlayer().getName());
        game.execute(new Bet());
        assertEquals("b", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("c", game.getActivePlayer().getName());
        game.execute(new Raise(), 2);

        assertEquals(Round.PREFLOP, game.getCurrentRound());
        assertEquals("a", game.getActivePlayer().getName());
        assertEquals(4, game.getPot());
        assertEquals(2, game.getCurrentBid());
        assertEquals(false,game.getActivePlayer().isTookAction());
    }

    @Test
    void should_call_to_previous_bet_after_someone_raise_the_wager() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("a", game.getActivePlayer().getName());
        game.execute(new Bet());
        int playerAWager = game.getCurrentBid();

        assertEquals("b", game.getActivePlayer().getName());
        game.execute(new Bet());


        assertEquals("c", game.getActivePlayer().getName());
        game.execute(new Raise(), 3);

        assertEquals(3, game.getCurrentBid());
        int pot = game.getPot();

        assertEquals("a", game.getActivePlayer().getName());
        //call
        game.execute(new Bet());

        assertEquals(3, game.getCurrentBid());
        assertEquals(game.getCurrentBid() - playerAWager, game.getPot() - pot);

        assertEquals("b", game.getActivePlayer().getName());
        //call
        game.execute(new Bet());
        assertEquals(Round.FLOP, game.getCurrentRound());
    }

    @Test
    void should_remove_player_when_player_fold() {
        Game game = new Game(new Player("a"), new Player("b"), new Player("c"));

        assertEquals("a", game.getActivePlayer().getName());
        game.execute(new Fold());

        assertEquals("b", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals("c", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals("b", game.getActivePlayer().getName());
    }


}
