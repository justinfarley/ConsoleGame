package main_game.player.inventory.items.weapons.level_2;

import java.lang.invoke.WrongMethodTypeException;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 0-10 
 * Crit: 0.5
 * Worth: 1-5
 * Price: 25
 */
public class Longbow extends Weapon {
    private static final int MIN_DAMAGE = 0;
    private static final int MAX_DAMAGE = 11;
    private static final Range WORTH = new Range(1, 6);
    private static final String NAME = "Longbow";
    public Longbow() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE);
        critChance = 0.5; //high crit chance
        price = 25;
    }
    public static Longbow getPreset(){
        return new Longbow();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }
}
