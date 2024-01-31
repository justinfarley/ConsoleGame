package helpers;

import java.util.Random;

import enemies.*;

public class Enemies {
    private static final Random RAND = new Random();
    private Enemies(){

    }
    public enum EnemyType {
        GOBLIN,
        ORC,
        PLANT,
        CLOWN,
    }
    //change the "getPreset" things, it does not work I dont think
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

    public static final EnemyType[][] ENEMIES_PER_LEVEL = 
    {
        LEVEL_0,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
    };
    /**
     * all enemy CLASSES
     */
    public static final Class<?>[] ENEMYCLASSES = new Class[]
    {
        Clown.class,
        Goblin.class,
        ViciousPlant.class,
        Clown.class,
    };
}
