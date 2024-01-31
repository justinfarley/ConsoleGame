package interactions.battle_options;

import java.util.Scanner;

import helpers.DialogueHelper;
import interactions.Battle;
import main_game.player.Player;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;

public class UseItem extends Option {
    private Player player;
    private Scanner scan = new Scanner(System.in);
    public UseItem(String n, Player player){
        super(n);
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
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        DialogueHelper.sayText("What would you like to switch your weapon to?\n", 0, false);
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Consumable){
                System.out.print("\t-" + i + "\n");
            }
        }
        DialogueHelper.sayText("Enter choice: ", 20, false);
        String consumableName = scan.nextLine();

        for(Item i : player.getInventory().getItems()){
            if(i.name.equalsIgnoreCase(consumableName) && i instanceof Consumable){
                ((Consumable)i).use();
                break;
            }
        }
    }
    @Override
    public void init() {

    }
    @Override
    public String toString(){
        return name;
    }
}
