package main_game.player.inventory.items.weapons.level_3;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 5-21
 * Crit: 0.75
 * Worth: 10
 * Price: 10
 */
public class Gambler extends Weapon {
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 21;
    private static final Range WORTH = new Range(10, 11);
    private static final String NAME = "Gambler";
    

    public Gambler(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE);
        critChance = 0.75;
        price = 10;
    }
    public static Gambler getPreset(){
        return new Gambler(NAME);
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
