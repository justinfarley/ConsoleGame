package helpers;

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
    public static void sayTextInColor(String text, String color, long delayInMillis, boolean clear) throws InterruptedException{
        sayText(color, delayInMillis, clear);
        for(int i = 0; i < text.length(); i++){
            System.out.print(text.charAt(i));
            waitForMillis(delayInMillis);
        }
        if(clear) 
        { 
            removeLastLine(text);
        }
        sayText(Colors.RESET, delayInMillis, clear);
    }
    public static void sayTextInColorln(String text, String color, long delayInMillis, boolean clear) throws InterruptedException{
        sayText(color, delayInMillis, clear);
        for(int i = 0; i < text.length(); i++){
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
        sayTextln(Colors.RESET, delayInMillis, clear);
    }
    public static void sayText(String text, long delayInMillis, boolean clear) throws InterruptedException{
        for(int i = 0; i < text.length(); i++){
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
}
