package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameStatusMenu
{
    public Parent createContent(Stage pauseStage, Stage boardStage)
    {
        StackPane root = new StackPane();

        root.setPrefSize(1050-600, 600-200);

        ImageView imageView = new ImageView(ImageLoader.getImageBrickWall());
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);

        Title title = new Title("VICTORY");
        title.setTranslateY(-100);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemEndGame(boardStage, pauseStage)
        );

        vBoxMenuBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, vBoxMenuBox);

        return root;
    }
}