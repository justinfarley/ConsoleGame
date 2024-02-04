package main_game.player.inventory.items.weapons.level_1;

import java.lang.invoke.WrongMethodTypeException;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 2-6
 * Crit: 0.5
 * Worth: 1-5
 */
public class BowAndArrow extends Weapon {
    private static final int MIN_DAMAGE = 2;
    private static final int MAX_DAMAGE = 7;
    private static final Range WORTH = new Range(1, 6);
    private static final String NAME = "Bow and Arrow";
    public BowAndArrow() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE);
        critChance = 0.5; //high crit chance
    }
    public static BowAndArrow getPreset(){
        return new BowAndArrow();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }
}
