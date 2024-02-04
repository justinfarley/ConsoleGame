package main_game.player.inventory.items.weapons.level_5;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 15-25
 * Crit: 0.2
 * Worth: 12 - 15
 */
public class SharkGun extends Weapon {
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 26;
    private static final Range WORTH = new Range(12, 16);
    private static final String NAME = "Shark Gun";


    public SharkGun() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //15-25 damage
        critChance = 0.2;
    }
    public static SharkGun getPreset(){
        return new SharkGun();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
