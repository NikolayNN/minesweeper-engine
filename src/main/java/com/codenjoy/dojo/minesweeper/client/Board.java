package com.codenjoy.dojo.minesweeper.client;

import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.minesweeper.model.Elements;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.*;

import static com.codenjoy.dojo.services.PointImpl.pt;

public class Board extends AbstractBoard<Elements> {

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public boolean isBarrierAt(int x, int y) {
        return isAt(x, y, Elements.BORDER);
    }

    public boolean isNumb(Point point){
        return isAt(point.getX(), point.getY(), Elements.ONE_MINE, Elements.TWO_MINES, Elements.THREE_MINES, Elements.FOUR_MINES, Elements.FIVE_MINES, Elements.SIX_MINES, Elements.SEVEN_MINES, Elements.EIGHT_MINES);
    }

    public Set<Point> getHiddenCells(int x, int y) {
        Set<Point> hiddenCells = new HashSet<>();
        Elements element = Elements.HIDDEN;
        x--;
        y--;
        int i = 0;
        while(i < 3){
            int j = 0;
            while (j < 3){
                int currentX = x + j;
                int currentY = y + i;
                if (isAt(currentX, currentY, element)){
                    hiddenCells.add(new PointImpl(currentX, currentY));
                }
                j++;
            }
            i++;
        }
        return hiddenCells;
    }

    public Point getMe() {
        return get(Elements.DETECTOR).get(0);
    }

    public boolean isGameOver() {
    return !get(Elements.BANG).isEmpty();
    }
}