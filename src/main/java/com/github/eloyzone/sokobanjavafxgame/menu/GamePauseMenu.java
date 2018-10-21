package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GamePauseMenu
{
    public Parent createContent(StackPane boardStackPane)
    {
        StackPane root = new StackPane();

        root.setPrefSize(1050, 600);

        ImageView imageView = new ImageView(ImageLoader.getImageBrickWall());
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);

        Title title = new Title("P A U S E");
        title.setTranslateY(-200);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemResume(boardStackPane),
                new MenuItemRestartLevel(boardStackPane),
                new MenuItemEndGame(boardStackPane)
        );

        vBoxMenuBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, vBoxMenuBox);


        return root;
    }
}