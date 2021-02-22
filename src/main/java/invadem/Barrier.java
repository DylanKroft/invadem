package invadem;

import processing.core.PApplet;

import java.util.*;

public class Barrier extends GameObject{

    private List<BComponent> barrierComponents;

    //associates each barrier object with 7 barrier components
    public void set(List<BComponent> barrierParts){
        this.barrierComponents = barrierParts;
    }

    //returns list of barrier components that make up a barrier
    public List<BComponent> list(){
        return barrierComponents;
    }

    public void drawBarrier(PApplet app){

        for(BComponent part: barrierComponents){
            app.image(part.getImg(), part.getXPos(), part.getYPos());
        }

    }

}


