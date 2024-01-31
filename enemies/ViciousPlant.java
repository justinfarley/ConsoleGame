package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.Stick;
import main_game.player.inventory.items.weapons.level_1.BrassKnuckles;

public class ViciousPlant extends Enemy {

    public static final String NAME = Colors.RED + "Vicious Plant" + Colors.RESET;
    public ViciousPlant(String n, double h) {
        super(n, h);
        expWorth = 2;
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
