package main_game.player.inventory;

import java.util.ArrayList;

import main_game.GameLoop;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.weapons.Weapon;

public class Inventory {
    private static final int INVENTORY_SIZE = 20;
    private ArrayList<Item> items = new ArrayList<>();



    public Inventory(){
        //nothing needed yet
    }

    public void addItem(Item item){
        if(items.size() >= INVENTORY_SIZE){
            //out of inventory space!
            return;
        }
        if(GameLoop.getPlayer().getWeapon() == null && (item instanceof Weapon)){
            GameLoop.getPlayer().equipWeapon((Weapon)item);
        }
        
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    public void viewInventory(){
        if(items.isEmpty()){
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("All items acquired:");
        for(Item i : items){
            boolean playerHasItemEquipped = GameLoop.getPlayer().getWeapon().equals(i);
            if(playerHasItemEquipped){
                System.out.print("\t-" + i + " (Equipped)\n");
            }
            else{
                System.out.print("\t-" + i + "\n");
            }
        }
    }
    /**
     * If a weapon has a duplicate, will not print duplicates
     */
    public void viewWeapons(){
        ArrayList<String> printedWeapons = new ArrayList<>();
        for(Item i : items){
            boolean playerHasItemEquipped = GameLoop.getPlayer().getWeapon().equals(i);
            if(i instanceof Weapon && playerHasItemEquipped && !printedWeapons.contains(i.name)){
                System.out.print("\t-" + i + " (Equipped)\n");
                printedWeapons.add(i.name);
            }
            else if(!printedWeapons.contains(i.name) && i instanceof Weapon){
                System.out.print("\t-" + i + "\n");
                printedWeapons.add(i.name);
            }
        }
    }
}
