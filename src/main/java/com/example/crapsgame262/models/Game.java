package com.example.crapsgame262.models;

public class Game {
    private int shot;
    private int point;
    private int wonGames;
    private int lostGames;
    private boolean pointSet;
    private int dice1Value;
    private int dice2Value;

    public Game() {
        this.shot = 0;
        this.point = 0;
        this.wonGames = 0;
        this.lostGames = 0;
        this.pointSet = false;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public void incrementWonGames() {
        this.wonGames++;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void setLostGames(int lostGames) {
        this.lostGames = lostGames;
    }

    public void incrementLostGames() {
        this.lostGames++;
    }

    public boolean isPointSet() {
        return pointSet;
    }

    public void setPointSet(boolean pointSet) {
        this.pointSet = pointSet;
    }

    public int getDice1Value() {
        return dice1Value;
    }

    public void setDice1Value(int dice1Value) {
        this.dice1Value = dice1Value;
    }

    public int getDice2Value() {
        return dice2Value;
    }

    public void setDice2Value(int dice2Value) {
        this.dice2Value = dice2Value;
    }

    public int getTotalShot() {
        return this.dice1Value + this.dice2Value;
    }

    public void resetPoint() {
        this.point = 0;
        this.pointSet = false;
    }
}
