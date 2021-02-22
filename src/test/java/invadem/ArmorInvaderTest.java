package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class ArmorInvaderTest {

    private PImage sprite1;
    private PImage sprite2;


    ArmorInvader aInvader = new ArmorInvader(200,100);

    @Before

    @Test
    //Tests armor invader construction
    public void testInvaderConstruction(){
        assertNotNull(aInvader);
    }

    @Test
    //Tests that armor invaders can withstand three hits from tank
    public void testInvaderDestroy(){

        Projectile p = new Projectile(200,100);
        aInvader.destroy(p);
        assertFalse(aInvader.destroyed);
        aInvader.destroy(p);
        assertFalse(aInvader.destroyed);
        aInvader.destroy(p);
        assertTrue(aInvader.destroyed);
        aInvader.destroy(p);
        assertTrue(aInvader.destroyed);
    }

    @Test
    //Tests that armored invaders straight away when hit by laser
    public void testInvaderLaserDestroy(){
        Laser l = new Laser(200, 200);
        assertFalse(aInvader.destroyed);
        aInvader.destroy(l);
        assertTrue(aInvader.destroyed);
    }





}
