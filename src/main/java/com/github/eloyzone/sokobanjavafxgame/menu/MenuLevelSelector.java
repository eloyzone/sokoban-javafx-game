package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuLevelSelector
{
    public static Stage menuLevelSelectorStage;

    public void createStageAndShow()
    {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(1050, 600);

        ImageView imageView = new ImageView(new Image("/brick-wall.png"));
        imageView.setFitWidth(stackPane.getPrefWidth());
        imageView.setFitHeight(stackPane.getPrefHeight());

        stackPane.getChildren().add(imageView);

        VBox vBox = new VBox();

        Title menuTitle = new Title("SELECT LEVEL", Color.BLACK);

        Button backButtonToMainMenu = new Button("<-");

        backButtonToMainMenu.setStyle(
                "-fx-min-width: 70px; " +
                        "-fx-min-height: 70px; " +
                        "-fx-max-width: 70px; " +
                        "-fx-max-height: 70px;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-background-color: black;" +
                        "-fx-font-size: 25px;" +
                        "-fx-text-fill: white;"
        );


        // todo: add levels

        VBox.setMargin(menuTitle, new Insets(80, 0, 40, 0));
        VBox.setMargin(backButtonToMainMenu, new Insets(40, 0, 0, 5));

        backButtonToMainMenu.setOnAction(e -> backToMainMenu());

        vBox.getChildren().addAll(menuTitle, backButtonToMainMenu);
        stackPane.getChildren().addAll(vBox);
        Scene scene = new Scene(stackPane);

        menuLevelSelectorStage = new Stage();
        menuLevelSelectorStage.initStyle(StageStyle.UNDECORATED);
        menuLevelSelectorStage.setScene(scene);
        menuLevelSelectorStage.show();
    }

    private void backToMainMenu()
    {
        menuLevelSelectorStage.close();
        Main.mainStage.show();
    }
}
