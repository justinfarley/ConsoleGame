package helpers;

import java.util.Random;

import main_game.player.inventory.items.*;
import main_game.player.inventory.items.consumables.*;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.*;
import main_game.player.inventory.items.weapons.level_1.*;
import main_game.player.inventory.items.weapons.level_2.*;
import main_game.player.inventory.items.weapons.level_3.*;
import main_game.player.inventory.items.weapons.level_4.*;
import main_game.player.inventory.items.weapons.level_5.*;

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
        WoodenSword.getPreset(),
        IronHammer.getPreset(),
        FrozenSausage.getPreset(),
        SimplePotion.getPreset(),
        BowAndArrow.getPreset(),
    };
    protected static final Item[] LEVEL_2 = 
    {
        SimplePotion.getPreset(),
        BrassKnuckles.getPreset(),
        PlatedAxe.getPreset(),
        Brick.getPreset(),
        RustedSpear.getPreset(),
        MediocrePotion.getPreset(),
        Longbow.getPreset(),
    };
    protected static final Item[] LEVEL_3 = 
    {
        SimplePotion.getPreset(),
        RustedSpear.getPreset(),
        Gambler.getPreset(),
        Brick.getPreset(),
        WoodenBat.getPreset(),
        MediocrePotion.getPreset(),
        Crossbow.getPreset(),
    };
    protected static final Item[] LEVEL_4 = 
    {
        Gambler.getPreset(),
        ToyGun.getPreset(),
        WoodenBat.getPreset(),
        MediocrePotion.getPreset(),
        SharpKey.getPreset(),
        Club.getPreset(),
    };
    protected static final Item[] LEVEL_5 = 
    {
        MediocrePotion.getPreset(),
        SharkGun.getPreset(),
        AdvancedPotion.getPreset(),
        SharpKey.getPreset(),
        Club.getPreset(),
    };
    public static final Item[][] ALL_ITEMS_FOR_LEVEL = 
    {
        LEVEL_0,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5,
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
        Club.getPreset(),
        SharpKey.getPreset(),
        SharkGun.getPreset(),
        ToyGun.getPreset(),
        Gambler.getPreset(),
        MetalBat.getPreset(),
        RustedSpear.getPreset(),
        FrozenSausage.getPreset(),
        MediocrePotion.getPreset(),
        AdvancedPotion.getPreset(),
        UltraPotion.getPreset(),
        ExoticPotion.getPreset(),
        BowAndArrow.getPreset(),
        Longbow.getPreset(),
        Crossbow.getPreset(),

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
        MetalBat.getPreset(),
        RustedSpear.getPreset(),
        FrozenSausage.getPreset(),
        ToyGun.getPreset(),
        Club.getPreset(),
        SharpKey.getPreset(),
        SharkGun.getPreset(),
        BowAndArrow.getPreset(),
        Longbow.getPreset(),
        Crossbow.getPreset(),
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
