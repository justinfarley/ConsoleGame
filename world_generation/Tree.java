package world_generation;

import helpers.Colors;

import java.util.Random;

public class Tree extends Tile{
    public static final int SPAWN_CHECK_RADIUS = 3;
    public static final int ALLOWED_NUM_TREES_IN_RADIUS = 5;
    public static final double SPAWN_CHANCE = 0.2;
    public static final char SYMBOL = '|';


    public Tree(){
        setValue(SYMBOL);
        tileType = TileType.NOT_WALKABLE;
        name = "Tree";
    }
    public static boolean rollTile(){
        Random random = new Random();
        double num = random.nextDouble();

        return num <= SPAWN_CHANCE;
    }
    public String toString(){
        return Colors.YELLOW_BACKGROUND + SYMBOL + Colors.RESET;
    }
}
