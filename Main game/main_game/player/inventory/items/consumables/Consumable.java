package main_game.player.inventory.items.consumables;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.Item;

public abstract class Consumable extends Item {

    public Consumable(String n, Range worthRange) {
        super(n, worthRange);
    }

    @Override
    public String toString() {
        return Colors.CYAN + name + Colors.RESET;
    }
    public abstract void use() throws InterruptedException;
    
}
