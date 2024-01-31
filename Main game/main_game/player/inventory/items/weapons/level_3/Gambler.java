package main_game.player.inventory.items.weapons.level_3;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 1-10
 * Crit: 0.5
 * Worth: 10
 */
public class Gambler extends Weapon {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;
    private static final Range WORTH = new Range(10, 11);

    public Gambler(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //1-10 damage
        critChance = 0.5;
    }
    public static WoodenBat getPreset(){
        return new WoodenBat("The Gambler");
    }
    @Override
    public String toString(){
        return (Colors.GREEN + "The Gambler" + Colors.RESET);
    }

}
