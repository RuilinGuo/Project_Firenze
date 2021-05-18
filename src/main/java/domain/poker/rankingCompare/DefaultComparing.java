package domain.poker.rankingCompare;

import domain.poker.Card;
import domain.poker.Point;
import domain.poker.ranking.RankingResult;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

abstract class DefaultComparing implements Comparator<RankingResult>{

    public int multiComparing(Map<Point, Integer> map1, Map<Point, Integer> map2, int pair) {
        Point p1MaxNumber = getMaxNumber(map1, pair);
        Point p2MaxNumber = getMaxNumber(map2, pair);

        if (p1MaxNumber.getPoint() > p2MaxNumber.getPoint()) {
            return 1;
        }
        if (p1MaxNumber.getPoint() < p2MaxNumber.getPoint()) {
            return -1;
        }
        if(p1MaxNumber.getPoint() == p2MaxNumber.getPoint()){
            map1.remove(p1MaxNumber);
            map2.remove(p2MaxNumber);
            p1MaxNumber = getMaxNumber(map1, 1);
            p2MaxNumber = getMaxNumber(map2, 1);
            return p1MaxNumber.compare(p2MaxNumber);
        }
        return 0;
    }

    private Point getMaxNumber(Map<Point, Integer> map, int pair) {
        Point maxNumber = Point.ZERO;
        Iterator<Map.Entry<Point, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Point, Integer> next = it.next();
            if (next.getValue() == pair) {
                maxNumber = next.getKey();
            }
        }
        return maxNumber;
    }

    public int seqComparing(List<Card> card1, List<Card> card2) {
        int size = card1.size();
        for (int i = 0; i < size; i++) {
            if (card1.get(i).getPointNumber() < card2.get(i).getPointNumber()) {
                return -1;
            }
            if (card1.get(i).getPointNumber() > card2.get(i).getPointNumber()) {
                return 1;
            }
        }
        return 0;
    }

    public int pairComparing(Map<Point, Integer> map1, Map<Point, Integer> map2, int pair, int loopNum) {
        Point p1MaxNum = findMaxPairNumber(map1, pair);
        Point p2MaxNum = findMaxPairNumber(map2, pair);

        if (p1MaxNum.getPoint() < p2MaxNum.getPoint()) {
            return -1;
        }
        if (p1MaxNum.getPoint() > p2MaxNum.getPoint()) {
            return 1;
        }
        if (p1MaxNum == p2MaxNum) {
            map1.remove(p1MaxNum);
            map2.remove(p2MaxNum);
            if (map1.size() == map2.size() && 0 == loopNum - 1) {
                return this.pairComparing(map1, map2, pair - 1, 1);
            }
            return this.pairComparing(map1, map2, pair, loopNum - 1);
        }
        return 0;

    }

    public Point findMaxPairNumber(Map<Point, Integer> map, int pair) {
        Point pairNumber = Point.ZERO;

        Iterator<Map.Entry<Point, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Point, Integer> next = it.next();
            if (next.getValue() == pair) {
                Point number = next.getKey();
                if (number.getPoint() > pairNumber.getPoint()) {
                    pairNumber = number;
                }
            }
        }
        return pairNumber;
    }
}
