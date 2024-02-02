package helpers;

import java.util.Random;

public class Range {
    private int min;
    private int max;

    public Range(int min, int max){
        this.min = min;
        this.max = max;
    }

    public int getNumberInRange(){
        Random rand = new Random();

        return rand.nextInt(min, max);
    } 
    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
    }
}
