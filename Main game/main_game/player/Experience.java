package main_game.player;

import event_handling.Event;
import helpers.Colors;
import helpers.DialogueHelper;
import main_game.GameLoop;
import world_generation.ItemPedestal;

public class Experience {
    private double currentAmount;
    private double goal;
    private int level = 0;
    private Player player;
    public static Event onLevelUp = new Event();
    private static final int[] HEALTH_GAINED_PER_LEVEL = 
    {
        2, 3, 5, 7, 10, 8, 11, 13, 15, 20
    };
    public static final int[] EXP_GOAL_LIST = 
    {
        /*testing*/ 1,1,1,1,1,1,1,1,1,
        /*real*///5, 25, 75, 150, 300, 500, 750, 1000, 1200, 3000
    };
    public static final int[] POSSIBLE_ITEM_PEDESTAL_SPAWNS = 
    {
        0, 1, 2, 3, 3, 3, 3, 4, 3, 3, 5
    };
    public Experience(Player p){
        player = p;
        goal = EXP_GOAL_LIST[0];
    }
    public void levelUp() throws InterruptedException{
        currentAmount = 0;
        level++;
        goal = EXP_GOAL_LIST[level];
        int healthGained = HEALTH_GAINED_PER_LEVEL[level - 1];
        DialogueHelper.sayTextln(Colors.PURPLE + "Level Up!\nYou are now level " + level + "!", GameLoop.TEXT_SPEED, false);
        DialogueHelper.waitForMillis(500);
        player.increaseMaxHealth(healthGained);
        DialogueHelper.sayTextln("You gained " + Colors.GREEN +  healthGained + Colors.PURPLE + " maximum health! (health has been restored to the maximum: " + Colors.GREEN + player.getHealth() + Colors.PURPLE+ ")", GameLoop.TEXT_SPEED, false); //-1 cuz of indexing
        DialogueHelper.sayTextln("New " + Colors.BLUE + "Item Pedestals" + Colors.PURPLE + " have spawned!" + Colors.RESET, GameLoop.TEXT_SPEED, false);
        DialogueHelper.waitForMillis(1000);
        GameLoop.getMap().updatePedestalSpawns();
        onLevelUp.invokeEvent();
    }
    public void gainExp(double amount) throws InterruptedException{
        currentAmount += amount;
        if(currentAmount >= goal){
            double leftovers = currentAmount - goal;
            levelUp();
            currentAmount += leftovers;
        }
    }
    public double getExp(){
        return currentAmount;
    }
    //show when enemy gets defeated and stuff
    public void showExpBar() throws InterruptedException{
        int numProgress = ((int)((currentAmount / goal) * 100)) >= 1 ? 100 : (int)((currentAmount / goal) * 100);
        int numNoProgress = 100 - numProgress;
        String bar = "";
        bar += "\n" + currentAmount +" Exp |" + Colors.GREEN;
        for(int i = 0; i < numProgress; i++){
            bar += "=";
        }
        bar += Colors.RED;
        for(int i = 0; i < numNoProgress; i++){
            bar += ".";
        }
        bar += Colors.RESET + "| " + goal + " Exp\n";
        DialogueHelper.sayTextln(bar, 10, false);
        DialogueHelper.waitForMillis(500);
    }
    //show when enemy gets defeated and stuff
    public String getExpBar(){
        int numProgress = ((int)((currentAmount / goal) * 100)) >= 1 ? 100 : (int)((currentAmount / goal) * 100);
        int numNoProgress = 100 - numProgress;
        String bar = "";
        bar += "\n" + currentAmount +" Exp |" + Colors.GREEN;
        for(int i = 0; i < numProgress; i++){
            bar += "=";
        }
        bar += Colors.RED;
        for(int i = 0; i < numNoProgress; i++){
            bar += ".";
        }
        bar += Colors.RESET + "| " + goal + " Exp\n";
        return bar;
    }
    public int getLevel(){
        return level;
    }
    // /**
    //  * testing function only
    //  * @param currentAmount
    //  * @param goal
    //  */
    // public static void showExpBar(double currentAmount, double goal){
    //     int numEquals = (int)((currentAmount / goal) * 100);
    //     int numSpaces = 100 - numEquals;
    //     System.out.print("\n" + currentAmount +" |");
    //     for(int i = 0; i < numEquals; i++){
    //         System.out.print("=");
    //     }
    //     for(int i = 0; i < numSpaces; i++){
    //         System.out.print(" ");
    //     }
    //     System.out.println("| " + goal + "\n");
    // }
    public void setExp(double currentExp) {
        currentAmount = currentExp;
    }
    public void setLevel(int l) {
        level = l;
    }
    public void setMaxExp(int i) {
        goal = i;
    }

}
