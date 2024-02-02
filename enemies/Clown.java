package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_3.WoodenBat;

public class Clown extends Enemy{
    public static final String NAME = Colors.RED + "Clown" + Colors.RESET;
    public static final int STARTING_HEALTH = 50;
    public Clown(String n) {
        super(n, STARTING_HEALTH);
        expWorth = 15;
        numMoves = 2;
        speed = 3;
        setWeapon();
    }
    private static final Weapon[] CLOWN_WEAPONS = 
    {
        WoodenBat.getPreset(),
    };
    private void setWeapon(){
        weapon = CLOWN_WEAPONS[rand.nextInt(CLOWN_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException{
        super.interact();
    }
}
