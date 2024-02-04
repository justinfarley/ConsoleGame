package helpers;

import enemies.*;
import world_generation.*;

public class Tiles {
    public static Tile getTileByName(String name, int x, int y){
        //if(name == null) return null;
        if(name.equalsIgnoreCase(Edge.NAME)){
            Edge e = new Edge();
            e.setCoords(x, y);
            return e;
        }
        else if(name.equalsIgnoreCase(Grass.NAME)){
            Grass g = new Grass();
            g.setCoords(x, y);
            return g;
        }
        else if(name.equalsIgnoreCase(Tree.NAME)){
            Tree t = new Tree();
            t.setCoords(x, y);
            return t;
        }
        else if(name.equalsIgnoreCase(ItemPedestal.NAME)){
            ItemPedestal i = new ItemPedestal();
            i.setCoords(x, y);
            return i;
        }
        else if(name.equalsIgnoreCase("enemy")){
            Enemy e = Enemies.getRandomEnemy();
            e.setCoords(x, y);
            e.setTileUnder(new Grass());
            return e;
        }
        else{
            System.out.println(name + "DOESNT EXIST");
            return null;
        }
    }
}
