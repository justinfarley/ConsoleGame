package enemies;

import helpers.Colors;
import main_game.player.inventory.items.weapons.*;
import main_game.player.inventory.items.weapons.level_0.WoodenSword;

public class Orc extends Enemy{
    public static final String NAME = Colors.RED + "Orc" + Colors.RESET;
    public Orc(String n, double h) {
        super(n, h);
        expWorth = 7;
        health = 10;
        numMoves = 2;
        speed = 3;
        setWeapon();
    }
    private static final Weapon[] ORC_WEAPONS = 
    {
        WoodenSword.getPreset(),
    };
    private void setWeapon(){
        weapon = ORC_WEAPONS[rand.nextInt(ORC_WEAPONS.length)];
    }
    @Override
    public void interact() throws InterruptedException{
        super.interact();
    }
}
