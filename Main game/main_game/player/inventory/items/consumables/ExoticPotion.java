package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 500-750
 * Price: 250
 * Worth: 200-250
 */
public class ExoticPotion extends SimplePotion {
    private static final Range WORTH = new Range(200, 251);
    public ExoticPotion(String n) {
        super(n, WORTH);
        rangeOfHealing = new Range(500, 751);
        price = 250;
    }
    public static ExoticPotion getPreset(){
        return new ExoticPotion("Exotic Potion");
    }
}
