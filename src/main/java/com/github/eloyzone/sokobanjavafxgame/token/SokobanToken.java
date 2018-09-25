package com.github.eloyzone.sokobanjavafxgame.token;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SokobanToken extends ImageView
{
    int row;
    int column;

    private Image imageUp = new Image("/lift-truck-up.png");
    private Image imageDown = new Image("/lift-truck-down.png");
    private Image imageLeft = new Image("/lift-truck-left.png");
    private Image imageRight = new Image("/lift-truck-right.png");

    public SokobanToken(int row, int column)
    {
        setImage(imageUp);
        setFitHeight(30);
        setFitWidth(30);

        this.row = row;
        this.column = column;
    }


    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public void changeImageToUp()
    {
        setImage(imageUp);
    }

    public void changeImageToDown()
    {
        setImage(imageDown);
    }

    public void changeImageToLeft()
    {
        setImage(imageLeft);
    }

    public void changeImageToRight()
    {
        setImage(imageRight);
    }
}
