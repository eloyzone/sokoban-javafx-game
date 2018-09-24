package com.github.eloyzone.sokobanjavafxgame.menu;

public class MenuItemExit extends MenuItem
{
    public MenuItemExit()
    {
        setMenuItemName("EXIT");
        setOnMouseClicked(event -> System.exit(0));
    }
}
