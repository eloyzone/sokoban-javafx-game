package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.GameEvent;
import javafx.scene.layout.StackPane;

class MenuItemEndGame extends MenuItem
{
    public MenuItemEndGame(StackPane boardStackPane)
    {
        setMenuItemName("End Game");

        setOnMouseClicked(event ->
        {
            boardStackPane.fireEvent(new GameEvent(GameEvent.END_GAME_LEVEL));
        });
    }
}
