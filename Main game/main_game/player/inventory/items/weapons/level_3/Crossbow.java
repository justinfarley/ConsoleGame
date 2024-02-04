package main_game.player.inventory.items.weapons.level_3;

import java.lang.invoke.WrongMethodTypeException;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 5-15
 * Crit: 0.5
 * Worth: 1-5
 */
public class Crossbow extends Weapon {
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 16;
    private static final Range WORTH = new Range(1, 6);
    private static final String NAME = "Crossbow";
    public Crossbow() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE);
        critChance = 0.5; //high crit chance
    }
    public static Crossbow getPreset(){
        return new Crossbow();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }
}
