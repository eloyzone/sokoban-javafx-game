package com.github.eloyzone.sokobanjavafxgame;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event
{
    public static final EventType<GameEvent> RESUME_GAME = new EventType<>(Event.ANY, "RESUME_GAME");
    public static final EventType<GameEvent> RESTART_LEVEL = new EventType<>(Event.ANY, "RESTART_LEVEL");
    public static final EventType<GameEvent> END_GAME_LEVEL = new EventType<>(Event.ANY, "END_GAME_LEVEL");

    public GameEvent(EventType<? extends Event> eventType)
    {
        super(eventType);
    }
}
