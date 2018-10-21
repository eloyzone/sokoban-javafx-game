package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.Board;
import javafx.scene.layout.StackPane;

class MenuItemResume extends MenuItem
{
    public MenuItemResume(StackPane boardStackPane)
    {
        setMenuItemName("Resume");
        setOnMouseClicked(event ->
        {
            Board.setPauseMenuFalse();
            boardStackPane.getChildren().remove(boardStackPane.getChildren().size()-1);
        });
    }
}
