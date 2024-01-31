package main_game.player.inventory.items.weapons.level_0;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 1-3
 * Crit: 0.1
 * Worth: 2-4
 */
public class WoodenSword extends Weapon {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 4;
    private static final Range WORTH = new Range(2, 5);
    public WoodenSword(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //1-3 damage
        critChance = 0.1;
    }
    public static WoodenSword getPreset(){
        return new WoodenSword("Wooden Sword");
    }
    @Override
    public String toString(){
        return (Colors.GREEN + "Wooden Sword" + Colors.RESET);
    }

}
