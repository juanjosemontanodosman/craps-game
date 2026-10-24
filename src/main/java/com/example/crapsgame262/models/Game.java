package com.example.crapsgame262.models;

public class Game {

    private static final int NO_POINT = 0;

    private int shot;
    private int point;
    private int wonGames;
    private int lostGames;

    public int getShot() {
        return shot;
    }

    public int getPoint() {
        return point;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    /**
     * Plays a shot with the given dice values, updates the game state
     * and returns a message describing what happened.
     */
    public String play(int dice1Value, int dice2Value) {
        shot = dice1Value + dice2Value;

        if (point == NO_POINT) {
            return playFirstShot();
        }
        return playNextShot();
    }

    private String playFirstShot() {
        if (shot == 7 || shot == 11) {
            wonGames++;
            return "Ganaste el juego con un tiro de " + shot + ".";
        }
        if (shot == 2 || shot == 3 || shot == 12) {
            lostGames++;
            return "Perdiste el juego con un tiro de " + shot + ".";
        }
        point = shot;
        return "Se estableció el punto en " + point + ". Sigue tirando.";
    }

    private String playNextShot() {
        if (shot == point) {
            wonGames++;
            point = NO_POINT;
            return "Ganaste el juego al repetir el punto " + shot + ".";
        }
        if (shot == 7) {
            lostGames++;
            point = NO_POINT;
            return "Perdiste el juego al sacar 7 antes del punto.";
        }
        return "Sigue tirando: debes repetir el punto " + point + ".";
    }

    public void reset() {
        shot = 0;
        point = NO_POINT;
        wonGames = 0;
        lostGames = 0;
    }
}
