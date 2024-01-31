package interactions;

import java.util.Scanner;

import enemies.*;

import helpers.DialogueHelper;

import interactions.battle_options.*;
import main_game.GameLoop;
import main_game.player.Player;

public class Battle {
    private Enemy enemy;
    private Player player;
    private boolean playerTurn;
    private boolean ended;
    private Scanner scan = new Scanner(System.in);
    public static final long BATTLE_TEXT_SPEED = 10;
    private final Option[] BATTLE_OPTIONS = new Option[]{
        new Fight(this, "Fight"),
        new UseItem("Item", GameLoop.getPlayer()),
        new Run(this, "Run"),
    };
    public Battle(Enemy enemy, Player player) throws InterruptedException{
        this.enemy = enemy; //ensures we dont use the actual copy of the enemy in the list, always get new preset
        this.player = player;
        playerTurn = player.getSpeed() > enemy.getSpeed();
        DialogueHelper.sayTextln("You have encountered a wild " + enemy.getName() + "!", BATTLE_TEXT_SPEED, false);
        DialogueHelper.waitForMillis(200);
        startFight();
    }
    /**
     * Starts turn based combat
     */
    public void startFight() throws InterruptedException{
        if(ended) return;
        if(playerTurn){
            playerTurn();
        }
        else{
            enemyTurn();
        }
    }
    private void playerTurn() throws InterruptedException{
        if(ended) return;
        DialogueHelper.sayTextln("It is your turn. What will you do?", BATTLE_TEXT_SPEED, false);
        DialogueHelper.waitForMillis(500);
        displayBattleOptions();
        String choice = scan.nextLine();
        Option chosenOption = getOption(choice);
        while(chosenOption == null || !chosenOption.canInvoke()){
            String text = "Please choose a valid option: ";
            if(chosenOption != null && !chosenOption.canInvoke()){
                text = chosenOption.cannotInvokeMessage;
            }
            DialogueHelper.sayText(text, BATTLE_TEXT_SPEED, false);
            choice = scan.nextLine();
            chosenOption = getOption(choice);
        }
        chosenOption.init();
        chosenOption.invoke(true);
        if(enemy.getHealth() > 0)
            enemyTurn();
        
    }
    private void enemyTurn() throws InterruptedException{
        if(ended) return;

        //pick random battle option for the enemy and invoke
        Fight enemyFight = new Fight(this, "Fight");
        enemyFight.init();
        enemyFight.invoke(false);
        playerTurn();
    }
    private void displayBattleOptions() throws InterruptedException{
        for(Option s : BATTLE_OPTIONS){
            System.out.println("\t-" + s);
        }
        DialogueHelper.sayTextln("Enter an action: ", BATTLE_TEXT_SPEED, false);
    }
    private Option getOption(String input){
        for(Option o : BATTLE_OPTIONS){
            if(o.toString().equalsIgnoreCase(input)) return o;
        }
        return null;
    }
    public Player getPlayer(){
        return player;
    }
    public Enemy getEnemy(){
        return enemy;
    }
    public void end(){
        ended = true;
    }
}
