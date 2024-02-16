package main_game;
import java.util.*;

import enemies.Goblin;
import helpers.Colors;
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
import world_generation.ItemPedestal;
import world_generation.Tile;
import world_generation.Tree;
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
        player.setGold(saveData.gold);
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
        player.equipWeapon(new Stick());
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

        DialogueHelper.sayTextln("Welcome to the game " + Colors.YELLOW + player.getName() + Colors.RESET + ".", TEXT_SPEED, false);
        //DialogueHelper.waitForMillis(500);
        DialogueHelper.sayTextln("How large would you like to make your world, Small(1), Medium(2), Large(3), or XLarge(4)?", TEXT_SPEED, false);
        
        int answer = scan.nextInt();
        map.chooseWorldSize(pickSize(answer));
        map.generate(player);
        //TODO: remove
        DialogueHelper.sayText("Generating World.....", (long)GameLoop.TEXT_SPEED * answer, true);
        DialogueHelper.sayTextln("Generating World.....", (long)GameLoop.TEXT_SPEED * answer, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("You have spawned into a new world. ", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("You look around and see you are lost in the wilderness.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("You see lots of " + new Grass().toString() + " GRASS around, as well as " + new Tree().toString() + " TREES.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("In the distance you see " + new ItemPedestal().toString() + " ITEM PEDESTALS. You can obtain various Items, Weapons, and Consumables from these ancient pedestals.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("These pedestals will provide better items the higher your " + Colors.PURPLE + "Level" + Colors.RESET + " is.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("As you wander the wilderness, more and more " + new Goblin("").toString() + " ENEMIES will appear.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("Kill them for " + Colors.PURPLE + "experience" + Colors.RESET + " in order to " + Colors.PURPLE + "Level Up" + Colors.RESET + ".", 50, false);
        DialogueHelper.waitForMillis(1000);
        
        DialogueHelper.sayTextln("Here are your stats: ", 50, false);
        System.out.println(player.getPlayerStatSheet());
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("You have started off with three " + Colors.BLUE + "Simple Potions " + Colors.RESET + "as well as a " + Colors.GREEN + "Stick" + Colors.RESET + " to defend yourself.", 50, false);
        DialogueHelper.waitForMillis(1000);
        player.getInventory().viewInventory();
        DialogueHelper.sayTextln("To start off, type HELP for a list of commands.", 50, false);
        DialogueHelper.waitForMillis(1000);
        DialogueHelper.sayTextln("Our system will autocomplete your actions for you, so feel free to only type the first few letters, or key part of your entry.", 50, false);
        DialogueHelper.waitForMillis(1000);
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
