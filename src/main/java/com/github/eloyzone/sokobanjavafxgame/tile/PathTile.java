package com.github.eloyzone.sokobanjavafxgame.tile;

import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import javafx.scene.text.Text;

public class PathTile extends AbstractTile
{
    private Text text = new Text(".");

    private SokobanToken sokobanToken = null;
    private BoxToken boxToken = null;

    public PathTile(int row, int column, double translateX, double translateY, SokobanToken sokobanToken)
    {
        super(row, column, translateX, translateY);
        this.sokobanToken = sokobanToken;
        setTokenOnIt(true);
        getChildren().addAll(text, sokobanToken);
    }

    public PathTile(int row, int column, double translateX, double translateY, BoxToken boxToken)
    {
        super(row, column, translateX, translateY);
        this.boxToken = boxToken;
        setTokenOnIt(true);

        getChildren().addAll(text, boxToken);
    }

    public PathTile(int row, int column, double translateX, double translateY)
    {
        super(row, column, translateX, translateY);
        text.setText(".");
        getChildren().addAll(text);
    }


    public boolean removeSokoban()
    {
        if (getChildren().size() > 1)
        {
            getChildren().remove(1);
            sokobanToken = null;
            setTokenOnIt(false);
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public boolean addSokobanToken(SokobanToken sokobanToken)
    {
        this.sokobanToken = sokobanToken;
        sokobanToken.setColumn(getColumn());
        sokobanToken.setRow(getRow());
        getChildren().addAll(sokobanToken);
        setTokenOnIt(true);
        if (getChildren().size() > 1 && this.sokobanToken != null)
            return true;
        return false;
    }


    @Override
    public boolean addBoxToken(BoxToken boxToken)
    {
        this.boxToken = boxToken;
        this.boxToken.setColumn(getColumn());
        this.boxToken.setRow(getRow());
        getChildren().addAll(this.boxToken);
        setTokenOnIt(true);
        if (getChildren().size() > 1 && this.boxToken != null)
            return true;
        return false;
    }


    @Override
    public boolean removeBoxToken()
    {
        if (getChildren().size() > 1)
        {
            getChildren().remove(1);
            boxToken = null;
            setTokenOnIt(false);
            return true;
        } else
        {
            return false;
        }
    }

    public SokobanToken getSokobanToken()
    {
        return sokobanToken;
    }

    public void setSokobanToken(SokobanToken sokobanToken)
    {
        this.sokobanToken = sokobanToken;
    }

    @Override
    public BoxToken getBoxToken()
    {
        return boxToken;
    }

    public void setBoxToken(BoxToken boxToken)
    {
        this.boxToken = boxToken;
    }
}
