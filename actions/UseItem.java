package actions;

import java.util.Arrays;
import java.util.Scanner;

import helpers.DialogueHelper;
import main_game.player.Player;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;

public class UseItem extends Action {
    Player player;
    private static Scanner scan = new Scanner(System.in);
    public UseItem(String n, Player player){
        name = n;
        cannotInvokeMessage = "You don't have any consumables in your inventory! Try again: ";
        this.player = player;
    }
    @Override
    public boolean canInvoke() {
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Consumable) return true;
        }
        return false;
    }
    @Override
    public void invoke() throws InterruptedException {
        DialogueHelper.sayText("What would you like to switch your weapon to?\n", 0, false);
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Consumable){
                System.out.print("\t-" + i + "\n");
            }
        }
        DialogueHelper.sayText("Enter choice: ", 20, false);
        String consumableName = scan.nextLine();

        Item[] arr = (Item[])Arrays.copyOf(player.getInventory().getItems().toArray(), player.getInventory().getItems().size());
        
        for(Item i : arr){
            if(i.name.equalsIgnoreCase(consumableName) && i instanceof Consumable){
                ((Consumable)i).use();
            }
        }
    }
    
}
