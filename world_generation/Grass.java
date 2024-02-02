package world_generation;

import helpers.Colors;

public class Grass extends Tile {
    public static final char SYMBOL = '.';
    public static final String NAME = "Grass";

    public Grass(){
        setValue(SYMBOL);
        tileType = TileType.WALKABLE;
        name = NAME;
    }
    public String toString(){
        return Colors.GREEN_BACKGROUND + SYMBOL + Colors.RESET;
    }
}
