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
        Scene scene = new Scene(new MainMenu().createContent());
        scene.getStylesheets().add(getClass().getResource("/menu_level_selector_menu.css").toExternalForm());
        primaryStage.setScene(scene);


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mainStage = primaryStage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
