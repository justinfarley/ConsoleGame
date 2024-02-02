package main_game.player.inventory.items.weapons.level_3;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 6-12
 * Crit: 0.2
 * Worth: 6-8
 */
public class WoodenBat extends Weapon {
    private static final int MIN_DAMAGE = 6;
    private static final int MAX_DAMAGE = 13;
    private static final Range WORTH = new Range(6, 8);
    private static final String NAME = "Wooden Bat";


    public WoodenBat() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //6-12 damage
        critChance = 0.2;
    }
    public static WoodenBat getPreset(){
        return new WoodenBat();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
