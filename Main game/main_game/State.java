package main_game;

import java.util.ArrayList;
import java.util.Scanner;

import actions.*;
import helpers.DialogueHelper;
import time.Clock;

public class State {
    public enum TimeState{
        START,
        MIDDLE,
        LATE,
        NIGHT;
    }
    private TimeState currentState;
    private ArrayList<Action> actions = new ArrayList<>();
    private GameLoop loop;
    private Scanner scan = new Scanner(System.in);
    public State(){
        currentState = TimeState.START;
        addAllActions();
    }
    public State(GameLoop loop){
        currentState = TimeState.START;
        this.loop = loop;
        addAllActions();
    }
    private void addAllActions(){
        actions.add(new Move("Move", GameLoop.getPlayer(), GameLoop.getMap()));
        actions.add(new ViewMap("View Map", GameLoop.getMap()));
        actions.add(new ViewInventory("View Inventory", GameLoop.getPlayer().getInventory()));
        actions.add(new ViewPlayerStats("Player Stats", GameLoop.getPlayer()));
        actions.add(new ViewWeaponStats("Weapon Stats", GameLoop.getPlayer()));
        actions.add(new VisitShop("Visit Shop", GameLoop.getPlayer().getInventory()));
        actions.add(new ChangeWeapon("Change Weapon", GameLoop.getPlayer()));
        actions.add(new UseItem("Use Item", GameLoop.getPlayer()));
        actions.add(new Rest("Rest"));
    }
    public void stateUpdate() throws InterruptedException{
        switch(currentState){
            case START:
                startDay();
                break;
            case MIDDLE:
                midDay();
                break;
            case LATE:
                lateDay();
                break;
            case NIGHT:
                night();
                break;
            default:
                startDay();
                break;
        }
    }
    public TimeState swapState(TimeState newState){
        currentState = newState;
        return currentState;
    }
    public void startDay() throws InterruptedException{
        //choose an action at the start
        Clock.displayTime();
        awaitAction();
        swapState(TimeState.MIDDLE);
    }
    public void midDay() throws InterruptedException{
        //choose an action at the middle day
        DialogueHelper.sayText("MID DAY", GameLoop.TEXT_SPEED, false);
        swapState(TimeState.LATE);
    }
    public void lateDay() throws InterruptedException{
        //choose an action at the late day
        DialogueHelper.sayText("LATE DAY", GameLoop.TEXT_SPEED, false);
        swapState(TimeState.NIGHT);
    }
    public void night() throws InterruptedException{
        //choose an action at the night time
        DialogueHelper.sayText("NIGHT TIME", GameLoop.TEXT_SPEED, false);
        swapState(TimeState.START);
    }
    private void awaitAction() throws InterruptedException{
        DialogueHelper.sayTextln("Choose an action to take. For a list of actions, type HELP.", GameLoop.TEXT_SPEED, false);
        Action selectedAction = null;
        while(selectedAction == null || !selectedAction.canInvoke()){
            String input = scan.nextLine().toLowerCase();
            if(input.equals("help"))
            {
                printActionList();
                DialogueHelper.sayText("Type the action you would like to perform: ", GameLoop.TEXT_SPEED, false);
                input = scan.nextLine();
            }
            selectedAction = DialogueHelper.getClosestAction(actions, input);
            if(selectedAction == null)
            {
                DialogueHelper.sayText("Choose a VALID action to take. For a list of actions, type HELP: ", GameLoop.TEXT_SPEED, false);
            }
            else if(!selectedAction.canInvoke()){
                DialogueHelper.sayText(selectedAction.cannotInvokeMessage, GameLoop.TEXT_SPEED, false);
            }
        }
        selectedAction.invoke();
        //TODO: uncomment this
        //currentState = Clock.getTimeState();
        loop.tick(); //tick the game loop to repeat
    }
    private Action hasAction(String input){
        for(Action action : actions){
            if(action.getName().toLowerCase().equals(input)) return action;
        }
        return null;
    }
    private void printActionList(){
        System.out.print("Actions:\n\t");
        for(Action action : actions){
            System.out.print("-" + action.getName() + "\n\t");
        }
        System.out.println();
    }
}
