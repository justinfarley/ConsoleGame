package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_4.Club;
import main_game.player.inventory.items.weapons.level_5.MetalBat;

public class Vampire extends Enemy{
    public static final String NAME = Colors.RED + "Vampire" + Colors.RESET;
    public static final int STARTING_HEALTH = 150;
    public Vampire(String n) {
        super(n, STARTING_HEALTH);
        expWorth = 100;
        numMoves = 3;
        speed = 7;
        setWeapon();
    }
    private static final Weapon[] VAMPIRE_WEAPONS = 
    {
        MetalBat.getPreset(),
    };
    private void setWeapon(){
        weapon = VAMPIRE_WEAPONS[rand.nextInt(VAMPIRE_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException{
        super.interact();
    }
}
