package main_game.player;
import java.util.Arrays;

import helpers.Colors;
import helpers.DialogueHelper;
import main_game.player.inventory.Inventory;
import main_game.player.inventory.items.weapons.Weapon;
import main_game.player.inventory.items.weapons.level_0.Stick;
import world_generation.Tile;

public class Player extends Tile{
    public static final char SYMBOL = 'X';
    private int health;
    private int maxHealth;
    private double speed = 5;
    private Tile underPlayer;
    private Weapon equippedWeapon;
    private Inventory inventory = new Inventory();
    private Experience exp;
    public Player(String name, int health, int[] position){
        this.name = name;
        this.health = health;
        maxHealth = health;
        this.position = Arrays.copyOf(position, 2);
        setValue(SYMBOL);
        exp = new Experience(this);
        Stick stick = new Stick("Stick");

    }   
    /**
     * used with potions and other items NOT on level up
     * @param amount
     */
    public void gainHealth(int amount){
        health += amount;
        if(health > maxHealth) health = maxHealth;
    }
    /**
     * increases the maximum player health and sets the health to this amount (used on level up)
     * @param amount
     */
    public void increaseMaxHealth(int amount){
        maxHealth += amount;
        health = maxHealth;
    }
    public void takeDamage(int damage){
        health -= damage;
        if(health <= 0){
            die();
        }
    }
    public void die(){
        System.out.println(name + " died!");
    }
    public void move(int x, int y){
        position[0] += x;
        position[1] += y;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setTileUnderPlayer(Tile tile){
        underPlayer = tile;
    }
    public Tile getTileUnderPlayer(){
        return underPlayer;
    }
    public void setCoords(int x, int y){
        position[0] = x;
        position[1] = y;
    }
    public int[] getCoords(){
        return position;
    }
    public String toString(){
        return Colors.PURPLE_BACKGROUND + Colors.BLACK + SYMBOL + Colors.RESET;
    }
    public void setSpeed(double s){
        speed = s;
    }
    public double getSpeed(){
        return speed;
    }
    public Inventory getInventory(){
        return inventory;
    }
    public Weapon getWeapon(){
        if(equippedWeapon == null) return null;
        return equippedWeapon;
    }
    public void equipWeapon(Weapon w){
        if(equippedWeapon != null) equippedWeapon = null;
        equippedWeapon = w;
        if(!inventory.getItems().contains(w))
            inventory.addItem(w);
    }
    public double getHealth(){
        return health;
    }
    /**
     * gets the exp object for levelling up and increases exp
     * @return
     */
    public Experience getExperience(){
        return exp;
    }
    public void displayHealth() throws InterruptedException{
        DialogueHelper.sayTextln("You now have " + health + "/" + maxHealth + " health!", SYMBOL, false);
    }
    public double getMaxHealth() {
        return maxHealth;
    }
}