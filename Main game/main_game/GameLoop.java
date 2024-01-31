package main_game;
import java.util.*;

import helpers.DialogueHelper;
import main_game.player.Player;
import main_game.player.inventory.items.weapons.level_0.Stick;
import main_game.player.inventory.items.weapons.level_0.WoodenSword;
import world_generation.WorldMap;
import world_generation.WorldMap.Size;

public class GameLoop {
    private State state;
    private Scanner scan = new Scanner(System.in);
    private static Player player = new Player("FlatHippo", 10, new int[]{0, 0});
    private static WorldMap map = new WorldMap();
    public static final int TEXT_SPEED  = 15; //in milliseconds
    public GameLoop(){
        state = new State(this);
    }
    public void startGame() throws InterruptedException {
        player.equipWeapon(new Stick("Stick"));
        DialogueHelper.sayText("Hello traveler! Enter your name here: ", TEXT_SPEED, false);
        String name = scan.nextLine();
        while(name.length() <= 2 || name.length() > 15){
            if(name.length() <= 2)
                DialogueHelper.sayTextln("Name is too short! (" + name.length() + " / 3)", TEXT_SPEED, false);
            else if(name.length() > 15)
                DialogueHelper.sayTextln("Name is too long! (" + name.length() + " / 15)", TEXT_SPEED, false);
            DialogueHelper.sayTextln("Enter your name here: ", TEXT_SPEED, false);
            name = scan.nextLine();
        }
        
        player.setName(name);
        
        DialogueHelper.sayTextln("Welcome to the game " + player.getName() + ".", TEXT_SPEED, false);
        //DialogueHelper.waitForMillis(500);
        DialogueHelper.sayTextln("How large would you like to make your world, Small(1), Medium(2), Large(3), or XLarge(4)?", TEXT_SPEED, false);
        
        int answer = scan.nextInt();
        map.chooseWorldSize(pickSize(answer));
        map.generate(player);
        //TODO: remove
        DialogueHelper.sayText("Generating World.....", (long)GameLoop.TEXT_SPEED * answer, true);
        DialogueHelper.sayTextln("Generating World.....", (long)GameLoop.TEXT_SPEED * answer, false);
        
        tick();
    }
    private Size pickSize(int answer){
        switch(answer){
            case 1:
                return Size.SMALL;
            case 2:
                return Size.MEDIUM;
            case 3:
                return Size.LARGE;
            case 4:
                return Size.XLARGE;
            default:
                return Size.XLARGE;
        }
    }
    public void tick() throws InterruptedException{
        //game logic
        state.stateUpdate();
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public static WorldMap getMap(){
        return map;
    }
    public static Player getPlayer(){
        return player;
    }
}
