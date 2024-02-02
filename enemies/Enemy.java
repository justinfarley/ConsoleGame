package enemies;

import java.util.Random;

import actions.Move;
import event_handling.Listener;
import helpers.Colors;
import helpers.DialogueHelper;
import helpers.Enemies;
import helpers.Enemies.EnemyType;
import interactions.Battle;
import interactions.IInteractable;
import main_game.GameLoop;
import main_game.player.Player;
import main_game.player.inventory.items.weapons.Weapon;
import world_generation.Grass;
import world_generation.Tile;
import world_generation.WorldMap;
/**
 * Definable attributes: expWorth, health, numMoves, speed, weapon
 */
public abstract class Enemy extends Tile implements IInteractable{
    protected double health;
    protected double speed;
    protected double expWorth;
    protected Weapon weapon;
    private int opportunities; 
    private boolean despawned = false;
    public static final char SYMBOL = 'O';
    protected Random rand = new Random();
    protected int numMoves; //steps the enemy can take per player move
    protected Enemy(String n, double h){
        name = n;
        health = h;
        setValue(Enemy.SYMBOL);
        tileType = TileType.NOT_WALKABLE;
        this.opportunities = 25;
    }
    //I see no reason to make an IDamagable YET... so building the damage into the base class
    public void takeDamage(double damage) throws InterruptedException{
        health -= damage;
        if(health <= 0){
            die();
        }
        //TODO: turn excess damage into exp maybe if i implement that
    }
    private void die() throws InterruptedException{
        //turn into grass tile
        //give player the exp and stuff
        DialogueHelper.sayTextln("The " + name + " has been defeated! Gained " + expWorth + " exp!", GameLoop.TEXT_SPEED, false);
        DialogueHelper.waitForMillis(500);
        Player player = GameLoop.getPlayer();
        player.getExperience().gainExp(expWorth);
        player.getExperience().showExpBar();
        DialogueHelper.waitForMillis(1000);
        WorldMap map = GameLoop.getMap();
        map.editMap(position[0], position[1], player); //put player where the enemy used to be to "kill" it.
    }
    public void move(){
        if(despawned) return;
        //THIS IS FOR EACH MOVEMENT, give each enemy different number of moves
        for(int i = 0; i < numMoves; i++){
            moveTile();
        }
        opportunities--; //total move opportunities, not just how many tiles
        if(opportunities <= 0){
            despawn();
        }
    }
    private void despawn(){
        System.out.print("despawned");
        despawned = true;
        GameLoop.getMap().editMap(position[0], position[1], new Grass());
    }
    @Override
    public void interact() throws InterruptedException{
        new Battle(this, GameLoop.getPlayer());
    }
    private void moveTile(){
        //50% chance to move towards the player, 50% chance to move a random adjacent tile (but could still be towards the player) mess with numbers later
        double roll = rand.nextDouble();
        WorldMap map = GameLoop.getMap();
        
        if(roll < 0){
            //maybe despawn or teleport
        }
        else{
            //random direction
            int num = rand.nextInt(1, 5); //1-4
            int tileX = getCoords()[0];
            int tileY = getCoords()[1];
            switch(num){
                case 1:
                map.moveTile(this, tileX - 1, tileY);
                break;
                case 2:
                map.moveTile(this, tileX + 1, tileY);
                break;
                case 3:
                map.moveTile(this, tileX, tileY - 1);
                break;
                case 4:
                map.moveTile(this, tileX, tileY + 1);
                break;
                default:
                map.moveTile(this, tileX, tileY + 1);
                break;
            }
        }
    }
    public String toString(){
        return Colors.RED_BACKGROUND + Colors.BLACK + SYMBOL + Colors.RESET;
    }

    public void setSpeed(double s){
        speed = s;
    }
    public double getSpeed(){
        return speed;
    }
    public double getHealth(){
        return health;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    public static Enemy getRandomEnemy(){
        Random rand = new Random();
        EnemyType[] arr = Enemies.ENEMIES_PER_LEVEL[GameLoop.getPlayer().getExperience().getLevel()];
        EnemyType type = arr[rand.nextInt(arr.length)];

        switch (type){
            case GOBLIN:
            return new Goblin(Goblin.NAME);
            case ORC:
            return new Orc(Orc.NAME);
            case PLANT:
            return new ViciousPlant(ViciousPlant.NAME);
            default:
            return new Clown(Clown.NAME);                
        }
        
    }
}
