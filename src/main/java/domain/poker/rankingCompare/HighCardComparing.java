package domain.poker.rankingCompare;

import domain.poker.ranking.RankingResult;

import java.util.Comparator;

public class HighCardComparing implements Comparator<RankingResult> {

    @Override
    public int compare(RankingResult o1, RankingResult o2) {
        int size = o1.getCards().size();

        for (int i = 0; i < size; i++) {
            if (o1.getCards().get(i).getPointNumber() < o2.getCards().get(i).getPointNumber()) {
                return -1;
            }
            if (o1.getCards().get(i).getPointNumber() > o2.getCards().get(i).getPointNumber()) {
                return 1;
            }
        }
        return 0;
    }
}
