package main_game.player.inventory.items.weapons.level_1;

import java.lang.invoke.WrongMethodTypeException;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 4-5
 * Crit: 0.25
 */
public class BrassKnuckles extends Weapon {
    private static final int MIN_DAMAGE = 3;
    private static final int MAX_DAMAGE = 6;
    private static final Range WORTH = new Range(4, 6);
    private static final String NAME = "Brass Knuckles";
    public BrassKnuckles(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //3-5 damage
        critChance = 0.25; //high crit chance
    }
    public static BrassKnuckles getPreset(){
        return new BrassKnuckles(NAME);
    }
    @Override
    public String toString(){
        return (Colors.GREEN + NAME + Colors.RESET);
    }

}
