package main_game;

import java.io.IOException;

/**
 * fun stuff to do: add funny death messages and choose random one each time u kill something (or die),
 *                  do the same as above with the "attacked you with" and "attacked the X with your" phrases. 
 *                      e.g. The Orcs slaps you around with his Stick..., You slapped around the Orc with your Stick..., The Orc fell on you with his Stick... bunch of those
 *                  make enemies drop gold (more gold per leftover damage perhaps)
 *                  make enemies despawn after certain # of movement opportunities (thanks fnaf)
 */
public class Main {
    public static final String FILE_PATH = "./MAP.txt";
    public static void main(String[] args) throws IOException{
        GameLoop game = new GameLoop();
        try{
            game.startGame();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //map.writeMapToFile("./MAP.txt");
        //map.print();
    }
}
