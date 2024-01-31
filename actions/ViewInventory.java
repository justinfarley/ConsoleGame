package actions;

import main_game.player.inventory.Inventory;

public class ViewInventory extends Action{
    private Inventory inventory;
    public ViewInventory(String n, Inventory inventory){
        name = n;
        this.inventory = inventory;
    }
    @Override
    public void invoke() throws InterruptedException{
        inventory.viewInventory();
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
}
