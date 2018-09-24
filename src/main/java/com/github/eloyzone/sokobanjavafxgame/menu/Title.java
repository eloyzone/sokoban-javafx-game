package com.github.eloyzone.sokobanjavafxgame.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class Title extends StackPane
{
    public Title(String name)
    {
        Rectangle bg = new Rectangle(390, 60);
        bg.setFill(Color.BLACK);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
    }

    public Title(String name, Color fillColor)
    {
        Rectangle bg = new Rectangle(390, 60);
        bg.setFill(fillColor);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
    }
}
