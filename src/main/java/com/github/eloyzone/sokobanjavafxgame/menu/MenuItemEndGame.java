package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.GameEvent;
import javafx.stage.Stage;

class MenuItemEndGame extends MenuItem
{
    public MenuItemEndGame(Stage boardStage, Stage pauseStage)
    {
        setMenuItemName("End Game");

        setOnMouseClicked(event ->
        {
            boardStage.fireEvent(new GameEvent(GameEvent.END_GAME_LEVEL));
            pauseStage.close();
        });
    }
}
