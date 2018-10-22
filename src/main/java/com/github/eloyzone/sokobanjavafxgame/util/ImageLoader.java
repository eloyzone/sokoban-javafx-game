package com.github.eloyzone.sokobanjavafxgame.util;

import javafx.scene.image.Image;

public class ImageLoader
{
    private static  Image imageBrickWallMainMenu = new Image("/brick-wall-main-menu.png");
    private static  Image imageBrickWall = new Image("/brick-wall.png");
    private static  Image imageBrickWallSmall = new Image("/brick-wall-smaller.png");
    private static  Image imageBox = new Image("/box.png");
    private static  Image imageTarget = new Image("/target.png");
    private static  Image imageWarehouse = new Image("/warehouse.jpg");
    private static  Image imageLock = new Image("/lock.png");

    public static Image getImageBrickWallMainMenu()
    {
        return imageBrickWallMainMenu;
    }

    public static Image getImageBrickWallSmall()
    {
        return imageBrickWallSmall;
    }

    public static Image getImageBrickWall()
    {
        return imageBrickWall;
    }

    public static Image getImageBox()
    {
        return imageBox;
    }

    public static Image getImageTarget()
    {
        return imageTarget;
    }

    public static Image getImageWarehouse()
    {
        return imageWarehouse;
    }

    public static Image getImageLock()
    {
        return imageLock;
    }
}
