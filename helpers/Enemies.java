package helpers;

import java.util.Random;

import enemies.*;
import main_game.GameLoop;

public class Enemies {
    private static final Random RAND = new Random();
    private Enemies(){

    }
    /**
    * all enemy CLASSES
    */
    public static final Class<?>[] ENEMYCLASSES = new Class[]
    {
        Goblin.class,
        Orc.class,
        ViciousPlant.class,
        Clown.class,
        Giant.class,
    };
    public enum EnemyType {
        GOBLIN,
        ORC,
        PLANT,
        CLOWN,
        GIANT,
        VAMPIRE,
    }
    protected static final EnemyType[] LEVEL_0 = 
    {
        EnemyType.GOBLIN,
    };
    protected static final EnemyType[] LEVEL_1 = 
    {
        EnemyType.ORC,
    };
    protected static final EnemyType[] LEVEL_2 =
    {
        EnemyType.PLANT,
    };
    protected static final EnemyType[] LEVEL_3 =
    {
        EnemyType.CLOWN,
    };
    protected static final EnemyType[] LEVEL_4 =
    {
        EnemyType.GIANT,
    };
    protected static final EnemyType[] LEVEL_5 =
    {
        EnemyType.VAMPIRE,
    };
    public static final EnemyType[][] ENEMIES_PER_LEVEL = 
    {
        LEVEL_0,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5,
    };
    public static Enemy getRandomEnemy(){
        Random rand = new Random();
        int level = GameLoop.getPlayer().getExperience().getLevel();
        EnemyType[] arr = Enemies.ENEMIES_PER_LEVEL[level];
        EnemyType type = arr[rand.nextInt(arr.length)];

        switch (type){
            case GOBLIN:
            return new Goblin(Goblin.NAME);
            case ORC:
            return new Orc(Orc.NAME);
            case PLANT:
            return new ViciousPlant(ViciousPlant.NAME);
            case CLOWN:
            return new Clown(Clown.NAME);
            case GIANT:
            return new Giant(Giant.NAME);
            case VAMPIRE:
            return new Vampire(Vampire.NAME);
            default:
            return new Clown(Clown.NAME);                
        }
    }
}
