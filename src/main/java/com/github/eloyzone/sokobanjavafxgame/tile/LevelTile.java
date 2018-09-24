package com.github.eloyzone.sokobanjavafxgame.tile;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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

        getChildren().addAll(button);
    }
}
