import domain.Game;
import domain.Player;
import org.junit.jupiter.api.Test;

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
}
