import domain.Game;
import domain.GameManager;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static domain.GameStatus.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameTest {

    @Test
    void should_start_game_successfully() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game();
        game.addPlayer(playerA);
        game.addPlayer(playerB);

        //when
        game.start();

        //then
        assertEquals(START, game.getStatus());
    }

    @Test
    void should_update_status_when_player_bet() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game(Arrays.asList(playerA, playerB));

        //when
        GameManager.playerBet(game, game.getCurrentPlayer(), 1);

        //then
        assertEquals(9, playerA.getHoldingChips());
        assertEquals(1, game.getCurrentBet());
        assertEquals(playerB, game.getCurrentPlayer());
        assertEquals(1, game.getPot());
        assertEquals(0, game.getWaitingPlayers().size());
        assertEquals(1, game.getCompletedPlayers().size());
        assertEquals(1, game.getPlayerCurrentBet(playerA));
        assertEquals(1, game.getPlayerTotalBet(playerA));
    }

    @Test
    void should_update_status_when_player_call() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game(Arrays.asList(playerA, playerB));

        //when
        GameManager.playerBet(game, game.getCurrentPlayer(), 1);
        GameManager.playerCall(game, game.getCurrentPlayer());

        //then
        assertEquals(9, playerA.getHoldingChips());
        assertEquals(9, playerB.getHoldingChips());

        assertEquals(1, game.getCurrentBet());
        assertNull(game.getCurrentPlayer());
        assertEquals(2, game.getPot());
        assertEquals(0, game.getWaitingPlayers().size());
        assertEquals(2, game.getCompletedPlayers().size());
        assertEquals(1, game.getPlayerCurrentBet(playerA));
        assertEquals(1, game.getPlayerTotalBet(playerA));
        assertEquals(1, game.getPlayerCurrentBet(playerB));
        assertEquals(1, game.getPlayerTotalBet(playerB));
    }

    @Test
    void should_update_status_when_player_fold() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game(Arrays.asList(playerA, playerB));

        //when
        GameManager.playerFold(game, game.getCurrentPlayer());

        //then
        assertEquals(10, playerA.getHoldingChips());
        assertEquals(10, playerB.getHoldingChips());
        assertEquals(playerB, game.getCurrentPlayer());
        assertEquals(0, game.getCompletedPlayers().size());
    }

    @Test
    void should_enter_next_round_when_all_player_completed() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game(Arrays.asList(playerA, playerB));

        //when
        GameManager.playerBet(game, game.getCurrentPlayer(), 1);
        GameManager.playerCall(game, game.getCurrentPlayer());
        GameManager.nextRound(game);

        //then
        assertEquals(9, playerA.getHoldingChips());
        assertEquals(9, playerB.getHoldingChips());
        assertEquals(playerA, game.getCurrentPlayer());
        assertEquals(0, game.getCurrentBet());
        assertEquals(2, game.getPot());
    }

    @Test
    void should_over_game_when_a_winner_after_game_settlement() {
        //given
        Player playerA = new Player(10);
        Player playerB = new Player(10);
        Game game = new Game(Arrays.asList(playerA, playerB));
        GameManager.playerBet(game, playerA, 2);
        GameManager.playerCall(game, playerB);

        //when
        GameManager.settleGame(game, playerA);

        //then
        assertEquals(12, playerA.getHoldingChips());
        assertEquals(8, playerB.getHoldingChips());
        assertNull(game.getCurrentPlayer());
        assertNull(game.getCurrentBet());
        assertEquals(0, game.getPot());
    }
}
