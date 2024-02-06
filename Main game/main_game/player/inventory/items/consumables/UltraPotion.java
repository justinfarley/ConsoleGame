package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 200-300
 * Price: 150
 * Worth: 125-150
 */
public class UltraPotion extends SimplePotion {
    private static final Range WORTH = new Range(125, 151);
    public UltraPotion(String n) {
        super(n, WORTH);
        rangeOfHealing = new Range(200, 301);
        price = 150;
    }
    public static UltraPotion getPreset(){
        return new UltraPotion("Ultra Potion");
    }
}
