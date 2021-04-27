package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Setter
@Getter
public class Game {
    private GameStatus status;
    private List<Player> playerList;
    private Integer pot;
    private Integer currentBet;
    private Player currentPlayer;
    private Queue<Player> waitingPlayers;
    private Queue<Player> completedPlayers;

    public Game() {
        this.status = GameStatus.STOP;
        this.playerList = new ArrayList<>();
        this.pot = 0;
        this.waitingPlayers = new LinkedList<>();
        this.completedPlayers = new LinkedList<>();
    }

    public Game(List<Player> playerList) {
        this.status = GameStatus.START;
        this.playerList = playerList;
        this.pot = 0;
        this.currentBet = 0;
        this.waitingPlayers = new LinkedList<>();
        this.waitingPlayers.addAll(playerList);
        this.currentPlayer = this.waitingPlayers.poll();
        this.completedPlayers = new LinkedList<>();
    }

    public void playerBet(Integer betNum) {
        if (Objects.isNull(this.currentPlayer)) {
            throw new RuntimeException("在轮次结束之后无法再下注");
        }
        this.currentBet = betNum;
        this.pot += betNum;
        this.completedPlayers.add(this.currentPlayer);
        if (this.waitingPlayers.size() > 0) {
            this.currentPlayer = this.waitingPlayers.poll();
        } else {
            this.currentPlayer = null;
        }
    }

    public void start() {
        if (Objects.nonNull(playerList) && playerList.size() < 2) {
            throw new RuntimeException("玩家人数不足，无法开始游戏");
        }
        if (playerList.stream().anyMatch(item -> !item.isHoldingChips())) {
            throw new RuntimeException("所有玩家必须持有筹码");
        }
        this.status = GameStatus.START;
        this.currentPlayer = this.waitingPlayers.poll();
    }

    public void addPlayer(Player player) {
        if (playerList.contains(player)) {
            throw new RuntimeException("玩家人数不足，无法开始游戏");
        }
        this.playerList.add(player);
        this.waitingPlayers.add(player);
    }


}
