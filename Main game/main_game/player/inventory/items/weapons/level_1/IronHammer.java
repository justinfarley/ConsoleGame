package main_game.player.inventory.items.weapons.level_1;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 4-7
 * Crit: 0.05
 * Worth: 4-5
 */
public class IronHammer extends Weapon {
    private static final int MIN_DAMAGE = 4;
    private static final int MAX_DAMAGE = 8;
    private static final Range WORTH = new Range(4, 6);
    private static final String NAME = "Iron Hammer";
    public IronHammer(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //4-7 damage
        critChance = 0.05;
    }
    public static IronHammer getPreset(){
        return new IronHammer(NAME);
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;

    }
}
