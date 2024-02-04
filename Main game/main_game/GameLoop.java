package main_game;
import java.util.*;

import helpers.DialogueHelper;
import helpers.Items;
import main_game.player.Experience;
import main_game.player.Player;
import main_game.player.inventory.items.consumables.SimplePotion;
import main_game.player.inventory.items.weapons.level_0.Stick;
import main_game.player.inventory.items.weapons.level_0.WoodenSword;
import save_system.PlayerData;
import save_system.SaveManager;
import world_generation.Grass;
import world_generation.Tile;
import world_generation.WorldMap;
import world_generation.WorldMap.Size;

public class GameLoop {
    private State state;
    private Scanner scan = new Scanner(System.in);
    private static final int STARTING_PLAYER_HEALTH = 15;
    private static Player player = new Player(STARTING_PLAYER_HEALTH);
    private static WorldMap map = new WorldMap();
    public static final int TEXT_SPEED  = 15; //in milliseconds
    public GameLoop(){
        state = new State(this);
    }
    public void startGame(PlayerData saveData) throws InterruptedException {
        //manually set all this crap bc the json to object is so strict on types :(
        player.equipWeapon(Items.getWeapon(saveData.equippedWeaponName));
        player.setHealth(saveData.health);
        player.setMaxHealth(saveData.maxHealth);
        player.setName(saveData.name);
        player.getExperience().setExp(saveData.currentExp);
        player.getInventory().setInventory(saveData.inventoryItems);
        player.getExperience().setLevel(saveData.level);
        player.getExperience().setMaxExp(Experience.EXP_GOAL_LIST[saveData.level]);
        player.setCoords(saveData.playerPosition[0], saveData.playerPosition[1]);
        player.setSpeed(saveData.speed);
        player.setTileUnderPlayer(new Grass());
        //load the world map in too from the file
        map.setWorldSize(saveData.worldSize);
        Tile[][] ogMap = map.convertNamesToTiles(saveData.mapOfNames);
        ogMap[saveData.playerPosition[0]][saveData.playerPosition[1]] = player;
        map.setWorldMap(ogMap);
        System.out.println(player.getPlayerStatSheet());
        player.getInventory().viewInventory();
        tick();
    }
    public void startGame() throws InterruptedException {
        //STARTING WEAPON
        player.equipWeapon(new Stick("Stick"));
        //STARTING POTIONS
        player.getInventory().addItem(SimplePotion.getPreset()); 
        player.getInventory().addItem(SimplePotion.getPreset());
        player.getInventory().addItem(SimplePotion.getPreset());

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
        
        //player.setName(name);
        player.setName(name.toUpperCase());

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
