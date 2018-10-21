package com.github.eloyzone.sokobanjavafxgame.util;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fade
{
    public static void fadeInTransitionForScene(Stage stage)
    {
        FadeTransition fadein = new FadeTransition(Duration.millis(500), stage.getScene().getRoot());
        fadein.setFromValue(0);
        fadein.setToValue(1);
        fadein.play();
    }

    public static void fadeInTransitionForScene(Node node)
    {
        FadeTransition fadein = new FadeTransition(Duration.millis(500), node);
        fadein.setFromValue(0);
        fadein.setToValue(1);
        fadein.play();
    }
}
