package domain.poker.rankingCompare;

import domain.poker.ranking.RankingResult;

public class ThreeOfTHeKindComparing extends DefaultComparing{
    @Override
    public int compare(RankingResult o1, RankingResult o2) {
        return this.multiComparing(o1.getMap(), o2.getMap(), 3);
    }
}
