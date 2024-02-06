package main_game.player.inventory.items.weapons.level_4;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 7-9
 * Crit: 0.99
 * Worth: 1
 * Price: 25
 */
public class SharpKey extends Weapon {
    private static final int MIN_DAMAGE = 7;
    private static final int MAX_DAMAGE = 10;
    private static final Range WORTH = new Range(1, 2);
    private static final String NAME = "Sharp Key";


    public SharpKey() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //7 damage
        critChance = 0.99;
        price = 25;
    }
    public static SharpKey getPreset(){
        return new SharpKey();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
