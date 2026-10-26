package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;
    private final Game game = new Game();
    private final Dice dice1 = new Dice();
    private final Dice dice2 = new Dice();

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label shotLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label wonGamesLabel;

    @FXML
    private Label lostGamesLabel;

    @FXML
    private TextArea resultTextArea;

    @FXML
    public void initialize() {
        updateGameLabels();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        int dice1Value = dice1.roll();
        int dice2Value = dice2.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );

        String result = game.play(dice1Value, dice2Value);

        updateGameLabels();
        resultTextArea.appendText(result + System.lineSeparator());
    }

    private void updateGameLabels() {
        shotLabel.setText(String.format("%02d", game.getShot()));
        pointLabel.setText(String.format("%02d", game.getPoint()));
        wonGamesLabel.setText(String.valueOf(game.getWonGames()));
        lostGamesLabel.setText(String.valueOf(game.getLostGames()));
    }
}
