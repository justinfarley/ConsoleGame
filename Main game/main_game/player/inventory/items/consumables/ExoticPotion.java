package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 500-750
 */
public class ExoticPotion extends SimplePotion {

    public ExoticPotion(String n) {
        super(n);
        rangeOfHealing = new Range(500, 751);
    }
    public static ExoticPotion getPreset(){
        return new ExoticPotion("Exotic Potion");
    }
}
