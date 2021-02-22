package invadem;

import processing.core.PApplet;
import processing.core.PImage;


import java.util.*;

public class ProjectileController {

    private LinkedList<Projectile> bullets;
    private List<Projectile> bulletsToRemove;


    public ProjectileController(){

        bullets = new LinkedList<>();
        bulletsToRemove = new ArrayList<>();
    }

    //adds a tank bullet to list of tank bullets moving upwards
    public void addBullet(Projectile bullet){
        bullets.add(bullet);
    }

    //returns list of tank bullets
    public List<Projectile> list(){
        return bullets;
    }

    //draw out projectiles
    public void draw(PApplet app, PImage bulletImg, PImage powerBulletImg){
        for(Projectile bullet: this.bullets){

            if(bullet.getHeight() == 5){
                app.image(powerBulletImg,bullet.getXPos(),bullet.getYPos());

            }
            else {
                app.image(bulletImg, bullet.getXPos(), bullet.getYPos());
            }
        }
    }

    //moves bullets in up if they're friendly, and down if they're shot by invaders
    public void move(){
        for(Projectile bullet: this.bullets){
            if(bullet.isFriendly()){
                bullet.moveUp();
            }
            else{
                bullet.moveDown();
            }
        }
    }

    //removes bullets that have hit something or are off screen
    public void removeBullets(){
        for(Projectile bullet: this.bullets){
            if(bullet.destroyed || bullet.getYPos() > 490 || bullet.getYPos() < 0){
                this.bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);
    }

    //checks for collision between bullets and invaders/barriers/tank
    public List<Invader> bulletCollisionCheck(List<Barrier> barriers, List<Invader> invaders, Tank tank){
        List<Invader> invadersToRemove = new ArrayList<>();

        for(Projectile bullet: this.bullets){

            this.checkCollision(bullet, tank);

            if(bullet.isFriendly()){
                for(Invader invader: invaders){
                    this.checkCollision(bullet, invader);

                    if(invader.isDestroyed()){
                        invadersToRemove.add(invader);
                    }
                }
            }

            for(Barrier barrier: barriers){
                for(BComponent b: barrier.list()){
                    this.checkCollision(bullet, b);
                }
            }
        }
        return invadersToRemove;
    }

    //checks for collision between lasers and invaders/barriers
    public List<Invader> laserCollisionCheck(List<Barrier> barriers, List<Invader> invaders, Laser laser, Boolean laserFired){
        List<Invader> invadersToRemove = new ArrayList<>();

        if(laserFired) {
            for (Invader invader : invaders) {
                this.checkCollision(laser, invader);

                if (invader.isDestroyed()) {
                    invadersToRemove.add(invader);
                }
            }

            //laser destroys each barrier component in 1 hit (cycles through all three phases of barrier components)
                for (Barrier barrier : barriers) {
                    for (BComponent component : barrier.list()) {
                        this.checkCollision(laser, component);
                        }
                    }
                }
        return invadersToRemove;
    }



    //AABB Collision detection
    public void checkCollision(GameObject n1, GameObject n2){

        if(n1.getXPos() < n2.getXPos() + n2.getWidth() &&
                n1.getXPos() + n1.getWidth() > n2.getXPos() &&
                n1.getYPos() < n2.getYPos() + n2.getHeight() &&
                n1.getYPos() + n1.getHeight() > n2.getYPos())
        {
            //both GameObjects are "destroyed" upon collision
            n1.destroy(n1);
            n2.destroy(n1);
        }
    }
}
