package com.github.eloyzone.sokobanjavafxgame.menu;

import javafx.stage.Stage;

class MenuItemResume extends MenuItem
{
    public MenuItemResume(Stage pauseStage)
    {
        setMenuItemName("Resume");
        setOnMouseClicked(event -> pauseStage.close());
    }
}
