package com.codenjoy.dojo.minesweeper.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.minesweeper.model.Elements;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;

import java.util.*;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "Nikolay.Horushko@gmail.com";

    private Dice dice;
    private Board board;
    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        //если на поле есть пустая клетка, то вокруг нее все клетки безопасны
        //определяем все скрытые ячейки вокруг
        List<Point> emptyCells = board.get(Elements.NONE);
        Set<Point> hiddenCells = new HashSet<Point>();
        for (Point emptyCell : emptyCells) {
            hiddenCells = board.getHiddenCells(emptyCell.getX(), emptyCell.getY());
        }
        //находим наиболее близкую точку к нам
        //определяем наше местоположение
        Point me = board.getMe();
        //находим в цикле растояние до ближайшей скрытой клетки
        //добавляем точки в список
        //todo создать список безопасных точек
        Point nextPoint = findNextPoint(hiddenCells, me);
        // после нахождения ближайшей точки нужно двигаться в ее направлении
        
        //когда мы встали на точку эту точку удалить из hiddenCells



      return Direction.UP.toString();
    }

    private Point findNextPoint(Set<Point> hiddenCells, Point me) {
        int minDest = 100;
        Point nextPoint = null;
        Iterator<Point> iterator = hiddenCells.iterator();
        while(iterator.hasNext()){
            Point point = iterator.next();
            int destination = Math.abs(point.getX() - me.getX()) + Math.abs(point.getY() + me.getY());
            if (destination < minDest){
                minDest = destination;
                nextPoint = point;
            }
        }

        return nextPoint;
    }


    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
