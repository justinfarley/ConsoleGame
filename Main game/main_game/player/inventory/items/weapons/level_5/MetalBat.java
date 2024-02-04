package main_game.player.inventory.items.weapons.level_5;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 15-25
 * Crit: 0.4
 * Worth: 10 - 15
 */
public class MetalBat extends Weapon {
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 26;
    private static final Range WORTH = new Range(10, 16);
    private static final String NAME = "Metal Bat";


    public MetalBat() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //15-25 damage
        critChance = 0.4;
    }
    public static MetalBat getPreset(){
        return new MetalBat();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
