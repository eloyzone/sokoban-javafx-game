package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.Main;

class MenuItemPlay extends MenuItem
{
    public MenuItemPlay()
    {
        setMenuItemName("PLAY");

        setOnMouseClicked(event ->
        {
            new MenuLevelSelector().createStageAndShow();
            Main.mainStage.hide();
        });
    }
}
