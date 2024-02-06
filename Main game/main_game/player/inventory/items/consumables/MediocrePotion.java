package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 30-60
 * Price: 25
 * Worth: 10-15
 */
public class MediocrePotion extends SimplePotion{
    private static final Range WORTH = new Range(10, 16);
    public MediocrePotion(String n) {
        super(n, WORTH);
        rangeOfHealing = new Range(30,61);
        price = 25;
    }
    public static MediocrePotion getPreset(){
        return new MediocrePotion("Mediocre Potion");
    }
}
