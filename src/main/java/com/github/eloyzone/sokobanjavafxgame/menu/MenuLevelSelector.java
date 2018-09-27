package com.github.eloyzone.sokobanjavafxgame.menu;

import com.github.eloyzone.sokobanjavafxgame.Main;
import com.github.eloyzone.sokobanjavafxgame.tile.LevelTile;
import com.github.eloyzone.sokobanjavafxgame.util.Fade;
import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private static int MAX_ROW_COUNT = 3;
    private static int MAX_COLUMN_COUNT = 9;

    public void createStageAndShow()
    {
        int numberOfMaps = 17;

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(1050, 600);

        ImageView imageView = new ImageView(ImageLoader.getImageBrickWall());
        imageView.setFitWidth(stackPane.getPrefWidth());
        imageView.setFitHeight(stackPane.getPrefHeight());

        stackPane.getChildren().add(imageView);

        VBox vBox = new VBox();

        Title menuTitle = new Title("SELECT LEVEL", Color.BLACK);

        Button backButtonToMainMenu = new Button("<-");
        backButtonToMainMenu.setId("back-button-black");

        Pane paneLevelTilesContainer = new Pane();

        LevelTile[][] levelTiles = new LevelTile[MAX_ROW_COUNT][MAX_COLUMN_COUNT];

        for (int i = 0; i < MAX_ROW_COUNT; i++)
        {
            for (int j = 0; j < MAX_COLUMN_COUNT; j++)
            {
                if (numberOfMaps == 0)
                    break;

                int levelNumber = ((i) * MAX_COLUMN_COUNT) + j + 1;

                levelTiles[i][j] = new LevelTile(String.valueOf(levelNumber), (100 + 10) * j + 30, (100 + 10) * i);
                paneLevelTilesContainer.getChildren().addAll(levelTiles[i][j]);
                numberOfMaps--;
            }
        }

        paneLevelTilesContainer.setPrefHeight((MAX_ROW_COUNT) * 100);

        VBox.setMargin(menuTitle, new Insets(80, 0, 40, 0));
        VBox.setMargin(backButtonToMainMenu, new Insets(40, 0, 0, 5));

        backButtonToMainMenu.setOnAction(e -> backToMainMenu());

        vBox.getChildren().addAll(menuTitle, paneLevelTilesContainer, backButtonToMainMenu);
        stackPane.getChildren().addAll(vBox);

        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add(getClass().getResource("/menu_level_selector_menu.css").toExternalForm());

        menuLevelSelectorStage = new Stage();
        menuLevelSelectorStage.initStyle(StageStyle.UNDECORATED);
        menuLevelSelectorStage.setScene(scene);
        Fade.fadeInTransitionForScene(menuLevelSelectorStage);
        menuLevelSelectorStage.show();
    }

    private void backToMainMenu()
    {
        menuLevelSelectorStage.close();
        Main.mainStage.show();
    }
}
