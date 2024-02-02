package main_game.player.inventory.items.weapons;

import java.util.Random;

import enemies.Enemy;
import helpers.Colors;
import helpers.DialogueHelper;
import helpers.INameable;
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
        this.worthRange = worthRange;
        worth = worthRange.getNumberInRange();
    }
    public void attack(Enemy enemy) throws InterruptedException{
        int damage = damageRange.getNumberInRange();
        if(rollCrit()){
            damage *= 2;
            DialogueHelper.sayTextln("It's a " + Colors.YELLOW + "Critical Hit! " + Colors.RESET + getAttackMessage(enemy, damage, true), Battle.BATTLE_TEXT_SPEED, false);
        }
        else{
            DialogueHelper.sayTextln(getAttackMessage(enemy, damage, false), Battle.BATTLE_TEXT_SPEED, false);
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
            DialogueHelper.sayTextln("It's a " + Colors.YELLOW + "Critical Hit! " + Colors.RESET + getEnemyAttackMessage(enemy, damage, true), Battle.BATTLE_TEXT_SPEED, false);
        }
        else{
            DialogueHelper.sayTextln(getEnemyAttackMessage(enemy, damage, false), Battle.BATTLE_TEXT_SPEED, false);
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
    private String getAttackMessage(Enemy enemy, int damage, boolean crit){
        String ret = PLAYER_ATTACK_MESSAGES[rand.nextInt(PLAYER_ATTACK_MESSAGES.length)];
        ret = ret.replace("ENEMY", enemy.getName());
        ret = ret.replace("DAMAGE", (crit ? Colors.YELLOW : Colors.GREEN) + damage);
        return ret;
    }
    private String getEnemyAttackMessage(Enemy enemy, int damage, boolean crit){
        String ret = ENEMY_ATTACK_MESSAGES[rand.nextInt(ENEMY_ATTACK_MESSAGES.length)];
        ret = ret.replace("ENEMY", enemy.getName());
        ret = ret.replace("DAMAGE", (crit ? Colors.YELLOW : Colors.RED) + damage);
        return ret;
    }
    //TODO: have a show weapon stats command that will display the stats
    public String getWeaponStats(){
        String stats = "";
        String damage = ((damageRange.getMax() - damageRange.getMin()) == 1) ? damageRange.getMin() + "" : (damageRange.getMin() + " - " + (damageRange.getMax() - 1));

        String w = ((worthRange.getMax() - worthRange.getMin()) == 1) ? worthRange.getMin() + "" : (worthRange.getMin() + " - " + (worthRange.getMax() - 1));

        stats += toString() + " STATS:\n\t";
        stats += "Damage: " + damage + "\n\t";
        stats += "Crit chance: " + (critChance * 100) + "%\n\t";
        stats += "Worth: " + w + " gold";
        return stats;
    }
    private final String[] PLAYER_ATTACK_MESSAGES = 
    {
        "You attacked the ENEMY with your " + toString() + " for DAMAGE" + Colors.RESET + " damage!",
    };
    private final String[] ENEMY_ATTACK_MESSAGES = 
    {
        "The ENEMY attacked you with a " + toString() + " for DAMAGE" + Colors.RESET + " damage!"
    };
    public String getName(){
        return name;
    }
    public abstract String toString();
}
