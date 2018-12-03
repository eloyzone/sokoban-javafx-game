# Sokoban JavaFX Game
It is a Sokoban game which is implemented by JavaFX.

![alt text][logo]

[logo]: http://eloy.zone/static/sokoban-fx-web.gif "Screen Capture of App"


# About Sokoban game
Sokoban (倉庫番 sōko-ban, "warehouse keeper") is a type of puzzle video game, in which the player pushes boxes or crates around in a warehouse, trying to get them to storage locations.

Sokoban was created in 1981 by Hiroyuki Imabayashi, and published in December 1982 by Thinking Rabbit, a software house based in Takarazuka, Japan.

Source: [wikipedia] 



# Requirements
* Maven 3.5 (older versions might work too)
* Java Developer Kit 8 with at least Update 40


# How can I deploy it?
There are plenty of ways for deploying a JavaFX application (especially through your preferable IDE) but I have chosen one of the easiest one. You can use following command to deploy your application. Bottom-mentioned maven command is a plugin that helps you to deploy it easily.

```xml
<plugin>
    <groupId>com.zenjava</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>8.8.3</version>
    <configuration>
        <mainClass>com.github.eloyzone.sokobanjavafxgame.Main</mainClass>
    </configuration>
</plugin>
```
To create your executable file with JavaFX-magic, call `mvn jfx:jar`. The jar-file will be placed at `target/jfx/app`.

More information about plugin: [javafx-maven-plugin]




[wikipedia]: https://en.wikipedia.org/wiki/Sokoban
[javafx-maven-plugin]: https://github.com/javafx-maven-plugin/javafx-maven-plugin
