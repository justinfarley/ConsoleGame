package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.Stick;
import main_game.player.inventory.items.weapons.level_1.BrassKnuckles;

public class ViciousPlant extends Enemy {

    public static final String NAME = Colors.RED + "Vicious Plant" + Colors.RESET;
    public static final int STARTING_HEALTH = 25;
    public ViciousPlant(String n) {
        super(n, STARTING_HEALTH);
        expWorth = 12;
        numMoves = 1;
        speed = 1;
        setWeapon();
    }
    private static final Weapon[] PLANT_WEAPONS = 
    {
        BrassKnuckles.getPreset(),
    };
    private void setWeapon(){
        weapon = PLANT_WEAPONS[rand.nextInt(PLANT_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException {
        super.interact();
    }
}
