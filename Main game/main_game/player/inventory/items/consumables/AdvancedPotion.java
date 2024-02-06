package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 100-150
 * Price:75
 */
public class AdvancedPotion extends SimplePotion {

    private static final Range WORTH = new Range(5, 11);
    public AdvancedPotion(String n) {
        super(n, WORTH);
        rangeOfHealing = new Range(100, 151);
        price = 75;
    }
    public static AdvancedPotion getPreset(){
        return new AdvancedPotion("Advanced Potion");
    }
}
