package com.khlopotov.ai;

public class Snail {
    final DirectionState right = new Right(this);
    final DirectionState down = new Down(this);
    final DirectionState left = new Left(this);
    final DirectionState up = new Up(this);

    DirectionState currentDirection = right;
    final int[] currentPosition = new int[]{0, -1};
    private final int[][] arr2d;
    private final boolean[][] wasVisited;

    public Snail(int[][] arr2d) {
        this.arr2d = arr2d;
        wasVisited = new boolean[arr2d.length][arr2d.length];
    }

    public int[][] getArr2d() {
        return arr2d;
    }

    public boolean wasVisited(int row, int col) {
        return wasVisited[row][col];
    }

    public void setVisited(int row, int col) {
        wasVisited[row][col] = true;
    }

    public static int[] snail(int[][] array) {
        if (array[0].length == 0) {
            return array[0];
        }
        return new Snail(array).snail();
    }

    public int[] snail() {
        int[] res = new int[arr2d.length * arr2d.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = currentDirection.next();
        }
        return res;
    }

}

abstract class DirectionState {
    final Snail snail;

    public DirectionState(Snail snail) {
        this.snail = snail;
    }

    public int next() {
        int prevRow = snail.currentPosition[0];
        int prevCol = snail.currentPosition[1];
        snail.currentDirection.nextPosByDirection();

        try {
            if (snail.wasVisited(snail.currentPosition[0], snail.currentPosition[1])) {
                snail.currentDirection.setCurrentPosition(prevRow, prevCol);
                snail.currentDirection.nextDirection();
                return snail.currentDirection.next();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
            snail.currentDirection.setCurrentPosition(prevRow, prevCol);
            snail.currentDirection.nextDirection();
            return snail.currentDirection.next();
        }

        snail.setVisited(snail.currentPosition[0], snail.currentPosition[1]);
        return snail.getArr2d()[snail.currentPosition[0]][snail.currentPosition[1]];
    }

    public void setCurrentPosition(int row, int col) {
        snail.currentPosition[0] = row;
        snail.currentPosition[1] = col;
    }

    public abstract void nextPosByDirection();
    public abstract void nextDirection();
}

class Right extends DirectionState {
    public Right(Snail snail) {
        super(snail);
    }

    @Override
    public void nextPosByDirection() {
        snail.currentPosition[1]++;
    }

    @Override
    public void nextDirection() {
        snail.currentDirection = snail.down;
    }
}

class Down extends DirectionState {
    public Down(Snail snail) {
        super(snail);
    }

    @Override
    public void nextPosByDirection() {
        snail.currentPosition[0]++;
    }

    @Override
    public void nextDirection() {
        snail.currentDirection = snail.left;
    }
}

class Left extends DirectionState {
    public Left(Snail snail) {
        super(snail);
    }

    @Override
    public void nextPosByDirection() {
        snail.currentPosition[1]--;
    }

    @Override
    public void nextDirection() {
        snail.currentDirection = snail.up;
    }
}

class Up extends DirectionState {
    public Up(Snail snail) {
        super(snail);
    }

    @Override
    public void nextPosByDirection() {
        snail.currentPosition[0]--;
    }

    @Override
    public void nextDirection() {
        snail.currentDirection = snail.right;
    }
}
