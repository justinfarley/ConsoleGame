package actions;

import helpers.DialogueHelper;
import time.Clock;

public class Rest extends Action {

    public Rest(String n){
        name = n;
    }
    private void coroutine() throws InterruptedException{
        long milliDelay = 100;
        DialogueHelper.sayTextln("Resting.....", milliDelay, true);
        DialogueHelper.sayTextln("Resting.....", milliDelay, false);
    }
    @Override
    public void invoke() throws InterruptedException{
        restActions();
        coroutine();
    }
    private void restActions(){
        int timeRested = Clock.advanceToNextTimeState();
        //TODO: increase health based on the amount of time rested for etc.
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
        
}
