package interactions.battle_options;

import javax.print.attribute.standard.DialogOwner;

import enemies.Enemy;
import helpers.DialogueHelper;
import interactions.Battle;
import main_game.player.Player;
import main_game.player.inventory.Inventory;

public class Fight extends Option {
    private Battle battle;
    private Player player;
    private Enemy enemy;
    private Inventory playerInventory;
    private boolean initialized = false;
    public Fight(Battle currentBattle, String s){
        super(s);
        battle = currentBattle;
        cannotInvokeMessage = "You don't have a weapon! Pick a new option: ";
    }
    public void init(){
        initialized = true;
        enemy = battle.getEnemy();
        player = battle.getPlayer();
    }
    @Override
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        if(invokedByPlayer){
            player.getWeapon().attack(enemy);
        }
        else{
            enemy.getWeapon().attack(enemy, player);
        }
    }
    public String toString(){
        return name;
    }
    @Override
    public boolean canInvoke() {
        if(!initialized) init();
        return player.getWeapon() != null;
    }
}
