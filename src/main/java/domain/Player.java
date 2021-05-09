package domain;

import java.util.Objects;

public class Player {
    private String name;
    private boolean tookAction;

    public Player(String name) {
        this.name = name;
        this.tookAction = false;
    }

    public String getName() {
        return name;
    }

    public void tookAction() {
        this.tookAction = true;
    }

    public boolean isTookAction() {
        return tookAction;
    }

    public void resetAction(){
        this.tookAction = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
