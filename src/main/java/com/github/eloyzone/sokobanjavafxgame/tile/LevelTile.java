package com.github.eloyzone.sokobanjavafxgame.tile;

import com.github.eloyzone.sokobanjavafxgame.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LevelTile extends StackPane
{
    int levelNumber;

    public LevelTile(String value, double translateX, double translateY)
    {
        setAlignment(Pos.CENTER);

        setPrefWidth(100);
        setPrefHeight(100);

        setTranslateX(translateX);
        setTranslateY(translateY);

        levelNumber = Integer.parseInt(value);

        Button button = new Button();
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setId("tile-level-dark-blue");
        button.setText(value);
        button.setOnAction(e -> createAndShowBoardStage());

        getChildren().addAll(button);
    }

    private Stage createAndShowBoardStage()
    {
        Stage boardStage = new Board(levelNumber).getScene();
        boardStage.show();

        return boardStage;
    }
}
