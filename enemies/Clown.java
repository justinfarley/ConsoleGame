package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_3.WoodenBat;

public class Clown extends Enemy{
    public static final String NAME = Colors.RED + "Orc" + Colors.RESET;
    public Clown(String n, double h) {
        super(n, h);
        expWorth = 7;
        health = 10;
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
