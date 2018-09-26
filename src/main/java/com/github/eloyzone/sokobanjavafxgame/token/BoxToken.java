package com.github.eloyzone.sokobanjavafxgame.token;

import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.scene.image.ImageView;

public class BoxToken extends ImageView
{
    int row;
    int column;

    public BoxToken(int row, int column)
    {
        setImage(ImageLoader.getImageBox());
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
