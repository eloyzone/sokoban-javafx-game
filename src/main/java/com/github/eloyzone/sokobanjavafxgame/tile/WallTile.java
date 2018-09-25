package com.github.eloyzone.sokobanjavafxgame.tile;

import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WallTile extends AbstractTile
{
    public WallTile(int row, int column, double translateX, double translateY)
    {
        super(row, column, translateX, translateY);

        imageView = new ImageView(new Image("/brick-wall-smaller.png"));
        imageView.setFitHeight(49.5);
        imageView.setFitWidth(49.5);

        getChildren().addAll(imageView);
    }

    @Override
    public boolean removeSokoban()
    {
        return false;
    }

    @Override
    public boolean addSokobanToken(SokobanToken sokobanToken)
    {
        return false;
    }

    @Override
    public boolean addBoxToken(BoxToken boxToken)
    {
        return false;
    }

    @Override
    public boolean removeBoxToken()
    {
        return false;
    }

    @Override
    public BoxToken getBoxToken()
    {
        return null;
    }
}
