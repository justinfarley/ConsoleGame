package main_game.player.inventory.items.weapons.level_2;

import helpers.Colors;
import helpers.Range;
import main_game.player.inventory.items.weapons.Weapon;

/**
 * Damage: 5-8
 * Crit: 0.1
 * Worth: 1
 */
public class Brick extends Weapon {
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 9;
    private static final String NAME = "Brick";
    private static final Range WORTH = new Range(1, 2);

    
    public Brick(String n) {
        super(n, WORTH);
        damageRange = new Range(MIN_DAMAGE, MAX_DAMAGE); //5-8 damage
        critChance = 0.1;
    }
    public static PlatedAxe getPreset(){
        return new PlatedAxe(NAME);
    }
    @Override
    public String toString(){
        return (Colors.GREEN + NAME + Colors.RESET);
    }

}
