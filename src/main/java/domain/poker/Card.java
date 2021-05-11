package domain.poker;

import java.util.Objects;

public class Card implements Comparable {
    private Suit suit;
    private Point point;

    public Card(Suit suit, Point point) {
        this.suit = suit;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public Integer getPointNumber(){
        return point.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.point.getPoint(), ((Card) o).getPoint().getPoint());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return point == card.point && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, suit);
    }
}
