package interactions.shop_options;

import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.DialogOwner;

import helpers.Colors;
import helpers.DialogueHelper;
import helpers.Items;
import interactions.battle_options.Option;
import main_game.GameLoop;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;

public class BuyItem extends Option{
    private Scanner scan = new Scanner(System.in);
    public BuyItem(String name) {
        super(name);
        cannotInvokeMessage = "You don't have any money!";
    }
    @Override
    public boolean canInvoke() {
        return GameLoop.getPlayer().getGold() > 0;
    }
    @Override
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        DialogueHelper.sayTextln("Here is a list of items currently available:", 15, false);
        DialogueHelper.waitForMillis(500);
        ArrayList<Item> itemsAvailableForPurchase = displayAvailableShopItems();

        int playerGold = GameLoop.getPlayer().getGold();

        DialogueHelper.sayText("You currently have " + Colors.YELLOW + playerGold + " gold" + Colors.RESET + ". What item would you like to buy? (type NOTHING to go back): ", 15, false);
        String choice = scan.nextLine();

        //maybe far down road have multiple args to buy more than 1 thing at once I.E 
        Item item = DialogueHelper.getClosestAction(itemsAvailableForPurchase, choice);
        if(item == null || choice.equalsIgnoreCase("nothing")) return;
        int quantity = -1;
        if(playerGold <= item.getPrice() * 2 - 1){
            quantity = 1;
        }
        else{
            try {
                while(quantity <= 0){
                    System.out.println("Enter Quantity: ");
                    quantity = Integer.parseInt(scan.nextLine());
                }
            } catch (Exception e) {

            }
        }
        if(playerGold < item.getPrice() * quantity){
            DialogueHelper.sayTextln("You don't have enough gold to buy " + item + " (Quantity: " + quantity + ")! (" + GameLoop.getPlayer().getGold() + "/" + item.getPrice() * quantity + ")", 25, false);
            return;
        }
        DialogueHelper.sayTextln("Successfully purchased: " + ((item instanceof Consumable) ? Colors.CYAN : Colors.GREEN) + item + Colors.RESET + " (Quantity: " + quantity + ")!", 25, false);
        GameLoop.getPlayer().getInventory().buyItem(item, quantity);
        System.out.println();
    }
    @Override
    public void init() {

    }

    private ArrayList<Item> displayAvailableShopItems(){
        //display all weapons up to the current level available to buy, use their price and display that too
        ArrayList<String> itemNamesAvailable = new ArrayList<>();
        //calculate what can be bought
        for(int i = 0; i < GameLoop.getPlayer().getExperience().getLevel() + 1; i++){
            for(int j = 0; j < Items.ALL_ITEMS_FOR_LEVEL[i].length; j++){
                if(!itemNamesAvailable.contains(Items.ALL_ITEMS_FOR_LEVEL[i][j].getName())){
                    itemNamesAvailable.add(Items.ALL_ITEMS_FOR_LEVEL[i][j].getName());
                }
            }
        }
        //turn into list of items
        ArrayList<Item> itemsAvailable = new ArrayList<>();
        for(String name : itemNamesAvailable){
            Item i = Items.getItem(name);
            itemsAvailable.add(i);
        }


        //display
        for(int i = 0; i < itemsAvailable.size(); i++){
            try {
                System.out.print("\t-" + itemsAvailable.get(i++) + " (" + Colors.YELLOW + itemsAvailable.get(i - 1).getPrice() + " Gold" + Colors.RESET + ")");
                System.out.print("\t-" + itemsAvailable.get(i++) + " (" + Colors.YELLOW + itemsAvailable.get(i - 1).getPrice() + " Gold" + Colors.RESET + ")");
                System.out.println("\t-" + itemsAvailable.get(i) + " (" + Colors.YELLOW + itemsAvailable.get(i).getPrice() + " Gold" + Colors.RESET + ")");
            } catch (Exception e) {
                break;
            }
        }
        System.out.println();
        return itemsAvailable;
    }
}
