package world_generation;

import helpers.Colors;

public class Edge extends Tile {

    private static final char SYMBOL = '#';
    public static final String NAME = "Edge of World";

    public Edge(){
        setValue(SYMBOL);
        tileType = TileType.NOT_WALKABLE;
        name = NAME;
    }

    public String toString(){
        return Colors.BLACK_BACKGROUND + Colors.WHITE + SYMBOL + Colors.RESET;
    }
}
