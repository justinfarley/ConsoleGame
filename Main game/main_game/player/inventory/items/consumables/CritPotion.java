package main_game.player.inventory.items.consumables;

import helpers.Range;

/**
 * Will be one time use crit on next attack guaranteed (any weapon)
 * Price: 50
 */
public class CritPotion extends Consumable{
    private static final Range WORTH = new Range(5, 11);

    public CritPotion(String n) {
        super(n, WORTH);
        price = 50;
    }

    @Override
    public void use() throws InterruptedException {
        
    }
    
}
