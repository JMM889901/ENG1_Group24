package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.levelElements.Movable;

public class CounterTop extends Station{
    
    public CounterTop(){
        this.item = null;
    }
    
    public CounterTop(Movable item){
        this.item = item;
    }
}
