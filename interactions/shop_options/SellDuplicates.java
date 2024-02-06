package interactions.shop_options;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import helpers.Colors;
import helpers.DialogueHelper;
import interactions.battle_options.Option;
import main_game.GameLoop;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;
import main_game.player.inventory.items.weapons.Weapon;

public class SellDuplicates extends Option{

    private Scanner scan = new Scanner(System.in);
    public SellDuplicates(String name) {
        super(name);
        cannotInvokeMessage = "Looks like you don't have any duplicate items!";
    }

    @Override
    public boolean canInvoke() {
        return GameLoop.getPlayer().getInventory().hasDuplicates();
    }

    @Override
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        final String ITEMS = "Items", WEAPONS = "Weapons", ALL = "All";
        String[] options = new String[]{ITEMS, WEAPONS, ALL};

        DialogueHelper.sayText("What duplicates would you like to sell?\n\t-Items\n\t-Weapons\n\t-All\n\n", 15, false);

        String choice = null;
        String result = null;

        ArrayList<Item> itemsAbleToBeSold = new ArrayList<>();
        while(result == null || itemsAbleToBeSold.isEmpty()){
            itemsAbleToBeSold = GameLoop.getPlayer().getInventory().getDuplicates();
            DialogueHelper.sayText("Enter an option: ", 15, false);
            choice = scan.nextLine().toLowerCase();
            result = DialogueHelper.getClosestString(options, choice);
        
            switch(result){
                case ITEMS:
                itemsAbleToBeSold = filterItems(itemsAbleToBeSold);
                if(itemsAbleToBeSold.isEmpty()) DialogueHelper.sayTextln("You have no duplicate items!", 15, false);
                break;
                case WEAPONS:
                itemsAbleToBeSold = filterWeapons(itemsAbleToBeSold);
                if(itemsAbleToBeSold.isEmpty()) DialogueHelper.sayTextln("You have no duplicate weapons!", 15, false);
                break;
                case ALL:
                default:
                //nothing needed
                break;
            }
        }
        int sum = 0;
        for(Item i : itemsAbleToBeSold){
            sum += GameLoop.getPlayer().getInventory().sellItem(i);
        }
        //sold all items
        DialogueHelper.sayTextln("Sold " + Colors.YELLOW + itemsAbleToBeSold.size() + Colors.RESET + " item(s) for " + Colors.YELLOW + sum + " gold" + Colors.RESET, 20, false);
        System.out.println("New balance: " + Colors.YELLOW + GameLoop.getPlayer().getGold() + " gold" + Colors.RESET + ".");
    }
    private ArrayList<Item> filterItems(ArrayList<Item> itemsAbleToBeSold){
        ArrayList<Item> filteredList = new ArrayList<>();
        for(Item i : itemsAbleToBeSold){
            if(i instanceof Consumable){
                filteredList.add(i);
            }
        }
        return filteredList;
    }
    private ArrayList<Item> filterWeapons(ArrayList<Item> itemsAbleToBeSold){
        ArrayList<Item> filteredList = new ArrayList<>();
        for(Item i : itemsAbleToBeSold){
            if(i instanceof Weapon){
                filteredList.add(i);
            }
        }
        return filteredList;
    }
    @Override
    public void init() {
    }
    
}
