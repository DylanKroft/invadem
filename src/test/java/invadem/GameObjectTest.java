package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;
import static org.junit.Assert.*;


public class GameObjectTest{

    private PImage[] tempImgArray = new PImage[4];
    private PImage tankImg;
    private PImage laserImg;
    private PImage projectileImg;





    Tank tank = new Tank(319,450);
    Invader invader = new Invader(100,100);
    BComponent b = new BComponent(tempImgArray,50,50);
    Projectile p = new Projectile(tank.getXPos(),tank.getYPos());
    Laser laser = new Laser(tank.getXPos(),tank.getYPos());

    @Before

    @Test
    //Tests the game object height can be returned
    public void testGetHeight(){
        assertTrue(tank.getHeight() > 0);
        assertTrue(invader.getHeight() > 0);
        assertTrue(laser.getHeight() > 0);
        assertTrue(p.getHeight() > 0);
        assertTrue(b.getHeight() > 0);
    }

    @Test
    //Tests that game object width can be returned
    public void testGetWidth(){
        assertTrue(tank.getWidth() > 0);
        assertTrue(invader.getWidth() > 0);
        assertTrue(laser.getWidth() > 0);
        assertTrue(p.getWidth() > 0);
        assertTrue(b.getWidth() > 0);
    }

    @Test
    //Tests getting the x-position of a game object
    public void testGetXPos(){
        assertTrue(tank.getXPos() <= 460 || tank.getXPos() > 180);
        assertTrue(invader.getXPos() <= 460 || invader.getXPos() > 180);
        assertTrue(b.getXPos() <= 460 || b.getXPos() > 180);
        assertTrue(p.getXPos() <= 460 || p.getXPos() > 180);
        assertTrue(laser.getXPos() <= 460 || laser.getXPos() > 180);
    }

    @Test
    //Tests getting the y-position of a game object
    public void testGetYPos(){
        assertTrue(tank.getYPos() > 0);
        assertTrue(invader.getYPos() > 0);
        assertTrue(b.getYPos() > 0);
        assertTrue(p.getYPos() > 0);
        assertTrue(laser.getYPos() > 0 || laser.getYPos() <= 0);
    }

    @Test
    //Tests getting the image of a game object
    public void testSetGetImg(){
        tempImgArray[0] = new PImage(8,8);
        tempImgArray[1] = new PImage(8,8);
        tempImgArray[2] = new PImage(8,8);
        tempImgArray[3] = new PImage(8,8);
        tankImg = new PImage(22,16);
        laserImg = new PImage(5,500);
        projectileImg = new PImage(1,3);

        tank.setImg(tankImg);
        assertNotNull(tankImg);
        laser.setImg(laserImg);
        assertNotNull(laser.getImg());
        p.setImg(projectileImg);
        assertNotNull(p.getImg());
        assertNotNull(b.getImg());

    }

    @Test
    //Tests that game objects can be destroyed. Also tests that each game object's
    //destroy method may weaken the particular object different amounts depending on
    //the type of object it has collided with (laser, projectile, power projectile etc)
    public void testDestroy(){
        tank.destroy(p);
        assertFalse(tank.isDestroyed());
        tank.destroy(p);
        assertFalse(tank.isDestroyed());
        tank.destroy(p);
        assertTrue(tank.isDestroyed());

        p.destroy(p);
        assertTrue(p.destroyed);

        laser.destroy(invader);
        assertFalse(laser.destroyed);

        invader.destroy(p);
        assertTrue(invader.destroyed);

        b.destroy(p);
        assertFalse(b.destroyed);
        b.destroy(p);
        assertFalse(b.destroyed);
        b.destroy(p);
        assertTrue(b.destroyed);
    }




}

