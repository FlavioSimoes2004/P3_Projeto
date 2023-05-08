package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LeaderboardController {

    @FXML
    private Button BACK;

    @FXML
    private Label fifth;

    @FXML
    private Label first;

    @FXML
    private Label forth;

    @FXML
    private Label second;

    @FXML
    private Label third;

    @FXML
    void switchToPrincipal(ActionEvent event) throws IOException{
        App.setRoot("principal");
    }

    public void initialize(){
        first.setText(Score.getText(0));
        second.setText(Score.getText(1));
        third.setText(Score.getText(2));
        forth.setText(Score.getText(3));
        fifth.setText(Score.getText(4));
    }
}