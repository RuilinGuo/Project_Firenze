package domain.poker;

import java.util.List;
import java.util.Map;

public interface RankingInterface {
    boolean isTrue(Map<Point, Integer> map, List<Card> cards);
    Ranking getRanking();
}
