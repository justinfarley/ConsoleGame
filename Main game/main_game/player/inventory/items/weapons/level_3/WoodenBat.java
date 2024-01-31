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

    public WoodenBat(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //6-12 damage
        critChance = 0.2;
    }
    public static Gambler getPreset(){
        return new Gambler("Wooden Bat");
    }
    @Override
    public String toString(){
        return (Colors.GREEN + "Wooden Bat" + Colors.RESET);
    }

}
