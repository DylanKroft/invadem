package invadem;

import processing.core.PApplet;
import processing.core.PImage;


public class Tank extends GameObject {

    private int lives;
    private boolean shotFired;
    private float shotsFired;
    private int lasersLeft;
    private boolean laserFired;


    //Tank Constructor
    public Tank(int x, int y){
        this.xPos = x; //starting position
        this.yPos = y; //y coordinate of tank never changes
        width = 22;
        height = 16;

        lives = 3;
        destroyed = false;

        shotFired = false; //checks if a shot has been fired already during a keypress instance
        shotsFired = 0; //tracks number of shots tank has fired for accuracy measurement

        laserFired = false;
        lasersLeft = 3;
    }

    //Tank movement
    public void move(String n){

        if (n.equals("right")) {
            if (xPos < 460) {xPos++;}
        }

        else if (n.equals("left")) {
            if (xPos > 180) {xPos--;}
        }
    }

    //fires bullet (creates new Projectile object)
   public Projectile shoot(){
       shotFired = true; //prevents tank from firing another shot until the spacebar is released and pressed again
       shotsFired++;
       Projectile p = new Projectile(xPos, yPos);
       p.setFriendly();
       return p;
    }

    //fires laser (creates new laser object)
    public Laser laser(){
        if(lasersLeft > 0) {
            shotsFired++;
            laserFired = true;
            lasersLeft--;
            return new Laser(xPos, yPos);
        }
        return null;
    }

    //returns lives left
    public int getLives(){
        return this.lives;
    }

    //TANK LIVES (displays number of lives left)
    public void drawLives(PApplet app){
        if (this.getLives() == 3) {
            app.image(this.getImg(), 60, 145);
        }
        if (this.getLives() >= 2) {
            app.image(this.getImg(), 60, 110);
        }
        app.image(this.getImg(), 60, 75);
    }

    //lasers remaining (displays number of lasers remaining)
    public void drawLasersLeft(PApplet app, PImage laserBall){
        if (this.getLasersLeft() > 2) {
            app.image(laserBall, 88, 220);
        }
        if(this.getLasersLeft() > 1){
            app.image(laserBall,63,220);
        }
        if(this.getLasersLeft() > 0){
            app.image(laserBall,38,220);
        }
    }

    @Override
    //subtracts one life each time tank is hit, up until three lives are lost
    public void destroy(GameObject g){
        if(lives >= 1){
            lives--;
        }
        if(lives == 0 || g.getHeight() == 5){
            destroyed = true;
        }
    }

    //returns true if a shot has been fired
    public boolean fired(){
        return shotFired;
    }

    //returns true if a laser has been fired
    public boolean laserFired(){
        return laserFired;
    }

    //returns number of shots fired this round
    public float getShotsFired(){
        return shotsFired;
    }

    //allows tank to shoot another bullet
    public void resetCannon(){
        shotFired = false;
    }

    //returns the number of lasers a tank is able to shoot
    public int getLasersLeft(){
        return lasersLeft;
    }

    //allows tank to shoot another laser (provided that it still has lasers)
    public void resetLaser(){
        laserFired = false;
    }
}
