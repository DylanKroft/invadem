package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;

import static org.junit.Assert.*;

public class BComponentTest{

    private PImage[] tempImgArray = new PImage[4];

    @Before

    @Test
    //Tests barrier component not null
    public void testBComponentConstruction(){

        BComponent b = new BComponent(tempImgArray, 326,410);
        assertNotNull(b);

    }

    @Test
    //Tests getting image for each barrier component
    public void testGetImg(){
        BComponent b = new BComponent(tempImgArray, 326,410);
        PImage img = b.getImg();
        assertTrue(img == tempImgArray[0]);

    }

    @Test
    //Tests that barrier components weaken each time they are hit by regular projectiles
    public void testDestroy(){
        Projectile p = new Projectile(326,410);
        BComponent b = new BComponent(tempImgArray, 326,410);
        assertTrue(b.getImg() == tempImgArray[0]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[1]);
        b.destroy(p);

    }

    @Test
    //Tests barrier component state change when hit multiple tiles
    public void testMultipleHits(){
        Projectile p = new Projectile(326,410);
        BComponent b = new BComponent(tempImgArray, 326,410);
        assertTrue(b.getImg() == tempImgArray[0]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[1]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[2]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[3]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[3]);

    }

    @Test
    //Tests that barrier component is fully destroyed when hit three times
    public void testFullyDestroyed(){
        Projectile p = new Projectile(326,410);
        BComponent b = new BComponent(tempImgArray, 326,410);
        assertTrue(b.getImg() == tempImgArray[0]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[1]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[2]);
        b.destroy(p);
        assertTrue(b.getImg() == tempImgArray[3]);
        assertTrue(b.destroyed);
    }

    @Test
    //Tests that barrier is fully destroyed when hit by laser
    public void testLaserDestroy(){
        Laser l = new Laser(326,510);
        BComponent b = new BComponent(tempImgArray, 326,410);
        b.destroy(l);
        assertTrue(b.destroyed);
    }

    @Test
    //Tests that barrier is fully destroyed when hit by power projectile
    public void testPowerBulletDestroy(){
        PowerProjectile p = new PowerProjectile(326,407);
        BComponent b = new BComponent(tempImgArray, 326,410);
        b.destroy(p);
        assertTrue(b.destroyed);
    }





}
