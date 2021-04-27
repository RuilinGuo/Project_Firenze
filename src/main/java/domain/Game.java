package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Game {
    private GameStatus status;
    private List<Player> playerList;

    public Game() {
        this.status = GameStatus.STOP;
        playerList = new ArrayList<>();
    }

    public Game(List<Player> playerList) {
        this.status = GameStatus.STOP;
        this.playerList = playerList;
    }

    public void start(){
        if(Objects.nonNull(playerList) && playerList.size() <2){
            throw new RuntimeException("玩家人数不足，无法开始游戏");
        }
        if(playerList.stream().anyMatch(item -> !item.isHoldingChips())){
            throw new RuntimeException("所有玩家必须持有筹码");
        }
        this.status = GameStatus.START;
    }

    public void addPlayer(Player player){
        if (playerList.contains(player)) {
            throw new RuntimeException("玩家人数不足，无法开始游戏");
        }
        this.playerList.add(player);
    }
}
