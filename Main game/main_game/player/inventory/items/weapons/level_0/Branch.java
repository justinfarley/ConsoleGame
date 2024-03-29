package main_game.player.inventory.items.weapons.level_0;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 1-2
 * Crit: 0.07
 * Worth: 2
 * Price: 2
 */
public class Branch extends Weapon{
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 3;
    private static final Range WORTH = new Range(1, 3);
    private static final String NAME = "Branch";
    public Branch(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //1-2 damage
        critChance = 0.07;
        price = 2;
    }

    @Override
    public String toString() {
        return Colors.GREEN + NAME + Colors.RESET;
    }

    public static Branch getPreset() {
        return new Branch(NAME);
    }

}