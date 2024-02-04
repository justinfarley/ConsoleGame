package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_4.Club;

public class Giant extends Enemy{
    public static final String NAME = Colors.RED + "Giant" + Colors.RESET;
    public static final int STARTING_HEALTH = 100;
    public Giant(String n) {
        super(n, STARTING_HEALTH);
        expWorth = 50;
        numMoves = 1;
        speed = 1;
        setWeapon();
    }
    private static final Weapon[] GIANT_WEAPONS = 
    {
        Club.getPreset(),
    };
    private void setWeapon(){
        weapon = GIANT_WEAPONS[rand.nextInt(GIANT_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException{
        super.interact();
    }
}
