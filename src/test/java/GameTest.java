import domain.Game;
import domain.GameManager;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static domain.GameStatus.START;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        GameManager.playerBet(game, game.getCurrentPlayer(),1);

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
}
