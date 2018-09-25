package com.github.eloyzone.sokobanjavafxgame;

import com.github.eloyzone.sokobanjavafxgame.tile.AbstractTile;
import com.github.eloyzone.sokobanjavafxgame.tile.PathTile;
import com.github.eloyzone.sokobanjavafxgame.tile.TargetTile;
import com.github.eloyzone.sokobanjavafxgame.tile.WallTile;
import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Board
{
    private Stage boardStage;
    private Pane paneRootBoard;

    private AbstractTile[][] tiles;
    private SokobanToken sokobanToken;

    private ArrayList<TargetTile> targetTiles = new ArrayList<>();
    private ArrayList<BoxToken> boxTokens = new ArrayList<>();


    public Board(int levelNumber)
    {
        readMap(levelNumber);
    }

    public Stage getScene()
    {
        boardStage = new Stage();

        HBox hBox = new HBox();
        VBox vBox = new VBox();

        hBox.setPrefSize(1050, 600);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(paneRootBoard);
        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox, Color.LIGHTGRAY);

        boardStage.setScene(scene);
        boardStage.initStyle(StageStyle.UNDECORATED);

        return boardStage;
    }


    private void readMap(int levelNumber)
    {
        paneRootBoard = new Pane();

        int rowCount;
        int columnCount;

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("levels/test" + levelNumber + ".txt")));
            String result = bufferedReader.readLine();

            int i = 0;
            int temp = 0;
            while (result != null)
            {
                if (temp == 0)
                {
                    String s = result;
                    String s1[] = s.split(" ");
                    rowCount = Integer.parseInt(s1[0]);
                    columnCount = Integer.parseInt(s1[1]);

                    paneRootBoard.setPrefSize(columnCount * 50, rowCount * 50);

                    temp++;

                    tiles = new AbstractTile[rowCount][columnCount];
                }
                result = bufferedReader.readLine();
                if (result == null)
                    continue;
                char[] array = result.toCharArray();
                for (int j = 0; j < array.length; j++)
                {
                    if (array[j] == 'S')
                    {
                        sokobanToken = new SokobanToken(i, j);
                        tiles[i][j] = new PathTile(i, j, 50 * j, 50 * i, sokobanToken);
                    } else if (array[j] == 'X')
                    {
                        TargetTile targetTile = new TargetTile(i, j, 50 * j, 50 * i);
                        targetTiles.add(targetTile);
                        tiles[i][j] = targetTile;
                    } else if (array[j] == '@')
                    {
                        BoxToken boxToken = new BoxToken(i, j);
                        boxTokens.add(boxToken);
                        tiles[i][j] = new PathTile(i, j, 50 * j, 50 * i, boxToken);
                    } else if (array[j] == '#')
                    {
                        tiles[i][j] = new WallTile(i, j, 50 * j, 50 * i);
                    } else if (array[j] == '.')
                    {
                        tiles[i][j] = new PathTile(i, j, 50 * j, 50 * i);
                    } else
                    {
                        throw new Exception("Warning: the input is wrong your not able to continue");
                    }
                    paneRootBoard.getChildren().addAll(tiles[i][j]);
                }
                i++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
