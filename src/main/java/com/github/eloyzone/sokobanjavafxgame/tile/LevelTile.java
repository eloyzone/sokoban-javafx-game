package com.github.eloyzone.sokobanjavafxgame.tile;

import com.github.eloyzone.sokobanjavafxgame.Board;
import com.github.eloyzone.sokobanjavafxgame.GameEvent;
import com.github.eloyzone.sokobanjavafxgame.Main;
import com.github.eloyzone.sokobanjavafxgame.menu.MenuLevelSelector;
import com.github.eloyzone.sokobanjavafxgame.util.Fade;
import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LevelTile extends StackPane
{
    int levelNumber;

    public LevelTile(String value, double translateX, double translateY)
    {
        setAlignment(Pos.CENTER);

        setPrefWidth(100);
        setPrefHeight(100);

        setTranslateX(translateX);
        setTranslateY(translateY);

        levelNumber = Integer.parseInt(value);

        Button button = new Button();
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setId("tile-level-dark-blue");

        if (levelNumber - 1 > Main.getPlayerInfo().getLastPassedLevel())
        {
            ImageView imageViewLockedLevel = new ImageView(ImageLoader.getImageLock());
            imageViewLockedLevel.setFitHeight(20);
            imageViewLockedLevel.setFitWidth(20);
            button.setGraphic(imageViewLockedLevel);
        } else
        {
            button.setText(value);
            button.setOnAction(e -> createAndShowBoardStage());
        }

        getChildren().addAll(button);
    }

    private void createAndShowBoardStage()
    {
        StackPane boardStackPane = new Board(levelNumber).getScene();

        boardStackPane.addEventFilter(GameEvent.END_GAME_LEVEL, event ->
        {
            StackPane stackPane = (StackPane) Main.mainStage.getScene().getRoot();
            stackPane.getChildren().remove(stackPane.getChildren().size() - 1);
            stackPane.getChildren().add(new MenuLevelSelector().createLevelSelectorStackPane());
        });

        boardStackPane.addEventFilter(GameEvent.RESTART_LEVEL, event ->
        {
            createAndShowBoardStage();
        });

        Fade.fadeInTransitionForScene(boardStackPane);

        StackPane stackPane = (StackPane) Main.mainStage.getScene().getRoot();
        stackPane.getChildren().remove(stackPane.getChildren().size() - 1);
        stackPane.getChildren().add(boardStackPane);

    }
}
