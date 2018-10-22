package com.github.eloyzone.sokobanjavafxgame.util;

import com.github.eloyzone.sokobanjavafxgame.PlayerInfo;

import java.io.*;

public class PlayerInfoWriterReader
{
    private final static String playerInfoObjectFileName = "sokoban.eloyz";

    public void write(PlayerInfo playerInfo)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream(new File(playerInfoObjectFileName));
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(playerInfo);
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        } catch (IOException e)
        {
            System.out.println("Error initializing stream");
        } finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (objectOutputStream != null)
            {
                try
                {
                    objectOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public PlayerInfo read()
    {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        PlayerInfo playerInfo = null;

        try
        {
            fileInputStream = new FileInputStream(new File(playerInfoObjectFileName));
            objectInputStream = new ObjectInputStream(fileInputStream);

            playerInfo = (PlayerInfo) objectInputStream.readObject();

        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        } catch (IOException e)
        {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e)
        {
            System.out.println("class has not found exception");
        } finally
        {
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (objectInputStream != null)
            {
                try
                {
                    objectInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return playerInfo;
    }
}
