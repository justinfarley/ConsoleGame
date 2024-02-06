package interactions.battle_options;

import javax.print.attribute.standard.DialogOwner;
import javax.print.attribute.standard.MediaSize.NA;

import enemies.Enemy;
import helpers.DialogueHelper;
import interactions.Battle;
import main_game.GameLoop;
import main_game.player.Player;
import main_game.player.inventory.Inventory;

public class Fight extends Option {
    private Battle battle;
    private Enemy enemy;
    private Inventory playerInventory;
    private boolean initialized = false;
    private static final String NAME = "Fight";
    public Fight(Battle currentBattle){
        super(NAME);
        battle = currentBattle;
        cannotInvokeMessage = "You don't have a weapon! Pick a new option: ";
    }
    public void init(){
        initialized = true;
    }
    @Override
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        if(invokedByPlayer){
            GameLoop.getPlayer().getWeapon().attack(battle.getEnemy());
        }
        else{
            battle.getEnemy().getWeapon().attack(battle.getEnemy(), GameLoop.getPlayer());
        }
    }
    public String toString(){
        return name;
    }
    @Override
    public boolean canInvoke() {
        if(!initialized) init();
        return GameLoop.getPlayer().getWeapon() != null;
    }
}
