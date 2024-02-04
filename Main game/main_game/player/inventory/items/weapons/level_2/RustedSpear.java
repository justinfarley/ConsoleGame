package main_game.player.inventory.items.weapons.level_2;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 5-8
 * Crit: 0.4
 * Worth: 7-10
 */
public class RustedSpear extends Weapon {
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 7;
    private static final String NAME = "Rusted Spear";
    private static final Range WORTH = new Range(7, 11);

    public RustedSpear() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //5-8 damage
        critChance = 0.4;
    }
    public static RustedSpear getPreset(){
        return new RustedSpear();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
