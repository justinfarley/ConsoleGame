package actions;

import time.Clock;

public abstract class Action {
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
