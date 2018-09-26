package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.GameEvent;
import javafx.stage.Stage;

class MenuItemRestartLevel extends MenuItem
{
    public MenuItemRestartLevel(Stage boardStage, Stage pauseStage)
    {
        setMenuItemName("Restart Level");

        setOnMouseClicked(event ->
        {
            boardStage.fireEvent(new GameEvent(GameEvent.RESTART_LEVEL));
            pauseStage.close();
        });
    }
}
