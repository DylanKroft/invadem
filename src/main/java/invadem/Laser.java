package invadem;

import processing.core.PApplet;
import processing.core.PImage;


public class Laser extends GameObject {

    //Laser Constructor
    public Laser(int xPos, int yPos){

        this.xPos = xPos + 8;
        this.yPos = yPos-500;
        width = 5;
        height = 500;
        destroyed = false;
    }

    @Override
    //laser doesn't get destroyed upon collision. It is all powerful.
    public void destroy(GameObject g) {}

    public void draw(PApplet app, PImage laserImg, int tankX){

        app.image(laserImg, tankX + 8, this.yPos);

    }
}
