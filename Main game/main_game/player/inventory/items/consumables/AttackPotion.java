package main_game.player.inventory.items.consumables;

import helpers.DialogueHelper;
import helpers.Range;
import main_game.GameLoop;

public class AttackPotion extends Consumable {
    Range rangeOfDamageIncrease = new Range(1,10);
    public AttackPotion(String n) {
        super(n);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public void use() throws InterruptedException {
        //make next attack do 
        int damageIncrease = rangeOfDamageIncrease.getNumberInRange();
        
    }
    public static AttackPotion getPreset(){
        return new AttackPotion("Attack Potion");
    }
    
}
