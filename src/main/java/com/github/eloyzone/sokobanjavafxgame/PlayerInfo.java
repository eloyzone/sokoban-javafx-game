package com.github.eloyzone.sokobanjavafxgame;

import java.io.Serializable;

public class PlayerInfo implements Serializable
{
    private int lastPassedLevel = 0;

    public int getLastPassedLevel()
    {
        return lastPassedLevel;
    }

    public boolean grantLastPassedLevel(int newPassedLevel)
    {
        if (newPassedLevel > lastPassedLevel)
        {
            lastPassedLevel = newPassedLevel;
            return true;
        }
        return false;
    }
}
