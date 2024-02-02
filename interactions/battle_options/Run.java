package interactions.battle_options;

import interactions.Battle;

public class Run extends Option{
    private Battle battle;
    private static final String NAME = "Run";
    public Run(Battle currentBattle){
        super(NAME);
        battle = currentBattle;
    }

    @Override
    public void invoke(boolean invokedByPlayer) throws InterruptedException {
        battle.end();
    }
    public String toString(){
        return name;
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean canInvoke() {
        // TODO Auto-generated method stub
        return true;
    }
}
