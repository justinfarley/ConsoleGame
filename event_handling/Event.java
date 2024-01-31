package event_handling;
import java.util.*;
public class Event {
    private ArrayList<Listener> listeners = new ArrayList<>();


    public Event(){
    }
    public Event(Listener defaultListener){
        listeners.add(defaultListener);
    }
    public void invokeEvent(){
        for(Listener listener : listeners){
            listener.execute();
        }
    }
    public void addListener(Listener l){
        listeners.add(l);
    }
    public void resetListeners(){
        listeners.clear();
    }

}

