package main_game.player.inventory.items.weapons.level_0;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 1
 * Crit: 0.05
 * Worth: 1
 */
public class Stick extends Weapon{
    private static final int DAMAGE = 1;
    private static final Range WORTH = new Range(1, 2);
    private static final String NAME = "Stick";
    public Stick(String n) {
        super(n, WORTH);
        damageRange = new Range(DAMAGE, DAMAGE + 1); //1 damage
        critChance = 0.05;
    }

    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }
    public static Stick getPreset() {
        return new Stick("Stick");
    }

}