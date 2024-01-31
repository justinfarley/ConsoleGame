package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.Stick;

public class Goblin extends Enemy {

    public static final String NAME = Colors.RED + "Goblin" + Colors.RESET;
    public Goblin(String n, double h) {
        super(n, h);
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
