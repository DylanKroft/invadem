package invadem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import processing.core.PApplet;
import processing.core.PImage;



public class LaserTest {

    private Tank tank = new Tank(319, 450);
    private PImage testImg;
    @Before

    @Test
    //Tests laser construction
    public void testLaserConstructor(){
        Laser laser = tank.laser();
        assertNotNull(laser);
    }

    @Test
    //Tests that laser does not get destroyed upon collision
    public void testDestroyLaser() {
        Laser laser = tank.laser();
        laser.destroy(null);
        assertNotNull(laser);
    }



}
