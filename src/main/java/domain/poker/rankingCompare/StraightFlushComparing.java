package domain.poker.rankingCompare;

import domain.poker.ranking.RankingResult;

public class StraightFlushComparing extends DefaultComparing{
    @Override
    public int compare(RankingResult o1, RankingResult o2) {
        return this.seqComparing(o1.getCards(), o2.getCards());
    }
}
