package main_game;

import java.io.IOException;
import java.util.Scanner;

import helpers.DialogueHelper;
import save_system.PlayerData;
import save_system.SaveManager;
import helpers.DialogueHelper;

/**
 * fun stuff to do: add funny death messages and choose random one each time u kill something (or die),
 *                  do the same as above with the "attacked you with" and "attacked the X with your" phrases. 
 *                      e.g. The Orcs slaps you around with his Stick..., You slapped around the Orc with your Stick..., The Orc fell on you with his Stick... bunch of those
 *                  make enemies drop gold (more gold per leftover damage perhaps)
 *                  DONE: make enemies despawn after certain # of movement opportunities (thanks fnaf)
 *                  Autocomplete like in lethal company, if I type "change" and the only possible choice is change weapon it should work feel me
 *                  Bosses would be sick, purple instead of red, more health and stuff, never despawns or something idk
 *                  Save/Load system using a file
 *                  BESTIARY WOULD BE SO GAS, every time you fight a monster unlock their entry in the bestiary, can look through bestiary and see all monsters stats
 */
public class Main {
    public static final String FILE_PATH = "./MAP.txt";
    public static void main(String[] args) throws IOException{
        GameLoop game = new GameLoop();
        final Scanner SCAN = new Scanner(System.in);


        try{
            DialogueHelper.sayTextln("Would you like to start a new game or continue an old one(START/CONTINUE)?", 25, false);
            String answer = SCAN.nextLine();
            if(answer.equalsIgnoreCase("continue")){
                PlayerData data = SaveManager.loadGame();
                if(data == null)
                {
                    game.startGame();
                }
                else{
                    game.startGame(data);
                }
            }
            else{
                game.startGame();
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //map.writeMapToFile("./MAP.txt");
        //map.print();
    }
}
