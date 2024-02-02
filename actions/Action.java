package actions;

import helpers.INameable;
import time.Clock;

public abstract class Action implements INameable{
    protected String name;
    public String cannotInvokeMessage;
    public void invoke() throws InterruptedException{
        Clock.advanceTime();
        //Clock.displayTime();
    }
    public abstract boolean canInvoke();

    public String getName(){
        return name;
    }
}
