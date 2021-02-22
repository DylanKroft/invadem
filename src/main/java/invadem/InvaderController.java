package invadem;

import java.util.*;

import processing.core.PImage;
import processing.core.PApplet;


public class InvaderController {

    private List<Invader> invaders;
    private int speedUp;
    private long time;

    public InvaderController(){

        invaders = new ArrayList<>();

    }

    public List<Invader> list(){
        return this.invaders;
    }

    public void draw(PApplet app){

        for(Invader invader: this.invaders){
            app.image(invader.getImg(),invader.getXPos(),invader.getYPos());
        }
    }

    //draws out explosion when invader hit
    public void drawExplosion(PApplet app, PImage boom, List<Invader> invadersToRemove){

        for(Invader invader: invadersToRemove){
            app.image(boom,invader.getXPos(),invader.getYPos());
        }
    }

    //sets up 40 invaders for a new game
    public List<Invader> loadInvaders(PImage r1, PImage r2, PImage p1, PImage p2, PImage a1, PImage a2){

        int xPos = 170;
        int yPos = 60;
        int counter = 0;

        for(int i = 0; i < 40; i++) {

            if(i < 10){
                Invader invader = new ArmorInvader(xPos, yPos);
                invader.setSprite(a1, a2);
                invaders.add(invader);

            }

            else if(i >= 10 && i < 20){
                Invader invader = new PowerInvader(xPos, yPos);
                invader.setSprite(p1, p2);
                invaders.add(invader);

            }
            else {
                Invader invader = new Invader(xPos, yPos);
                invader.setSprite(r1, r2);
                invaders.add(invader);

            }

            counter++;
            xPos += 30;

            if(counter == 10){
                yPos += 30;
                xPos = 170;
                counter = 0;
            }
        }
        return invaders;
    }


    //allows one of the onscreen invaders to shoot a bullet
    public Projectile randomShot(int wins, long millis){

        Projectile p = null;

        //projectile rate of fire multiplier increments every time player wins a round
        if(wins < 4) {
            speedUp = wins;
        }
        else{
            speedUp = 4;
        }

        //projectile rate of fire increases by 500ms every time player wins. Increments up till RoF = 1bullet/500ms
        if(millis > 5000 + time - (speedUp*1000)){
            time = millis;

            //randomly selects invader that fires from existing invaders
            Random ran = new Random();
            int r = ran.nextInt(invaders.size());

            for(Invader invader: invaders){

                if(invaders.indexOf(invader) == r) {
                   p =  invader.shoot();
                }
            }
        }
        return p;
    }


}
