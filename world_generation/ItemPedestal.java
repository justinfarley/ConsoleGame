package world_generation;

import java.util.Random;

import javax.crypto.interfaces.DHPrivateKey;

import helpers.*;
import interactions.IInteractable;
import main_game.GameLoop;
import main_game.player.inventory.items.Item;

public class ItemPedestal extends Tile implements IInteractable {
    public static final char SYMBOL = 'Y';
    public static final double SPAWN_CHANCE = 0.1;
    public static final double LEVEL_UP_SPAWN_CHANCE = 1;
    public static final int SPAWN_CHECK_RADIUS = 15;
    public static final int MAX_IN_CHECK_RADIUS = 2;
    public static final String NAME = "Item Pedestal";

    private boolean collected = false;
    private final String COLOR = Colors.BLUE_BACKGROUND + Colors.BLACK;
    private Item item;
    public ItemPedestal(){
        setValue(SYMBOL);
        name = NAME;
        tileType = TileType.NOT_WALKABLE;
    }
    public ItemPedestal(Item item) { //predefined item for predetermined spawns i guess if we ever get there
        this.item = item;
        name = NAME;
        setValue(SYMBOL);
        //rollItem();
    }
    public String toString(){
        return COLOR + SYMBOL + Colors.RESET;
    }
    public void rollItem() throws InterruptedException{
        collected = true;
        DialogueHelper.sayText("The " + Colors.BLUE + NAME + Colors.RESET + " contains: ", 15, false);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                DialogueHelper.sayTextInColor("?", Colors.getRandomColor(), 25, false);
            }
            System.out.print("\b\b\b");
        }
        int level = GameLoop.getPlayer().getExperience().getLevel();
        item = Items.getRandomItemFromLevel(level);
        DialogueHelper.sayTextln(item + "!", GameLoop.TEXT_SPEED, false);
        DialogueHelper.waitForMillis(1000);
        item.addToInventory();
    }
    public static boolean rollTile(){
        Random rand = new Random();
        return rand.nextDouble() <= SPAWN_CHANCE;
    }
    private void replaceSelfWithPlayer(){
        GameLoop.getMap().moveTile(GameLoop.getPlayer(), position[0], position[1]);
    }
    @Override
    public void interact() throws InterruptedException {
        rollItem();
        replaceSelfWithPlayer();
    }
}
