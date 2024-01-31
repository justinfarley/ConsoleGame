package actions;

import java.util.ArrayList;
import java.util.Scanner;

import event_handling.Event;
import helpers.DialogueHelper;
import interactions.IInteractable;
import main_game.GameLoop;
import main_game.player.Player;
import world_generation.Grass;
import world_generation.Tile;
import world_generation.WorldMap;
import world_generation.Tile.TileType;

public class Move extends Action{
    private Scanner scan = new Scanner(System.in);
    private Player player;
    private WorldMap map;
    public static final int BASE_STEPS = 10;
    private static int maxSteps;
    public static Event onPlayerMoved = new Event();
    private static final char LEFT = 'a', RIGHT = 'd', UP = 'w', DOWN = 's';
    public Move(String n, Player player, WorldMap map){
        name = n;
        this.player = player;
        this.map = map;
    }
    @Override
    public void invoke() throws InterruptedException{
        int steps = 1;
        DialogueHelper.sayText("Where would you like to move? Enter WASD for that direction, or STOP to stop (max steps: " + maxSteps + ") ", 0, false);
        while(move() && steps < maxSteps){
            DialogueHelper.sayText("Enter WASD or STOP (" + (maxSteps - steps) + " steps remaining): ", 0, false);
            steps++;
        }
        super.invoke();
    }
    private boolean move() throws InterruptedException{
        String response = scan.nextLine().toLowerCase();
        if(response.equals("stop")){
            return false;
        }
        char c = response.charAt(0);

        switch(c){
            case LEFT:
            moveOnMap(-1, 0);
            break;
            case RIGHT:
            moveOnMap(1, 0);
            break;
            case UP:
            moveOnMap(0, -1);
            break;
            case DOWN:
            moveOnMap(0, 1);
            break;
            default:
                DialogueHelper.sayText("Not a valid entry, pick a valid entry: ", GameLoop.TEXT_SPEED, false);
                move();
            break;
        }
        return true;
    }
    private void moveOnMap(int x, int y) throws InterruptedException
    {
        int[] currentPos = player.getCoords();
        int playerY = currentPos[0];
        int playerX = currentPos[1];
        //change the tile the player was on back to the temp
        map.editMap(playerY, playerX, player.getTileUnderPlayer());

        if(x == 1){
            doSwap(playerY, playerX + 1);
        }
        else if(x == -1){
            doSwap(playerY, playerX - 1);
        }
        else if(y == 1){
            doSwap(playerY + 1, playerX);
        }
        else if(y == -1){
            doSwap(playerY - 1, playerX);
        }
        onPlayerMoved.invokeEvent(); //only invoke once player is for sure moved to avoid more than one event invoke (kinda broken still)
        //print map to show update
        map.print();
    }
    private void doSwap(int playerY, int playerX) throws InterruptedException{
        //if tile can actually be moved to
        Tile t = map.getTile(playerY, playerX);
        if(t.getTileType() != TileType.WALKABLE){
            if(t instanceof IInteractable){
                ((IInteractable)t).interact();
                map.editMap(playerY, playerX, new Grass());
                doSwap(playerY, playerX);
            }
            else{
                DialogueHelper.sayText("That tile (" + t.getName() + ") is blocked! Pick a different direction: ", 10, false);
                move();
            }
            return;
        }
        else{

        }
        //change the tile the player is moving to as the tile under the player
        player.setTileUnderPlayer(map.getWorldMap()[playerY][playerX]);

        //move player to the tile
        map.editMap(playerY, playerX, player);

        //update player coords
        player.setCoords(playerY, playerX);
    }
    @Override
    public boolean canInvoke() {
        return true;
    }
    public static int getMaxSteps(){
        return maxSteps;
    }
    public static void setMaxSteps(int i){
        maxSteps = i;
    }
}
