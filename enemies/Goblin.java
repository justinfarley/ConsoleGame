package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.Stick;

public class Goblin extends Enemy {

    public static final String NAME = Colors.RED + "Goblin" + Colors.RESET;
    public static final int STARTING_HEALTH = 5;
    public Goblin(String n) {
        super(n, STARTING_HEALTH);
        expWorth = 2;
        numMoves = 1;
        speed = 1;
        setWeapon();
    }
    private static final Weapon[] GOBLIN_WEAPONS = 
    {
        Stick.getPreset(),
    };
    private void setWeapon(){
        weapon = GOBLIN_WEAPONS[rand.nextInt(GOBLIN_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException {
        super.interact();
    }
}
