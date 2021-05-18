package domain.poker.rankingCompare;

import domain.poker.ranking.RankingResult;

public class TwoPairComparing extends DefaultComparing{
    @Override
    public int compare(RankingResult o1, RankingResult o2) {
        return this.pairComparing(o1.getMap(), o2.getMap(), 2, 3);
    }

}
