package com.example.crapsgame262.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains the rules of the Craps game.
 * <p>
 * First shot: 7 or 11 wins, 2, 3 or 12 loses ("Craps"), any other value
 * sets the point. Next shots: repeating the point wins, a 7 loses.
 * </p>
 *
 * @author Anthony Alejandro Mora
 * @version 1.0
 */
public class Game {

    /** Value used when there is no point established. */
    private static final int NO_POINT = 0;

    /** Shot value that loses after the point is established. */
    private static final int SEVEN_OUT = 7;

    /** Shot values that win on the first shot. */
    private static final List<Integer> NATURAL_SHOTS = List.of(7, 11);

    /** Shot values that lose on the first shot ("Craps"). */
    private static final List<Integer> CRAPS_SHOTS = List.of(2, 3, 12);

    /** Sum of the dice in the last shot. */
    private int shot;

    /** Current point, or {@link #NO_POINT} if there is none. */
    private int point;

    /** Number of games won. */
    private int wonGames;

    /** Number of games lost. */
    private int lostGames;

    /** Shots of the current round, in order. */
    private final List<Integer> roundShots = new ArrayList<>();

    /**
     * Plays a shot with the given dice values, updates the game state
     * and returns a message describing what happened.
     *
     * @param dice1Value value of the first dice (1 to 6)
     * @param dice2Value value of the second dice (1 to 6)
     * @return message that describes the result of the shot
     */
    public String play(int dice1Value, int dice2Value) {
        shot = dice1Value + dice2Value;

        if (!isPointEstablished()) {
            roundShots.clear();
        }
        roundShots.add(shot);

        if (!isPointEstablished()) {
            return playFirstShot();
        }
        return playNextShot();
    }

    /**
     * Applies the rules of the first shot of a round.
     *
     * @return message that describes the result
     */
    private String playFirstShot() {
        if (NATURAL_SHOTS.contains(shot)) {
            wonGames++;
            return "Ganaste el juego con un tiro de " + shot + ".";
        }
        if (CRAPS_SHOTS.contains(shot)) {
            lostGames++;
            return "Craps: perdiste el juego con un tiro de " + shot + ".";
        }
        point = shot;
        return "Se estableció el punto en " + point + ". Sigue tirando.";
    }

    /**
     * Applies the rules of the shots after the point is established.
     *
     * @return message that describes the result
     */
    private String playNextShot() {
        if (shot == point) {
            wonGames++;
            point = NO_POINT;
            return "Ganaste el juego al repetir el punto " + shot
                    + " en " + roundShots.size() + " tiros.";
        }
        if (shot == SEVEN_OUT) {
            lostGames++;
            point = NO_POINT;
            return "Perdiste el juego al sacar 7 antes del punto.";
        }
        return "Sigue tirando: debes repetir el punto " + point + ".";
    }

    /**
     * Indicates if there is a point established (round in progress).
     *
     * @return {@code true} if the point is established
     */
    public boolean isPointEstablished() {
        return point != NO_POINT;
    }

    /**
     * Restarts the game: clears the shot, the point, the counters and the history.
     */
    public void reset() {
        shot = 0;
        point = NO_POINT;
        wonGames = 0;
        lostGames = 0;
        roundShots.clear();
    }

    /**
     * Returns the sum of the dice in the last shot.
     *
     * @return the last shot
     */
    public int getShot() {
        return shot;
    }

    /**
     * Returns the current point.
     *
     * @return the point, or 0 if there is none
     */
    public int getPoint() {
        return point;
    }

    /**
     * Returns the number of games won.
     *
     * @return games won
     */
    public int getWonGames() {
        return wonGames;
    }

    /**
     * Returns the number of games lost.
     *
     * @return games lost
     */
    public int getLostGames() {
        return lostGames;
    }

    /**
     * Returns the shots of the current round (read only).
     *
     * @return list of shots of the round
     */
    public List<Integer> getRoundShots() {
        return Collections.unmodifiableList(roundShots);
    }
}