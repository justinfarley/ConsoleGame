package actions;

import helpers.DialogueHelper;
import world_generation.WorldMap;

public class ViewMap extends Action{
    private WorldMap mapReference;
    public ViewMap(String n, WorldMap map){
        name = n;
        mapReference = map;
    }
    @Override
    public void invoke() throws InterruptedException {
        mapReference.print();
        DialogueHelper.sayTextln("You are the X.", 50, false);
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
    
}
