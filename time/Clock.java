package time;

import helpers.DialogueHelper;
import main_game.*;
import main_game.State.TimeState;

public class Clock {
    private static int time = 6;
    private static boolean isPM = false;
    private Clock(){
        //no objects
    }
    public static void advanceTime(){
        if(!isPM){
            if(time == 11){ 
                time = 12;
                isPM = true;
            }
            else{
                time++;
            }
        }
        else{
            if(time == 11){
                isPM = false;
                time = 12;
            }
            else{
                time++;
            }
        }
        if(time == 13){
            time = 1;
        }
    }
    public static void advanceTime(int hours){
        time += hours;
        if(time > 16) time -= 16;
    }
    /**
     * returns the amount of hours skipped
     */
    public static int advanceToNextTimeState(){
        if(time >= 6 && time < 12 && !isPM)
        {
            int timePassed = 12 - time;
            time = 12;
            isPM = true;
            return timePassed;
        }
        else if(time == 12 || (time >= 1 && time < 6)) 
        { 
            int timePassed = time == 12 ? 6 : 6 - time;
            time = 6;
            isPM = true;
            return timePassed;
        }
        else if(time >= 6 && time < 12 && isPM){
            int timePassed = 12 - time;
            time = 12;
            isPM = false;
            return timePassed;
        }
        else{
            int timePassed = time == 12 ? 6 : 6 - time;
            time = 6;
            isPM = false;
            return timePassed;
        }
    }
    public static void displayTime() throws InterruptedException{
        DialogueHelper.sayTextln("It is currently " + time + ":00 " + (isPM ? "PM" : "AM"), GameLoop.TEXT_SPEED, false);
    }
    public static TimeState getTimeState(){
        if(time >= 6 && time < 12 && !isPM) return TimeState.START;
        else if(time == 12 || (time >= 1 && time < 6)) return TimeState.MIDDLE;
        else if(time >= 6 && time < 12 && isPM) return TimeState.LATE;
        else return TimeState.NIGHT;
        
    }
}
