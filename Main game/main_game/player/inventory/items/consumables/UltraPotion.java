package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 200-300
 */
public class UltraPotion extends SimplePotion {

    public UltraPotion(String n) {
        super(n);
        rangeOfHealing = new Range(200, 301);
    }
    public static UltraPotion getPreset(){
        return new UltraPotion("Ultra Potion");
    }
}
