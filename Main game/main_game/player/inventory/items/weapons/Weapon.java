package main_game.player.inventory.items.weapons;

import java.util.Random;

import enemies.Enemy;
import helpers.Colors;
import helpers.DialogueHelper;
import helpers.Range;
import interactions.Battle;
import main_game.player.Player;
import main_game.player.inventory.items.Item;

/**
 * Weapon properties: critChance, damageRange, worthRange
 */
public abstract class Weapon extends Item{
    protected Range damageRange;
    protected double critChance;
    private int worth;
    protected Range worthRange;
    private static Random rand = new Random();
    protected Weapon(String n, Range worthRange) {
        super(n);
        worth = worthRange.getNumberInRange();
    }
    public void attack(Enemy enemy) throws InterruptedException{
        int damage = damageRange.getNumberInRange();
        if(rollCrit()){
            damage *= 2;
            DialogueHelper.sayTextln("It's a " + Colors.YELLOW + "Critical Hit!" + Colors.RESET + " You attacked the  " + enemy.getName() + " with your " + toString() + " for " + Colors.YELLOW + damage + Colors.RESET + " damage!", Battle.BATTLE_TEXT_SPEED, false);
        }
        else{
            DialogueHelper.sayTextln("You attacked the " + enemy.getName() + " with your " + toString() + " for " + Colors.GREEN + damage + Colors.RESET + " damage!", Battle.BATTLE_TEXT_SPEED, false);
        }
        DialogueHelper.waitForMillis(500);
        enemy.takeDamage(damage);
        if(enemy.getHealth() > 0){
            DialogueHelper.sayTextln("The " + enemy.getName() + " has " + enemy.getHealth() + " health remaining.", Battle.BATTLE_TEXT_SPEED, false);
        }
        
    }
    public void attack(Enemy enemy, Player player) throws InterruptedException{
        int damage = damageRange.getNumberInRange();
        if(rollCrit()){
            damage *= 2;
            DialogueHelper.sayTextln("It's a " + Colors.YELLOW + "Critical Hit!" + Colors.RESET + " The " + enemy.getName() + " attacked you with " + toString() + " for " + Colors.YELLOW + damage + Colors.RESET + " damage!", Battle.BATTLE_TEXT_SPEED, false);
        }
        else{
            DialogueHelper.sayTextln("The " + enemy.getName() + " attacked you with a " + toString() + " for " + Colors.RED + damage + Colors.RESET + " damage!", Battle.BATTLE_TEXT_SPEED, false);
        }
        DialogueHelper.waitForMillis(500);
        player.takeDamage(damage);
        DialogueHelper.sayTextln("You have " + player.getHealth() + " health remaining.", Battle.BATTLE_TEXT_SPEED, false);
    }
    private boolean rollCrit(){
        double value = rand.nextDouble();

        return value <= critChance;
    }
    public int getWorth(){
        return worth;
    }
    public abstract String toString();
}
