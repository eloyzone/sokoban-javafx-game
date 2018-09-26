package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainMenu
{
    public Parent createContent()
    {
        StackPane root = new StackPane();

        root.setPrefSize(1050, 600);

        ImageView imageView = new ImageView(ImageLoader.getImageBrickWallMainMenu());
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);

        Title menuTitle = new Title("S O K O B A N");
        menuTitle.setTranslateY(-200);

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemPlay(),
                new MenuItemExit());

        vBoxMenuBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(menuTitle, vBoxMenuBox);

        return root;
    }
}