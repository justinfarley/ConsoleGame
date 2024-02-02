package world_generation;

import main_game.GameLoop;
import main_game.Main;
import main_game.player.Experience;
import main_game.player.Player;
import save_system.PlayerData;
import save_system.SaveManager;
import world_generation.Tile.TileType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import actions.Move;
import enemies.*;
import helpers.Enemies;
import helpers.Tiles;
import interactions.IInteractable;

import java.io.*;

public class WorldMap {
    private int worldSize; //world will be square, same size x and y
    private Tile[][] map;
    private int numPossibleEnemySpawns;
    private int numPossibleItemPedestalSpawns;
    private static final int SMALL_SIZE = 20, MEDIUM_SIZE = 50, LARGE_SIZE = 100, XLARGE_SIZE = 150;
    private static final Random RAND = new Random();
    public enum Size{
        SMALL(SMALL_SIZE),
        MEDIUM(MEDIUM_SIZE),
        LARGE(LARGE_SIZE),
        XLARGE(XLARGE_SIZE);

        int _size;
        Size(int s){
            _size = s;
        }
    }

    public WorldMap(){
        map = null;
        Move.onPlayerMoved.addListener(this::moveEnemies);
        Move.onPlayerMoved.addListener(() -> rollEnemySpawn(numPossibleEnemySpawns));
        Experience.onLevelUp.addListener(() -> 
        {
            
            rollItemPedestalSpawn(numPossibleItemPedestalSpawns);

        });
    }

    public WorldMap(int size){
        worldSize = size;
        map = new Tile[worldSize][worldSize];
        originalMap();
    }

    public WorldMap(int size, Tile[][] map){
        worldSize = size;
        map = Arrays.copyOf(map, worldSize);
        originalMap();
    }
    /**
     * fills with only grass
     */
    public void originalMap(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = new Grass();
            }
        }
        
    }
    /**
     * fills with random tiles
     */
    public void generate(Player player){
        if(map == null) map = new Tile[worldSize][worldSize];

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(i == 0 || j == 0 || i == map.length - 1 || j == map[i].length - 1){
                    Tile t = new Edge();
                    t.setCoords(i, j);
                    map[i][j] = t;
                }
                else{
                    Tile newTile = Tile.getRandomTile(i, j, this);
                    newTile.setCoords(i, j);
                    map[i][j] = newTile;
                }
                
            }
        }
        map[map.length / 2][map[0].length / 2] = new Grass();
        //store type of tile under the player for later when they move
        player.setTileUnderPlayer(map[map.length / 2][map[0].length / 2]);
        //replace tile with the player
        map[map.length / 2][map[0].length / 2] = player;
        //store player coordinates (index)
        player.setCoords(map.length / 2, map[0].length / 2);
        
    }

    public int findAllInArea(int locX, int locY, int radius, char value){
        ArrayList<Tile> tilesInArea = new ArrayList<>();
        int countInArea = 0;
        for(int x = locX - radius; x <= locX + radius; x++)
        {
            for(int y = locY - radius; y <= locY + radius; y++)
            {
                try{
                    if(map[x][y] != null && map[x][y].getValue() == value)
                        tilesInArea.add(map[x][y]);
                }
                catch(IndexOutOfBoundsException e){

                }
            }
        }
        for(Tile t : tilesInArea){
            if(t != null && t.getValue() == value){
                countInArea++;
            }
        }
        return countInArea;
    }
    /**
     * Changes world size of the map. (DOES NOT UPDATE THE MAP, CALL {@link WorldMap#generate(Player) GENERATE} TO REGENERATE)
     * @param size
     */
    public void chooseWorldSize(Size size){
        worldSize = size._size;
        switch(size){
            case SMALL:
            numPossibleEnemySpawns = 1;
                break;
            case MEDIUM:
            numPossibleEnemySpawns = 2;
                break;
            case LARGE:
            numPossibleEnemySpawns = 3;
                break;
            case XLARGE:
            numPossibleEnemySpawns = 4;
                break;
            default:
                break;
        }
        Move.setMaxSteps(Move.BASE_STEPS * numPossibleEnemySpawns);
        numPossibleItemPedestalSpawns = Experience.POSSIBLE_ITEM_PEDESTAL_SPAWNS[GameLoop.getPlayer().getExperience().getLevel()];
    }
    public void setWorldSize(int size){
        worldSize = size;
    }
    /**
     * writes the map to a file. Can be used to update the file whenever changes to the map are made.
     * @param path path of the file to write to
     * @throws IOException
     */
    public void writeMapToFile(String path) throws IOException{
        File f = new File(path);
        final FileWriter writer = new FileWriter(f);

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
            }
            writer.append("\n");
        }
        
        writer.close();
    }
    /**
     * prints the map to the console
     */
    public void print(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        updateMapFile();
        SaveManager.saveGame(new PlayerData(GameLoop.getPlayer()));
    }
    public void updateMapFile(){
        try{
            writeMapToFile(Main.FILE_PATH);
        }
        catch(IOException e){
            System.err.println("Error writing to file.");
            e.printStackTrace();
        }
    }
    public void editMap(int x, int y, Tile t){
        map[x][y] = t;
        t.setCoords(x, y);
        updateMapFile();
    }
    public Tile[][] getWorldMap(){
        return map;
    }
    public Tile getTile(int x, int y){
        return map[x][y];
    }
    private <T extends Tile> ArrayList<Tile> findAllTilesOnMap(Class<T> cls){
        ArrayList<Tile> ret = new ArrayList<>();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j].getClass() == cls){
                    ret.add(map[i][j]);
                }
            }
        }
        return ret;
    }
    private ArrayList<Tile> findAllTilesOnMap(Class<?>[] classes){
        ArrayList<Tile> ret = new ArrayList<>();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                for(Class<?> c : classes){
                    if(map[i][j].getClass() == c){
                        ret.add(map[i][j]);
                        break;
                    }
                }
            }
        }
        return ret;
    }
    private int getNumTilesOnMap(char symbol){
        int count = 0;
        for(int i = 1; i < map.length - 1; i++){ //all sides are edges so no need to check them (start on 1, and end 1 before)
            for(int j = 1; j < map[i].length - 1; j++){ 
                if(map[i][j].getValue() == symbol){
                    count++;
                }
            }
        }
        return count;
    }
    //subscribes this method to the onPlayerMove event at start
    private void rollItemPedestalSpawn(int numSpawnAttempts){
        if(numSpawnAttempts == 0) return;
        //every time player moves theres a 20% chance for an enemy to spawn.
        double pedestalSpawnChance = ItemPedestal.LEVEL_UP_SPAWN_CHANCE;
        double num = RAND.nextDouble();
        ArrayList<Tile> grassTiles = findAllTilesOnMap(Grass.class);

        if(num <= pedestalSpawnChance){
            //spawn succeeds, replace one of the grass randomly with an enemy
            ItemPedestal pedestal = new ItemPedestal();
            int upperBound = grassTiles.size();
            Tile tile = grassTiles.get(RAND.nextInt(upperBound));
            int[] tilePos = tile.getCoords();
            pedestal.setCoords(tilePos[0], tilePos[1]);
            map[tilePos[0]][tilePos[1]] = pedestal;
        }
        rollItemPedestalSpawn(numSpawnAttempts - 1);
    }
    //subscribes this method to the onPlayerMove event at start
    private void rollEnemySpawn(int numSpawnAttempts){
        if(numSpawnAttempts == 0) return;
        //every time player moves theres a 20% chance for an enemy to spawn.
        int numEnemies = getNumTilesOnMap(Enemy.SYMBOL);
        int numGrass = getNumTilesOnMap(Grass.SYMBOL);
        double enemySpawnChance = (numGrass + numEnemies) == 0 ? 0.2 : 0.2 - ((double)numEnemies / (numGrass + numEnemies));
        double num = RAND.nextDouble();
        ArrayList<Tile> grassTiles = findAllTilesOnMap(Grass.class);

        if(num <= enemySpawnChance){
            //spawn succeeds, replace one of the grass randomly with an enemy
            Enemy enemy = Enemy.getRandomEnemy();
            int upperBound = grassTiles.size();
            Tile tile = grassTiles.get(RAND.nextInt(upperBound));
            enemy.setTileUnder(tile);
            int[] tilePos = tile.getCoords();
            enemy.setCoords(tilePos[0], tilePos[1]);
            map[tilePos[0]][tilePos[1]] = enemy;
        }
        rollEnemySpawn(numSpawnAttempts - 1);
    }
    public boolean moveTile(Tile tileBeingMoved, int x, int y){
        //if tile can actually be moved to
        Tile tileToMoveTo = getTile(x, y);
        if(tileToMoveTo.getTileType() != TileType.WALKABLE){
            return false;
        }
        switch(tileToMoveTo.tileType){
            case NOT_WALKABLE:
            if(tileToMoveTo instanceof IInteractable){
                try{
                    ((IInteractable)tileToMoveTo).interact();
                    moveTile(tileBeingMoved, x, y);
                    return true;
                }
                catch(InterruptedException e){
                }
            }
            return false;
            case WALKABLE:
            int[] preCoords = tileBeingMoved.getCoords();
            //change tile back to the one that was under it
            map[preCoords[0]][preCoords[1]] = tileBeingMoved.getTileUnder();
    
            //change the tile the player is moving to as the tile under the player
            tileBeingMoved.setTileUnder(map[x][y]);
    
            //move tile to the next tiles position
            map[x][y] = tileBeingMoved;
    
            //update enemy coords
            tileBeingMoved.setCoords(x, y);

            return true;
        }
        return true;
    }
    public void moveEnemies(){
        ArrayList<Tile> enemies = findAllTilesOnMap(Enemies.ENEMYCLASSES);

        for(Tile t : enemies){
            if(t instanceof Enemy){
                ((Enemy)t).move();
            }
        }
    }
    public void updatePedestalSpawns(){
        numPossibleItemPedestalSpawns = Experience.POSSIBLE_ITEM_PEDESTAL_SPAWNS[GameLoop.getPlayer().getExperience().getLevel()];
    }

    public int getWorldSize() {
        return worldSize;
    }

    public String[][] getMapTileNames(){
        String[][] arr = new String[worldSize][worldSize];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] instanceof Enemy){
                    arr[i][j] = "enemy";
                }
                else
                    arr[i][j] = map[i][j].getName();
            }
        }
        return arr;
    }

    public void setWorldMap(Tile[][] importedMap) {
        map = Arrays.copyOf(importedMap, importedMap.length);

        switch(importedMap.length){
            case SMALL_SIZE:
            numPossibleEnemySpawns = 1;
            break;
            case MEDIUM_SIZE:
            numPossibleEnemySpawns = 2;
            break;
            case LARGE_SIZE:
            numPossibleEnemySpawns = 3;
            break;
            case XLARGE_SIZE:
            numPossibleEnemySpawns = 4;
            break;
        }

        Move.setMaxSteps(Move.BASE_STEPS * numPossibleEnemySpawns);
        numPossibleItemPedestalSpawns = Experience.POSSIBLE_ITEM_PEDESTAL_SPAWNS[GameLoop.getPlayer().getExperience().getLevel()];
    }

    public Tile[][] convertNamesToTiles(String[][] mapOfNames) {
        Tile[][] arr = new Tile[mapOfNames.length][mapOfNames[0].length];
        for(int i = 0; i < mapOfNames.length; i++){
            for(int j = 0; j < mapOfNames.length; j++){
                arr[i][j] = Tiles.getTileByName(mapOfNames[i][j], i, j);
            }
        }
        return arr;
    }
}
