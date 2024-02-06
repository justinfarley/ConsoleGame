package actions;

import java.util.ArrayList;
import java.util.Scanner;

import helpers.DialogueHelper;
import interactions.battle_options.Option;
import interactions.shop_options.BuyItem;
import interactions.shop_options.SellDuplicates;
import main_game.player.inventory.Inventory;
import main_game.player.inventory.items.Item;
import main_game.player.inventory.items.consumables.Consumable;

public class VisitShop extends Action{
    private Inventory inventory;
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Option> shopActions = new ArrayList<>();
    public VisitShop(String n, Inventory inventory){
        name = n;
        this.inventory = inventory;
        shopActions.add(new SellDuplicates("Sell Duplicates"));
        shopActions.add(new BuyItem("Buy Item"));
    }
    @Override
    public void invoke() throws InterruptedException{
        DialogueHelper.sayTextln("Welcome to the shop.\nWhat would you like to do?", 0, false);
        for(Option a : shopActions){
            System.out.println("\t-" + a.getName());
        }
        DialogueHelper.sayText("Enter choice: ", 20, false);
        String choice = scan.nextLine();
        Option option = DialogueHelper.getClosestAction(shopActions, choice);

        if(option.canInvoke())
            option.invoke(true);
        else System.out.println(option.cannotInvokeMessage);
    }
    @Override
    public boolean canInvoke() {
        return true;
    }

}
