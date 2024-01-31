package actions;

import main_game.player.inventory.Inventory;

public class VisitShop extends Action{
    private Inventory inventory;
    public VisitShop(String n, Inventory inventory){
        name = n;
        this.inventory = inventory;
    }
    @Override
    public void invoke() throws InterruptedException{
        System.out.println("Not implemented yet!");
    }
    @Override
    public boolean canInvoke() {
        return true;
    }

}
