package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 100-150
 */
public class AdvancedPotion extends SimplePotion {

    public AdvancedPotion(String n) {
        super(n);
        rangeOfHealing = new Range(100, 151);
    }
    public static AdvancedPotion getPreset(){
        return new AdvancedPotion("Advanced Potion");
    }
}
