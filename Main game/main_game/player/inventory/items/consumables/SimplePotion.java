package main_game.player.inventory.items.consumables;

import helpers.DialogueHelper;
import helpers.Range;
import main_game.GameLoop;

/**
 * Range of healing: 10-20
 * Price: 10
 * Worth: 3-5
 */
public class SimplePotion extends Consumable {
    Range rangeOfHealing = new Range(10,21);
    private static final Range WORTH = new Range(3, 6);
    public SimplePotion(String n, Range worthRange) {
        super(n, worthRange);
        price = 10;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public void use() throws InterruptedException {
        if(GameLoop.getPlayer().getHealth() >= GameLoop.getPlayer().getMaxHealth()){
            DialogueHelper.sayTextln("You are at full health! (" + GameLoop.getPlayer().getHealth() + " / " + GameLoop.getPlayer().getMaxHealth() + ", potion not used.)", GameLoop.TEXT_SPEED, false);
            return;
        }
        int healAmount = rangeOfHealing.getNumberInRange();
        DialogueHelper.sayTextln("You drank the " + toString() + " for a gain of " + healAmount + " health!", GameLoop.TEXT_SPEED, false);
        GameLoop.getPlayer().gainHealth(healAmount);
        GameLoop.getPlayer().displayHealth();
        GameLoop.getPlayer().getInventory().removeItem(this);
    }
    public static SimplePotion getPreset(){
        return new SimplePotion("Simple Potion", WORTH);
    }
    
}
