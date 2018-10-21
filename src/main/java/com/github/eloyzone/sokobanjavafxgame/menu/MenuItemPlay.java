package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.Main;
import javafx.scene.layout.StackPane;

class MenuItemPlay extends MenuItem
{
    public MenuItemPlay()
    {
        setMenuItemName("PLAY");

        setOnMouseClicked(event ->
        {
            StackPane stackPane = (StackPane) Main.mainStage.getScene().getRoot();
            stackPane.getChildren().add(new MenuLevelSelector().createLevelSelectorStackPane());
        });
    }
}
