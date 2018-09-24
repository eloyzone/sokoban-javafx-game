package com.github.eloyzone.sokobanjavafxgame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Board
{
    private Stage boardStage;

    public Board(int levelNumber)
    {
    }

    public Stage getScene()
    {
        boardStage = new Stage();

        HBox root = new HBox();
        root.setPrefSize(1050, 600);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, Color.LIGHTGRAY);

        boardStage.setScene(scene);
        boardStage.initStyle(StageStyle.UNDECORATED);

        return boardStage;
    }
}
