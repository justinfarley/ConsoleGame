package actions;

import java.util.Scanner;

import helpers.DialogueHelper;
import main_game.GameLoop;
import main_game.player.Player;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.weapons.Weapon;

public class ChangeWeapon extends Action {

    private Scanner scan = new Scanner(System.in);
    private Player player;
    public ChangeWeapon(String n, Player player){
        name = n;
        cannotInvokeMessage = "You don't have a weapon in your inventory! Try again: ";
        this.player = player;
    }

    @Override
    public void invoke() throws InterruptedException {
        DialogueHelper.sayText("What would you like to switch your weapon to?\n", 0, false);
        player.getInventory().viewWeapons();
        DialogueHelper.sayText("Enter choice: ", 20, false);
        String weaponName = scan.nextLine();

        Weapon w = (Weapon)DialogueHelper.getClosestAction(player.getInventory().getItems(), weaponName);
        if(w == null) return;
        if(player.getWeapon().getName().equalsIgnoreCase(w.getName())){
            DialogueHelper.sayTextln("You already have " + w + " equipped!", 20, false);
        }
        else if(!player.getWeapon().getName().equalsIgnoreCase(w.getName())){
            player.equipWeapon(w);
            DialogueHelper.sayTextln("Successfully equipped " + w + "!", 20, false);
        }
    }

    @Override
    public boolean canInvoke() {
        for(Item i : player.getInventory().getItems()){
            if(i instanceof Weapon) return true;
        }
        return false;
    }
}
