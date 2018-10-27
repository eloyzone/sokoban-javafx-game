package com.github.eloyzone.sokobanjavafxgame;

import com.github.eloyzone.sokobanjavafxgame.menu.GamePauseMenu;
import com.github.eloyzone.sokobanjavafxgame.menu.GameStatusMenu;
import com.github.eloyzone.sokobanjavafxgame.tile.AbstractTile;
import com.github.eloyzone.sokobanjavafxgame.tile.PathTile;
import com.github.eloyzone.sokobanjavafxgame.tile.TargetTile;
import com.github.eloyzone.sokobanjavafxgame.tile.WallTile;
import com.github.eloyzone.sokobanjavafxgame.token.BoxToken;
import com.github.eloyzone.sokobanjavafxgame.token.SokobanToken;
import com.github.eloyzone.sokobanjavafxgame.util.ImageLoader;
import com.github.eloyzone.sokobanjavafxgame.util.PlayerInfoWriterReader;
import com.github.eloyzone.sokobanjavafxgame.util.SoundLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Board
{
    private StackPane stackPaneBoard;
    private Pane paneRootBoard;
    private AbstractTile[][] tiles;
    private SokobanToken sokobanToken;
    private int levelNumber;

    private Parent nodePauseMenu = null;
    private static Boolean pauseMenuShown = false;

    private ArrayList<TargetTile> targetTiles = new ArrayList<>();
    private ArrayList<BoxToken> boxTokens = new ArrayList<>();

    private String remainingTime = "60";
    private Text textTime = new Text("Time: ");
    private Timeline sixtySecondsTimeLine;

    private Text moveTimes = new Text("Move: 0 Time");
    private Text gameLevelText;

    private int numberOfMoveTimes = 0;


    public Board(int levelNumber)
    {
        this.levelNumber = levelNumber;
        readMap(this.levelNumber);
        gameLevelText = new Text("Level " + levelNumber);
    }

    public StackPane getScene()
    {
        pauseMenuShown = false;

        stackPaneBoard = new StackPane();

        ImageView imageViewBackground = new ImageView(ImageLoader.getImageWarehouse());
        imageViewBackground.setFitHeight(600);
        imageViewBackground.setFitWidth(1050);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.6);
        imageViewBackground.setEffect(colorAdjust);

        HBox hBox = new HBox();
        VBox vBox = new VBox();

        hBox.setPrefSize(1050, 600);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(paneRootBoard);

        VBox vBoxLevelStatus = new VBox();
        vBoxLevelStatus.setAlignment(Pos.CENTER);

        gameLevelText.setFill(Color.WHITE);
        gameLevelText.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        textTime.setText("Time: " + remainingTime);
        textTime.setFill(Color.WHITE);
        textTime.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 40));


        moveTimes.setFill(Color.WHITE);
        moveTimes.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

        vBoxLevelStatus.getChildren().addAll(gameLevelText, textTime, moveTimes);

        hBox.getChildren().addAll(vBoxLevelStatus, vBox);
        vBoxLevelStatus.setPadding(new Insets(0, 50, 0, 0));

        stackPaneBoard.getChildren().addAll(imageViewBackground, hBox);

        stackPaneBoard.setFocusTraversable(true);

        createKeyboardHandlers(stackPaneBoard);

        return stackPaneBoard;
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

    private void createKeyboardHandlers(StackPane scene)
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                System.out.println("here");
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
                        if (pauseMenuShown == false)
                            showPauseMenu();
                        else
                        {
                            stackPaneBoard.getChildren().remove(stackPaneBoard.getChildren().size() - 1);
                            pauseMenuShown = false;
                        }
                        break;
                }
            }
        });
    }

    private void moveSokobanUp()
    {
        increaseNumberOfMovementOfSokoban();
        sokobanToken.changeImageToUp();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow - 1][sokobanColumn] instanceof WallTile)
        {
            System.out.println("can't move");
            SoundLoader.playCrashSound();
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
        increaseNumberOfMovementOfSokoban();
        sokobanToken.changeImageToDown();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow + 1][sokobanColumn] instanceof WallTile)
        {
            System.out.println("can't move");
            SoundLoader.playCrashSound();
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
        increaseNumberOfMovementOfSokoban();
        sokobanToken.changeImageToLeft();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow][sokobanColumn - 1] instanceof WallTile)
        {
            System.out.println("can't move");
            SoundLoader.playCrashSound();
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
        increaseNumberOfMovementOfSokoban();
        sokobanToken.changeImageToRight();

        int sokobanRow = sokobanToken.getRow();
        int sokobanColumn = sokobanToken.getColumn();

        if (tiles[sokobanRow][sokobanColumn + 1] instanceof WallTile)
        {
            System.out.println("can't move");
            SoundLoader.playCrashSound();
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

    private void increaseNumberOfMovementOfSokoban()
    {
        String moveTimesString = "";
        numberOfMoveTimes++;
        if (numberOfMoveTimes > 1)
        {
            moveTimesString = "Move: " + numberOfMoveTimes + " Times";
        } else
        {
            moveTimesString = "Move: " + numberOfMoveTimes + " Time";
        }
        moveTimes.setText(moveTimesString);
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
        } else
        {
            SoundLoader.playCrashSound();
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
        } else
        {
            SoundLoader.playCrashSound();
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
        } else
        {
            SoundLoader.playCrashSound();
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
        } else
        {
            SoundLoader.playCrashSound();
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
            sixtySecondsTimeLine.stop();

            Parent parent = new GameStatusMenu().createVictoryMenu(stackPaneBoard);
            stackPaneBoard.getChildren().add(parent);

            if (Main.getPlayerInfo().grantLastPassedLevel(levelNumber))
                new PlayerInfoWriterReader().write(Main.getPlayerInfo());
        }
    }

    private void showPauseMenu()
    {
        pauseMenuShown = true;
        nodePauseMenu = new GamePauseMenu().createContent(stackPaneBoard);
        stackPaneBoard.getChildren().add(nodePauseMenu);
    }

    public static void setPauseMenuFalse()
    {
        pauseMenuShown = false;
    }

    public static boolean getPauseMenuStatus()
    {
        return pauseMenuShown;
    }

    public void startTimer()
    {
        sixtySecondsTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                int remainingTimeValue = Integer.parseInt(remainingTime);
                remainingTimeValue--;
                remainingTime = String.valueOf(remainingTimeValue);
                textTime.setText("Time: " + remainingTime);

                if (remainingTimeValue == 0)
                {
                    sixtySecondsTimeLine.stop();
                    Parent parent = new GameStatusMenu().createLoseMenu(stackPaneBoard);
                    stackPaneBoard.getChildren().add(parent);
                }
            }
        }));
        sixtySecondsTimeLine.setCycleCount(Timeline.INDEFINITE);
        sixtySecondsTimeLine.play();
    }
}
