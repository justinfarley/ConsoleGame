package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Range of healing: 30-60
 */
public class MediocrePotion extends SimplePotion{
    public MediocrePotion(String n) {
        super(n);
        rangeOfHealing = new Range(30,61);
    }
    public static MediocrePotion getPreset(){
        return new MediocrePotion("Mediocre Potion");
    }
}
