package com.github.eloyzone.sokobanjavafxgame.menu;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainMenu
{
    public Parent createContent()
    {
        StackPane root = new StackPane();

        root.setPrefSize(1050, 600);

        Image imageBrickWallMainMenu = new Image("/brick-wall-main-menu.png");

        ImageView imageView = new ImageView(imageBrickWallMainMenu);
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);

        Title title = new Title("S O K O B A N");
        title.setTranslateY(-200);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemExit());

        vBoxMenuBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, vBoxMenuBox);

        return root;
    }
}