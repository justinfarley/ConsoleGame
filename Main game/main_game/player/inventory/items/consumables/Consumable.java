package main_game.player.inventory.items.consumables;

import helpers.Colors;
import main_game.player.inventory.items.Item;

public abstract class Consumable extends Item {

    public Consumable(String n) {
        super(n);
    }

    @Override
    public String toString() {
        return Colors.CYAN + name + Colors.RESET;
    }
    public abstract void use() throws InterruptedException;
    
}
