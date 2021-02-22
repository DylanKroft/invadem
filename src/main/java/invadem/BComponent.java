package invadem;

import processing.core.PImage;

public class BComponent extends GameObject {

    PImage[] img;
    private int state;

    //Barrier Component Constructor
    public BComponent(PImage[] img, int xPos, int yPos){

        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = 0; //indicates how "destroyed" the barrier component is
        this.width = 8;
        this.height = 8;
        this.destroyed = false;
    }

    @Override
    //returns an image of the barrier component based on its state
    public PImage getImg(){
        return this.img[state];
    }

    @Override
    //increments "state". If state = 3, the barrier component is marked as destroyed
    public void destroy(GameObject g) {

        if(state < 3){
            state++;
        }

        if(state == 3 || g.getHeight() == 5 || g.getHeight() == 500){
            this.destroyed = true;
        }
    }
}
