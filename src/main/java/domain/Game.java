package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private Map<Player, Integer> currentRoundPlayerBetChips;
    private Map<Player, Integer> playerTotalBetChips;

    public Game() {
        this.status = GameStatus.STOP;
        this.playerList = new ArrayList<>();
        this.pot = 0;
        this.waitingPlayers = new LinkedList<>();
        this.completedPlayers = new LinkedList<>();
        this.currentRoundPlayerBetChips = new HashMap<>();
        this.playerTotalBetChips = new HashMap<>();
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
        this.currentRoundPlayerBetChips = new HashMap<>();
        this.playerTotalBetChips = new HashMap<>();
        playerList.forEach(item -> {
            this.currentRoundPlayerBetChips.put(item, 0);
            this.playerTotalBetChips.put(item, 0);
        });
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
        this.currentRoundPlayerBetChips.put(player, 0);
        this.playerTotalBetChips.put(player, 0);
    }

    public Integer getPlayerCurrentBet(Player player) {
        return this.currentRoundPlayerBetChips.get(player);
    }

    public Integer getPlayerTotalBet(Player player) {
        return this.playerTotalBetChips.get(player);
    }

    public void playerCall() {
        Integer playerCurrentBet = this.getPlayerCurrentBet(this.currentPlayer);
        Integer playerBet = this.currentBet - playerCurrentBet;
        updatePotAndPlayerBetChips(playerBet);
        completedPlayer();
    }

    public void playerBet(Integer betNum) {
        if (Objects.isNull(this.currentPlayer)) {
            throw new RuntimeException("在轮次结束之后无法再下注");
        }
        this.currentBet += betNum;

        updatePotAndPlayerBetChips(betNum);
        resetWaitingPlayers();
        completedPlayer();
    }

    public void playerFold() {
        if (this.waitingPlayers.size() > 0) {
            this.currentPlayer = this.waitingPlayers.poll();
        } else {
            this.currentPlayer = null;
        }
    }

    private void completedPlayer() {
        this.completedPlayers.add(this.currentPlayer);
        if (this.waitingPlayers.size() > 0) {
            this.currentPlayer = this.waitingPlayers.poll();
        } else {
            this.currentPlayer = null;
        }
    }

    private void updatePotAndPlayerBetChips(Integer playerBet) {
        this.pot += playerBet;
        this.currentRoundPlayerBetChips.put(this.currentPlayer, getPlayerCurrentBet(this.currentPlayer) + playerBet);
        this.playerTotalBetChips.put(this.currentPlayer, getPlayerTotalBet(this.currentPlayer) + playerBet);
    }

    private void resetWaitingPlayers() {
        while (this.completedPlayers.size() > 0) {
            this.waitingPlayers.add(this.completedPlayers.poll());
        }
    }
}
