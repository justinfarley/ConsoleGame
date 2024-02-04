package main_game.player.inventory.items.weapons.level_4;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 10-15
 * Crit: 0.2
 * Worth: 5
 */
public class Club extends Weapon {
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 16;
    private static final Range WORTH = new Range(5, 6);
    private static final String NAME = "Club";


    public Club() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE);
        critChance = 0.2;
    }
    public static Club getPreset(){
        return new Club();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
