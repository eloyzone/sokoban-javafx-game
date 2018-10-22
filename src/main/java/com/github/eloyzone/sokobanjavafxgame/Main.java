package com.github.eloyzone.sokobanjavafxgame;

import com.github.eloyzone.sokobanjavafxgame.menu.MainMenu;
import com.github.eloyzone.sokobanjavafxgame.util.PlayerInfoWriterReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    public static Stage mainStage;
    private static PlayerInfo playerInfo;

    @Override
    public void start(Stage primaryStage)
    {
        initializePlayerInfo();
        Scene scene = new Scene(new MainMenu().createContent());
        scene.getStylesheets().add(getClass().getResource("/menu_level_selector_menu.css").toExternalForm());
        primaryStage.setScene(scene);


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mainStage = primaryStage;
    }

    private void initializePlayerInfo()
    {
        PlayerInfoWriterReader  playerInfoWriterReader = new PlayerInfoWriterReader();
        PlayerInfo playerInfo = playerInfoWriterReader.read();
        if(playerInfo == null)
        {
            playerInfo = new PlayerInfo();
            playerInfoWriterReader.write(playerInfo);
        }

        this.playerInfo = playerInfo;

    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static PlayerInfo getPlayerInfo()
    {
        return playerInfo;
    }
}
