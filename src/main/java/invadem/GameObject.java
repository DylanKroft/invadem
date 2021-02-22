package invadem;

import processing.core.PImage;
import processing.core.PApplet;


public abstract class GameObject {

    protected PImage img;
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;
    protected boolean destroyed;

    //Sets sprite(s) for object
    public void setImg(PImage img){
        this.img = img;
    }

    public void draw(PApplet app){
        app.image(this.getImg(), this.getXPos(), this.getYPos());
    }

    //returns the sprite for an object
    public PImage getImg(){
        return this.img;
    }

    //returns the x-coordinate of an object
    public int getXPos(){
        return this.xPos;
    }

    //returns the y-coordinate of an object
    public int getYPos(){
        return this.yPos;
    }

    //returns the height of an object
    public int getHeight(){
        return this.height;
    }

    //returns the width of an object
    public int getWidth(){
        return this.width;
    }

    //checks if a gameObject is destroyed
    public boolean isDestroyed(){
        return destroyed;
    }

    //"destroys" an object
    public void destroy(GameObject g){
        this.destroyed = true;
    }
}
