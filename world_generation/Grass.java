package world_generation;

import helpers.Colors;

public class Grass extends Tile {
    public static final char SYMBOL = '.';

    public Grass(){
        setValue(SYMBOL);
        tileType = TileType.WALKABLE;
        name = "Grass";
    }
    public String toString(){
        return Colors.GREEN_BACKGROUND + SYMBOL + Colors.RESET;
    }
}
