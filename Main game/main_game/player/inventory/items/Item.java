package main_game.player.inventory.items;

import helpers.INameable;
import helpers.Range;
import main_game.GameLoop;

public abstract class Item implements INameable{
    public String name;
    protected int price;
    private Range worth;
    public Item(String n, Range worthRange){
        name = n;
        worth = worthRange;
    }
    public abstract String toString();
    public void addToInventory(){
        GameLoop.getPlayer().getInventory().addItem(this);
    }
    public String getName(){
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getWorthAmount() {
        return worth.getNumberInRange();
    }
}
