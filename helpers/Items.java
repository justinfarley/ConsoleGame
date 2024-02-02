package helpers;

import java.util.Random;

import main_game.player.inventory.items.*;
import main_game.player.inventory.items.consumables.AttackPotion;
import main_game.player.inventory.items.consumables.SimplePotion;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.Branch;
import main_game.player.inventory.items.weapons.level_0.Stick;
import main_game.player.inventory.items.weapons.level_0.WoodenSword;
import main_game.player.inventory.items.weapons.level_1.BrassKnuckles;
import main_game.player.inventory.items.weapons.level_1.IronHammer;
import main_game.player.inventory.items.weapons.level_2.Brick;
import main_game.player.inventory.items.weapons.level_2.PlatedAxe;
import main_game.player.inventory.items.weapons.level_3.Gambler;
import main_game.player.inventory.items.weapons.level_3.WoodenBat;

public class Items {

    private static final Random RAND = new Random();

    private Items(){

    }
    protected static final Item[] LEVEL_0 = 
    {
        Stick.getPreset(),
        Branch.getPreset(),
        WoodenSword.getPreset(),
        SimplePotion.getPreset(),
    };
    protected static final Item[] LEVEL_1 = 
    {
        BrassKnuckles.getPreset(),
        IronHammer.getPreset(),
        SimplePotion.getPreset(),

    };
    protected static final Item[] LEVEL_2 = 
    {
        PlatedAxe.getPreset(),
        Brick.getPreset(),
        SimplePotion.getPreset(),
    };
    protected static final Item[] LEVEL_3 = 
    {
        WoodenBat.getPreset(),
        SimplePotion.getPreset(),
    };
    protected static final Item[][] ALL_ITEMS_FOR_LEVEL = 
    {
        LEVEL_0,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
    };
    //ALL ITEMS IN THE GAME
    protected static final Item[] ALL_ITEMS = 
    {
        Stick.getPreset(),
        Branch.getPreset(),
        WoodenSword.getPreset(),
        SimplePotion.getPreset(),
        AttackPotion.getPreset(),
        IronHammer.getPreset(),
        PlatedAxe.getPreset(),
        BrassKnuckles.getPreset(),
        Brick.getPreset(),
        WoodenBat.getPreset(),
    };

    protected static final Weapon[] ALL_WEAPONS = 
    {
        Stick.getPreset(),
        Branch.getPreset(),
        WoodenSword.getPreset(),
        IronHammer.getPreset(),
        PlatedAxe.getPreset(),
        BrassKnuckles.getPreset(),
        Brick.getPreset(),
        Gambler.getPreset(),
        WoodenBat.getPreset(),
    };
    /**
     * 
     * @return a random item out of ALL ITEMS including weapons
     */
    public static Item getRandomItem(){
        return ALL_ITEMS[RAND.nextInt(ALL_ITEMS.length)];
    }
    /**
     * @return a random WEAPON (not including items)
     */
    public static Weapon getRandomWeapon(){
        return ALL_WEAPONS[RAND.nextInt(ALL_WEAPONS.length)];
    }
    public static Weapon getWeapon(String name){
        for(Weapon w : ALL_WEAPONS){
            if(w.name.equalsIgnoreCase(name)){
                return w;
            }
        }
        return null;
    }
    /**
     * get random ITEM (includes weapons) from the given level
     * @param l players level
     * @return
     */
    public static Item getRandomItemFromLevel(int l){
        return ALL_ITEMS_FOR_LEVEL[l][RAND.nextInt(ALL_ITEMS_FOR_LEVEL[l].length)];
    }
    // public static Consumable getRandomConsumable(){
    //     return ALL_CONSUMABLES[RAND.nextInt(ALL_CONSUMABLES.length)];
    // }
    public static Item getItem(String name) {
        for(Item w : ALL_ITEMS){
            if(w.name.equalsIgnoreCase(name)){
                return w;
            }
        }
        return null;
    }
}
