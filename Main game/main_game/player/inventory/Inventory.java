package main_game.player.inventory;

import java.util.ArrayList;

import javax.swing.tree.ExpandVetoException;
import javax.tools.Diagnostic;

import helpers.DialogueHelper;
import helpers.Items;
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
    public ArrayList<Item> getDuplicates(){
        ArrayList<Item> countedItems = new ArrayList<>();
        ArrayList<Item> duplicates = new ArrayList<>();
        for(Item i : items){
            if(!countedItems.contains(i)){
                countedItems.add(i);
            }
            else{
                duplicates.add(i);
            }
        }
        return duplicates;
    }
    public boolean hasDuplicates(){
        ArrayList<String> countedItems = new ArrayList<>();
        for(Item i : items){
            if(!countedItems.contains(i.getName())){
                countedItems.add(i.getName());
            }
            else{
                return true;
            }
        }
        return false;
    }
    public void viewInventory(){
        if(items.isEmpty()){
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("All items acquired:");
        boolean shownEquippedItem = false;
        for(Item i : items){
            boolean playerHasItemEquipped = GameLoop.getPlayer().getWeapon().getName().equals(i.getName());
            try{
                playerHasItemEquipped = GameLoop.getPlayer().getWeapon().getName().equals(i.getName());
            }
            catch(Exception e){
                System.out.println("make sure you added the item to the list ALL_ITEMS in Items.java");
                e.printStackTrace();
                System.exit(1);
            }
            if(playerHasItemEquipped && !shownEquippedItem){
                System.out.print("\t-" + i + " (Equipped)\n");
                shownEquippedItem = true;
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
            boolean playerHasItemEquipped = GameLoop.getPlayer().getWeapon().getName().equalsIgnoreCase(i.getName());
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

    public void setInventory(String[] inventoryItems) {
        items = new ArrayList<>();
        for(String name : inventoryItems){
            items.add(Items.getItem(name));
        }
    }
    /**
     * returns sell price
     * @param i
     * @return
     */
    public int sellItem(Item i){
        if(!items.contains(i)){
            System.out.println("ERROR");
            return -1;
        }

        //sell item
        int itemSellPrice = i.getWorthAmount();
        items.remove(i);
        GameLoop.getPlayer().addGold(itemSellPrice);
        return itemSellPrice;
    }
    public void buyItem(Item item, int quantity){
        for(int i = 0; i < quantity; i++){
            int itemSellPrice = item.getPrice();
            GameLoop.getPlayer().removeGold(itemSellPrice);
            items.add(item);
        }
    }
}
