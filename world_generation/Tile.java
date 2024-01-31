package world_generation;
public class Tile {
    public enum TileType{
        WALKABLE,
        NOT_WALKABLE;
    }
    private char value;
    protected int[] position = new int[2];

    protected TileType tileType;

    protected String name;
    private Tile underTile;

    public char getValue(){
        return value;
    }
    public void setValue(char v){
        value = v;
    }
    public int[] getCoords(){
        return position;
    }
    public void setCoords(int x, int y){
        position[0] = x;
        position[1] = y;
    }
    public static Tile getRandomTile(int x, int y, WorldMap map){
        if(Tree.rollTile() && map.findAllInArea(x, y, Tree.SPAWN_CHECK_RADIUS, Tree.SYMBOL) < Tree.ALLOWED_NUM_TREES_IN_RADIUS){
            return new Tree();
        }
        else if(ItemPedestal.rollTile() && map.findAllInArea(x, y, ItemPedestal.SPAWN_CHECK_RADIUS, ItemPedestal.SYMBOL) < ItemPedestal.MAX_IN_CHECK_RADIUS){ // if tree doesnt get rolled try for an item pedastal
            return new ItemPedestal();
        }
        else{
            return new Grass();
        }
    }
    public String getName(){
        return name;
    }
    public TileType getTileType(){
        return tileType;
    }
    public Tile getTileUnder(){
        return underTile;
    }
    public void setTileUnder(Tile t){
        underTile = t;
    }
}
