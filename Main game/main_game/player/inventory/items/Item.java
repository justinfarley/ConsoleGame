package main_game.player.inventory.items;

import helpers.INameable;
import main_game.GameLoop;

public abstract class Item implements INameable{
    public String name;
    public Item(String n){
        name = n;
    }
    public abstract String toString();
    public void addToInventory(){
        GameLoop.getPlayer().getInventory().addItem(this);
    }
    public String getName(){
        return name;
    }
}
