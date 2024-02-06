package actions;

import helpers.Colors;
import helpers.DialogueHelper;
import main_game.GameLoop;
import main_game.player.inventory.items.weapons.level_3.Gambler;
import time.Clock;

public class Rest extends Action {

    public Rest(String n){
        name = n;
        cannotInvokeMessage = "You are already at maximum health!\n";
    }
    private void coroutine() throws InterruptedException{
        long milliDelay = 100;
        DialogueHelper.sayTextln("Resting.....", milliDelay, true);
        DialogueHelper.sayTextln("Resting.....", milliDelay, false);
    }
    @Override
    public void invoke() throws InterruptedException{
        coroutine();
        restActions();
    }
    private void restActions() throws InterruptedException{
        int timeRested = Clock.advanceToNextTimeState();
        double percentage = timeRested / 24.0;

        int healthHealed = (int)(GameLoop.getPlayer().getMaxHealth() * percentage);

        GameLoop.getPlayer().gainHealth(healthHealed);

        //TODO: increase health based on the amount of time rested for etc.
        DialogueHelper.sayTextln("Healed " + Colors.YELLOW + healthHealed + " health" + Colors.RESET + "! Now at " + GameLoop.getPlayer().getHealth() + "/" + GameLoop.getPlayer().getMaxHealth() + ".", healthHealed, false);

    }
    @Override
    public boolean canInvoke() {
        return GameLoop.getPlayer().getHealth() < GameLoop.getPlayer().getMaxHealth();
    }
        
}
