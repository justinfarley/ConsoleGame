package interactions.battle_options;

import java.util.ArrayList;
import java.util.Scanner;

import helpers.DialogueHelper;
import interactions.Battle;
import main_game.player.Player;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;

public class UseItem extends Option {
    private Player player;
    private Scanner scan = new Scanner(System.in);
    private static final String NAME = "Item";
    
    public UseItem(Player player){
        super(NAME);
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
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        DialogueHelper.sayText("What Item would you like to use?\n", 0, false);
        ArrayList<Item> consumables = new ArrayList<>();
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Consumable){
                System.out.print("\t-" + i + "\n");
                consumables.add(i);
            }
        }

        DialogueHelper.sayText("Enter choice: ", 20, false);
        String consumableName = scan.nextLine();
        Item i = DialogueHelper.getClosestAction(consumables, consumableName);
        ((Consumable)i).use();
    }
    @Override
    public void init() {

    }
    @Override
    public String toString(){
        return name;
    }
}
