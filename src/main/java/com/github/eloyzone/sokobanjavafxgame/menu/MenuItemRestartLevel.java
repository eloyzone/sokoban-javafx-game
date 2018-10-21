package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.GameEvent;
import javafx.scene.layout.StackPane;

class MenuItemRestartLevel extends MenuItem
{
    public MenuItemRestartLevel(StackPane boardStackPane)
    {
        setMenuItemName("Restart Level");

        setOnMouseClicked(event ->
        {
            boardStackPane.fireEvent(new GameEvent(GameEvent.RESTART_LEVEL));
        });
    }
}
