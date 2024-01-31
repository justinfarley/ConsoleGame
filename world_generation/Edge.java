package world_generation;

import helpers.Colors;

public class Edge extends Tile {

    private static final char SYMBOL = '#';

    public Edge(){
        setValue(SYMBOL);
        tileType = TileType.NOT_WALKABLE;
        name = "Edge of World";
    }

    public String toString(){
        return Colors.BLACK_BACKGROUND + Colors.WHITE + SYMBOL + Colors.RESET;
    }
}
