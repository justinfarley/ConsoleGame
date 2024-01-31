package interactions.battle_options;

public abstract class Option {
    protected String name;
    public String cannotInvokeMessage;
    protected Option(String name){
        this.name = name;
    }
    public abstract void invoke(boolean invokedByPlayer) throws InterruptedException;
    public abstract boolean canInvoke();
    public abstract void init();
}
