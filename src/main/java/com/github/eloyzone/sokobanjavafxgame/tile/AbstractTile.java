package com.github.eloyzone.sokobanjavafxgame.tile;

import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class AbstractTile extends StackPane
{
    private boolean tokenOnIt = false;

    ImageView imageView;

    private int row;
    private int column;

    public AbstractTile(int row, int column, double translateX, double translateY)
    {
        setPrefHeight(50);
        setPrefWidth(50);

        this.row = row;
        this.column = column;
        setTranslateX(translateX);
        setTranslateY(translateY);
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

    public abstract boolean removeSokoban();

    public abstract boolean addSokobanToken(SokobanToken sokobanToken);

    public abstract boolean addBoxToken(BoxToken boxToken);

    public abstract boolean removeBoxToken();

    public boolean isTokenOnIt()
    {
        return tokenOnIt;
    }

    public void setTokenOnIt(boolean tokenOnIt)
    {
        this.tokenOnIt = tokenOnIt;
    }

    public abstract BoxToken getBoxToken();
}
