package com.github.eloyzone.sokobanjavafxgame.token;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SokobanToken extends ImageView
{
    int row;
    int column;

    private Image image = new Image("/lift-truck.png");

    public SokobanToken(int row, int column)
    {
        setImage(image);
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
}
