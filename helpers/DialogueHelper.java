package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DialogueHelper {

    public static void waitForMillis(long timeInMillis) throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(timeInMillis);
    }
    public static void waitForSeconds(long timeInSeconds) throws InterruptedException{
        TimeUnit.SECONDS.sleep(timeInSeconds);
    }
    public static void sayTextln(String text, long delayInMillis, boolean clear) throws InterruptedException{
        for(int i = 0; i < text.length(); i++){
            String color = matchesColor(text, i);
            if(color != null){
                System.out.print(color);
                i += color.length();
            }
            System.out.print(text.charAt(i));
            waitForMillis(delayInMillis);
        }
        if(clear) 
        { 
            removeLastLine(text);
        }
        else{
            System.out.println();
        }
    }
    private static String matchesColor(String text, int index){
        for(String color : Colors.ALL_COLORS){
            try{
            if(text.substring(index, index + color.length()).equalsIgnoreCase(color)){
                return color;
            }
            }
            catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        return null;
    }
    public static void sayText(String text, long delayInMillis, boolean clear) throws InterruptedException{
        for(int i = 0; i < text.length(); i++){
            String color = matchesColor(text, i);
            if(color != null){
                System.out.print(color);
                i += color.length();
            }
            System.out.print(text.charAt(i));
            waitForMillis(delayInMillis);
        }
        if(clear) 
        { 
            removeLastLine(text);
        }
    }
    private static void removeLastLine(String text){
        System.out.print("\033[2K"); //clear
        for(int i = 0; i < text.length(); i++){ //moves cursor back
            System.out.print("\b"); 
        }
    }
    public static <T extends INameable> T getClosestAction(List<T> options, String input)
    {
        input = input.toLowerCase();
        for(T option : options){
            if(option.getName().toLowerCase().startsWith(input) || option.getName().toLowerCase().equals(input)){
                return option;
            }
        }
        if(input.length() <= 2) return null;
        for(T option : options){
            if(option.getName().toLowerCase().contains(input) || option.getName().toLowerCase().equals(input)){
                return option;
            }
        }
        return null;
    }
    public static String getClosestString(String[] options, String input)
    {
        input = input.toLowerCase();
        for(String option : options){
            if(option.toLowerCase().startsWith(input) || option.toLowerCase().equals(input)){
                return option;
            }
        }
        if(input.length() <= 2) return null;
        for(String option : options){
            if(option.toLowerCase().contains(input) || option.toLowerCase().equals(input)){
                return option;
            }
        }
        return null;
    }
}
