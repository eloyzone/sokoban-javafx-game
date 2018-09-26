package com.github.eloyzone.sokobanjavafxgame;

import com.github.eloyzone.sokobanjavafxgame.menu.GamePauseMenu;
import com.github.eloyzone.sokobanjavafxgame.menu.GameStatusMenu;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import com.github.eloyzone.sokobanjavafxgame.tile.AbstractTile;
import com.github.eloyzone.sokobanjavafxgame.tile.PathTile;
import com.github.eloyzone.sokobanjavafxgame.tile.TargetTile;
import com.github.eloyzone.sokobanjavafxgame.tile.WallTile;
import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.util.Fade;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.ESCAPE;

public class Board
{
    private Stage boardStage;
    private Pane paneRootBoard;
    private Stage pauseMenu;

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

        pauseMenu = createPauseMenu();
        Scene scene = new Scene(hBox, Color.LIGHTGRAY);

        createKeyboardHandlers(scene);
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

    private void createKeyboardHandlers(Scene scene)
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case UP:
                        moveSokobanUp();
                        break;
                    case DOWN:
                        moveSokobanDown();
                        break;
                    case LEFT:
                        moveSokobanLeft();
                        break;
                    case RIGHT:
                        moveSokobanRight();
                        break;
                    case ESCAPE:
                        if (!pauseMenu.isShowing())
                            pauseMenu.show();
                        else
                            pauseMenu.close();
                        break;
                }
            }
        });
    }

    private void moveSokobanUp()
    {
        sokobanToken.changeImageToUp();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow - 1][sokobanColumn] instanceof WallTile)
        {
            System.out.println("can't move");
        } else if ((tiles[sokobanRow - 1][sokobanColumn] instanceof PathTile) ||
                (tiles[sokobanRow - 1][sokobanColumn] instanceof TargetTile))
        {
            if (!tiles[sokobanRow - 1][sokobanColumn].isTokenOnIt())
            {
                tiles[sokobanRow][sokobanColumn].removeSokoban();
                tiles[sokobanRow - 1][sokobanColumn].addSokobanToken(sokobanToken);
            } else
            {
                moveBoxToUP(sokobanRow, sokobanColumn);
            }
        }
    }

    private void moveSokobanDown()
    {
        sokobanToken.changeImageToDown();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow + 1][sokobanColumn] instanceof WallTile)
        {
            System.out.println("can't move");
        } else if ((tiles[sokobanRow + 1][sokobanColumn] instanceof PathTile) || (tiles[sokobanRow + 1][sokobanColumn] instanceof TargetTile))
        {
            if (!tiles[sokobanRow + 1][sokobanColumn].isTokenOnIt())
            {
                tiles[sokobanRow][sokobanColumn].removeSokoban();
                tiles[sokobanRow + 1][sokobanColumn].addSokobanToken(sokobanToken);
            } else
            {
                moveBoxToDown(sokobanRow, sokobanColumn);
            }
        }
    }

    private void moveSokobanLeft()
    {
        sokobanToken.changeImageToLeft();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow][sokobanColumn - 1] instanceof WallTile)
        {
            System.out.println("can't move");
        } else if ((tiles[sokobanRow][sokobanColumn - 1] instanceof PathTile) || (tiles[sokobanRow][sokobanColumn - 1] instanceof TargetTile))
        {
            if (!tiles[sokobanRow][sokobanColumn - 1].isTokenOnIt())
            {
                tiles[sokobanRow][sokobanColumn].removeSokoban();
                tiles[sokobanRow][sokobanColumn - 1].addSokobanToken(sokobanToken);
            } else
            {
                moveBoxToLeft(tiles[sokobanRow], sokobanColumn);
            }
        }
    }

    private void moveSokobanRight()
    {
        sokobanToken.changeImageToRight();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow][sokobanColumn + 1] instanceof WallTile)
        {
            System.out.println("can't move");
        } else if ((tiles[sokobanRow][sokobanColumn + 1] instanceof PathTile) || (tiles[sokobanRow][sokobanColumn + 1] instanceof TargetTile))
        {
            if (!tiles[sokobanRow][sokobanColumn + 1].isTokenOnIt())
            {
                tiles[sokobanRow][sokobanColumn].removeSokoban();
                tiles[sokobanRow][sokobanColumn + 1].addSokobanToken(sokobanToken);
            } else
            {
                moveBoxToRight(tiles[sokobanRow], sokobanColumn);
            }
        }
    }

    private void moveBoxToRight(AbstractTile[] tile, int sokobanColumn)
    {
        if ((tile[sokobanColumn + 2] instanceof PathTile && !tile[sokobanColumn + 2].isTokenOnIt())
                || (tile[sokobanColumn + 2] instanceof TargetTile && !tile[sokobanColumn + 2].isTokenOnIt()))
        {
            tile[sokobanColumn].removeSokoban();
            BoxToken boxToken = tile[sokobanColumn + 1].getBoxToken();
            tile[sokobanColumn + 1].removeBoxToken();
            tile[sokobanColumn + 1].addSokobanToken(sokobanToken);
            tile[sokobanColumn + 2].addBoxToken(boxToken);
            isSolved();
        }
    }

    private void moveBoxToLeft(AbstractTile[] tile, int sokobanColumn)
    {
        if ((tile[sokobanColumn - 2] instanceof PathTile && !tile[sokobanColumn - 2].isTokenOnIt()) ||
                (tile[sokobanColumn - 2] instanceof TargetTile && !tile[sokobanColumn - 2].isTokenOnIt()))
        {
            tile[sokobanColumn].removeSokoban();
            BoxToken boxToken = tile[sokobanColumn - 1].getBoxToken();
            tile[sokobanColumn - 1].removeBoxToken();
            tile[sokobanColumn - 1].addSokobanToken(sokobanToken);
            tile[sokobanColumn - 2].addBoxToken(boxToken);
            isSolved();
        }
    }

    private void moveBoxToDown(int sokobanRow, int sokobanColumn)
    {
        if ((tiles[sokobanRow + 2][sokobanColumn] instanceof PathTile && !tiles[sokobanRow + 2][sokobanColumn].isTokenOnIt()) ||
                (tiles[sokobanRow + 2][sokobanColumn] instanceof TargetTile && !tiles[sokobanRow + 2][sokobanColumn].isTokenOnIt()))
        {
            tiles[sokobanRow][sokobanColumn].removeSokoban();
            BoxToken boxToken = tiles[sokobanRow + 1][sokobanColumn].getBoxToken();
            tiles[sokobanRow + 1][sokobanColumn].removeBoxToken();
            tiles[sokobanRow + 1][sokobanColumn].addSokobanToken(sokobanToken);
            tiles[sokobanRow + 2][sokobanColumn].addBoxToken(boxToken);
            isSolved();
        }
    }

    private void moveBoxToUP(int sokobanRow, int sokobanColumn)
    {
        if ((tiles[sokobanRow - 2][sokobanColumn] instanceof PathTile && !tiles[sokobanRow - 2][sokobanColumn].isTokenOnIt())
                || (tiles[sokobanRow - 2][sokobanColumn] instanceof TargetTile && !tiles[sokobanRow - 2][sokobanColumn].isTokenOnIt()))
        {
            tiles[sokobanRow][sokobanColumn].removeSokoban();
            BoxToken boxToken = tiles[sokobanRow - 1][sokobanColumn].getBoxToken();
            tiles[sokobanRow - 1][sokobanColumn].removeBoxToken();
            tiles[sokobanRow - 1][sokobanColumn].addSokobanToken(sokobanToken);
            tiles[sokobanRow - 2][sokobanColumn].addBoxToken(boxToken);
            isSolved();
        }
    }

    private void isSolved()
    {
        boolean solved = true;

        for (TargetTile targetTile : targetTiles)
        {
            if (targetTile.getBoxToken() == null)
            {
                solved = false;
                break;
            }
        }

        if (solved)
        {
            Stage gameStatusStage = new Stage();

            Scene scene = new Scene(new GameStatusMenu().createContent(gameStatusStage, boardStage));

            gameStatusStage.setScene(scene);
            gameStatusStage.initModality(Modality.APPLICATION_MODAL);
            gameStatusStage.initStyle(StageStyle.UNDECORATED);
            Fade.fadeInTransitionForScene(gameStatusStage);
            gameStatusStage.show();
        }
    }

    private Stage createPauseMenu()
    {
        Stage pauseStage = new Stage();

        Scene scene = new Scene(new GamePauseMenu().createContent(pauseStage, boardStage));
        pauseStage.setScene(scene);
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.initStyle(StageStyle.UNDECORATED);

        scene.setOnKeyPressed(event ->
        {
            if (event.getCode() == ESCAPE)
                pauseStage.close();
        });

        return pauseStage;
    }
}
