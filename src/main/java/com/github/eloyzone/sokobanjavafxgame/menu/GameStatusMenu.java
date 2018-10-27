package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameStatusMenu
{
    private StackPane rootStackPane;

    public GameStatusMenu()
    {
        rootStackPane = new StackPane();
        rootStackPane.setPrefSize(1050 - 600, 600 - 200);

        ImageView imageView = new ImageView(ImageLoader.getImageBrickWall());
        imageView.setFitWidth(rootStackPane.getPrefWidth());
        imageView.setFitHeight(rootStackPane.getPrefHeight());
        rootStackPane.getChildren().add(imageView);
    }

    public Parent createLoseMenu(StackPane boardStakePane)
    {
        Title title = new Title("YOU LOSE!");
        title.setTranslateY(-100);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemRestartLevel(boardStakePane),
                new MenuItemEndGame(boardStakePane)
        );

        vBoxMenuBox.setAlignment(Pos.CENTER);

        rootStackPane.getChildren().addAll(title, vBoxMenuBox);

        return rootStackPane;
    }


    public Parent createVictoryMenu(StackPane boardStakePane)
    {
        Title title = new Title("VICTORY");
        title.setTranslateY(-100);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemEndGame(boardStakePane)
        );

        vBoxMenuBox.setAlignment(Pos.CENTER);

        rootStackPane.getChildren().addAll(title, vBoxMenuBox);

        return rootStackPane;
    }

}