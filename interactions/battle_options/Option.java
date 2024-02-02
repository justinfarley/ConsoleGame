package interactions.battle_options;

import helpers.INameable;

public abstract class Option implements INameable {
    protected String name;
    public String cannotInvokeMessage;
    protected Option(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract void invoke(boolean invokedByPlayer) throws InterruptedException;
    public abstract boolean canInvoke();
    public abstract void init();
}
