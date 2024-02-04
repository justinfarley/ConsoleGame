package actions;

import java.util.ArrayList;
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
        DialogueHelper.sayText("What would you like to use?\n", 0, false);
        ArrayList<Item> thingsToChooseFrom = new ArrayList<>();
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Consumable){
                System.out.print("\t-" + i + "\n");
                thingsToChooseFrom.add(i);
            }
        }
        DialogueHelper.sayText("Enter choice: ", 20, false);
        String choice = scan.nextLine();

        Consumable consumable = (Consumable)DialogueHelper.getClosestAction(thingsToChooseFrom, choice);

        consumable.use();
    }
}
