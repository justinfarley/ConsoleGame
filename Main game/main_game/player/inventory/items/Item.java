package main_game.player.inventory.items;

import main_game.GameLoop;

public abstract class Item {
    public String name;
    public Item(String n){
        name = n;
    }
    public abstract String toString();
    public void addToInventory(){
        GameLoop.getPlayer().getInventory().addItem(this);
    }
}
