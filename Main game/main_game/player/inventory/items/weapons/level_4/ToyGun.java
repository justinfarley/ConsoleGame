package main_game.player.inventory.items.weapons.level_4;

import java.util.ArrayList;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 8-16
 * Crit: 0.15
 * Worth: 6 - 10
 */
public class ToyGun extends Weapon {
    private static final int MIN_DAMAGE = 8;
    private static final int MAX_DAMAGE = 16;
    private static final Range WORTH = new Range(6, 11);
    private static final String NAME = "Toy Gun";


    public ToyGun() {
        super(NAME, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //15-25 damage
        critChance = 0.15;
    }
    public static ToyGun getPreset(){
        return new ToyGun();
    }
    @Override
    public String toString(){
        return Colors.GREEN + NAME + Colors.RESET;
    }

}
