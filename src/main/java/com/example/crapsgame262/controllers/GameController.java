package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.AlertBox;
import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;
    private Game game;

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
    private TextArea messageTextArea;

    public GameController() {
        this.game = new Game();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        if (nicknameLabel != null) {
            nicknameLabel.setText(this.currentPlayer.getNickname());
        }
    }

    @FXML
    public void initialize() {
        if (this.game == null) {
            this.game = new Game();
        }
        updateView();
    }

    private void updateView() {
        if (shotLabel != null) {
            shotLabel.setText(String.valueOf(game.getShot()));
        }
        if (pointLabel != null) {
            if (game.isPointSet()) {
                pointLabel.setText(String.valueOf(game.getPoint()));
            } else {
                pointLabel.setText("00");
            }
        }
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        Dice dice1 = new Dice();
        int value1 = dice1.roll();
        if (dice1ImageView != null) {
            dice1ImageView.setImage(new Image(getClass().getResourceAsStream(dice1.getImagePath())));
        }

        Dice dice2 = new Dice();
        int value2 = dice2.roll();
        if (dice2ImageView != null) {
            dice2ImageView.setImage(new Image(getClass().getResourceAsStream(dice2.getImagePath())));
        }

        int total = value1 + value2;
        if (this.game == null) {
            this.game = new Game();
        }
        game.setDice1Value(value1);
        game.setDice2Value(value2);
        game.setShot(total);
        updateView();
    }

    @FXML
    void onActionHelpButton(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        alertBox.showAlertBox(
                "Craps Game - Ayuda",
                "¿Cómo se juega?",
                "1. Pulsa \"Lanzar\" para hacer tu tiro de salida.\n\n" +
                "2. Si sacas 7 u 11, ganas automáticamente.\n\n" +
                "3. Si sacas 2, 3 o 12 (Craps), pierdes.\n\n" +
                "4. Si sacas 4, 5, 6, 8, 9 o 10, ese número es tu \"punto\".\n\n" +
                "5. Sigue lanzando: si sacas tu punto antes de un 7, ganas; " +
                "si sacas un 7 antes del punto, pierdes."
        );
    }
}