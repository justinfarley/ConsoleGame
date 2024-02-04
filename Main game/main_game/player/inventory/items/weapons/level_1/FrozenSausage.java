package main_game.player.inventory.items.weapons.level_1;

import java.lang.invoke.WrongMethodTypeException;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 4-5
 * Crit: 0.55
 * Worth: 0-1
 */
public class FrozenSausage extends Weapon {
    private static final int MIN_DAMAGE = 4;
    private static final int MAX_DAMAGE = 6;
    private static final Range WORTH = new Range(0, 2);
    private static final String NAME = "Frozen Sausage";
    public FrozenSausage() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //3-5 damage
        critChance = 0.55; //high crit chance
    }
    public static FrozenSausage getPreset(){
        return new FrozenSausage();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }
}
