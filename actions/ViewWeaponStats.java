package actions;

import main_game.player.*;
public class ViewWeaponStats extends Action{
    Player player;
    public ViewWeaponStats(String n, Player player){
        name = n;
        this.player = player;
    }
    @Override
    public void invoke() throws InterruptedException{
        System.out.println(player.getWeapon().getWeaponStats());
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
}
