package com.github.eloyzone.sokobanjavafxgame.menu;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

abstract class MenuItem extends StackPane
{
    private String menuItemName;
    private Text text;

    public MenuItem()
    {
        Rectangle rectangleBorder = new Rectangle(260, 50);
        rectangleBorder.setOpacity(0.4);

        text = new Text();
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Times New Roman", 30));

        getChildren().addAll(rectangleBorder, text);

        setOnMouseEntered(event -> text.setFill(Color.DARKGREY));

        setOnMouseExited(event -> text.setFill(Color.WHITE));
    }

    public void setMenuItemName(String menuItemName)
    {
        this.menuItemName = menuItemName;
        text.setText(menuItemName);
    }
}
