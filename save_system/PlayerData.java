package save_system;

import main_game.GameLoop;
import main_game.player.Player;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.weapons.level_3.WoodenBat;

public class PlayerData {

    public String name;
    public int level;
    public int health;
    public int maxHealth;
    public double speed;
    public int[] tileUnderPlayerPosition;
    public int[] playerPosition;
    public String equippedWeaponName;
    public String[] inventoryItems; //stores their names
    public double currentExp;
    public int worldSize;
    public String[][] mapOfNames;
    public int gold;

    public PlayerData(){

    }
    public PlayerData(Player p){
        health = p.getHealth();
        maxHealth = p.getMaxHealth();
        speed = p.getSpeed();
        tileUnderPlayerPosition = p.getTileUnder() != null ? p.getTileUnder().getCoords() : null;
        playerPosition = p.getCoords();
        equippedWeaponName = p.getWeapon().getName();
        currentExp = p.getExperience().getExp();
        name = p.getName();
        level = p.getExperience().getLevel();
        inventoryItems = new String[p.getInventory().getItems().size()];
        worldSize = GameLoop.getMap().getWorldSize();
        mapOfNames = GameLoop.getMap().getMapTileNames();
        gold = GameLoop.getPlayer().getGold();
        for(int i = 0; i < inventoryItems.length; i++){
            inventoryItems[i] = p.getInventory().getItems().get(i).getName();
        }
    }
}
