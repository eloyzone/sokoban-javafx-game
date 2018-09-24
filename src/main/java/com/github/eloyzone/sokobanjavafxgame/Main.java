package com.github.eloyzone.sokobanjavafxgame;

import com.github.eloyzone.sokobanjavafxgame.menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setScene(new Scene(new MainMenu().createContent()));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mainStage = primaryStage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
