package actions;

import main_game.player.*;
public class ViewPlayerStats extends Action{
    Player player;
    public ViewPlayerStats(String n, Player player){
        name = n;
        this.player = player;
    }
    @Override
    public void invoke() throws InterruptedException{
        System.out.println(player.getPlayerStatSheet());
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
}
