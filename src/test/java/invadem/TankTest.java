package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.*;

public class TankTest {

    private Tank tank = new Tank(319,450);
    @Before

    @Test
    //Tests tank construction
    public void testTankConstruction() {
        assertNotNull(tank);
    }

    @Test
    //Tests that tanks can shoot
    public void testTankProjectile() {
        assertNotNull(tank.shoot());
    }

    @Test
    //Test that the tank is not destroyed when instantiated
    public void testTankIsNotDestroyed() {
        assertEquals(false, tank.isDestroyed());
    }

    @Test
    //Tests that the tank can move right
    public void testMoveRight(){
        tank.move("right");
        assertEquals(320, tank.getXPos());
    }

    @Test
    //Tests that the tank can move left
    public void testMoveLeft(){
        tank.move("left");
        assertEquals(318, tank.getXPos());
    }

    @Test
    //Tests that the tank cannot move further left than 180px from the boundary of the window (as per specs)
    public void testLeftBoundary(){
        Tank tank3 = new Tank(180,450);
        tank3.move("left");
        assertEquals(180,tank3.getXPos());
    }

    @Test
    //Tests that the tank cannot move further right than 180px from the boundary of the window (as per specs)
    public void testRightBoundary(){
        Tank tank2 = new Tank(460,450);
        tank2.move("right");
        assertEquals(460,tank2.getXPos());
    }

    @Test
    //Tests that the tank doesn't move when not given the argument "left" or "right"
    public void testNoMove(){
        tank.move("garbage");
        assertEquals(319, tank.getXPos());
    }

    @Test
    //Tests that the tank is in a "fired" state when it has just fired something
    public void testShotFired(){
        tank.shoot();
        assertTrue(tank.fired());
    }

    @Test
    //Tests the tank's cannon can be reset to fire again
    public void testResetCannon(){
        tank.shoot();
        tank.resetCannon();
        assertFalse(tank.fired());
    }

    @Test
    //Tests that every shot fires one bullet
    public void testShotsFired(){
        tank.shoot();
        assertEquals(1,tank.getShotsFired(),0.0f);
    }

    @Test
    //Tests that tank can shoot a laser
    public void testTankLaser(){
        assertNotNull(tank.laser());
    }

    @Test
    //Tests that the tank has fewer lasers left every time a laser is fired
    public void testTankLasersLeft(){
        assertEquals(3,tank.getLasersLeft(),0.0f);
        tank.laser();
        assertEquals(2,tank.getLasersLeft(),0.0f);

    }

    @Test
    //Tests that tank only has three lasers to use per round
    public void testTankNoLasers(){
        tank.laser();
        tank.laser();
        tank.laser();
        assertNull(tank.laser());
    }

    @Test
    //Tests that resetting the laser allows the tank to fire another laser (if it has lasers left)
    public void testResetLaser(){
        tank.laser();
        assertTrue(tank.laserFired());
        tank.resetLaser();
        assertFalse(tank.laserFired());
    }

    @Test
    //Tests that each collision with a regular bullet "destroys" the tank a bit, but not fully
    public void testDestroyTank(){
        Projectile p = new Projectile(319,450);
        assertEquals(3,tank.getLives());
        tank.destroy(p);
        assertEquals(2,tank.getLives());

    }

    @Test
    //Tests that each collision with a power projectile fully destroys the tank
    public void testDestroyTankPowerP(){
        PowerProjectile pp = new PowerProjectile(319,450);
        assertEquals(3,tank.getLives());
        tank.destroy(pp);
        assertTrue(tank.destroyed);
    }

    @Test
    //Tests that the number of lives left for the tank is reduced by once every time it is shot by a regular bullet.
    //This is used for checking how mnay lives should be printed out on the screen.
    public void testGetLives(){
        Projectile p = new Projectile(319,450);
        assertEquals(3,tank.getLives());
        tank.destroy(p);
        assertEquals(2,tank.getLives());
        tank.destroy(p);
        assertEquals(1,tank.getLives());
        tank.destroy(p);
        assertTrue(tank.isDestroyed());
        tank.destroy(p);
        assertEquals(0,tank.getLives());
        tank.destroy(p);
        assertEquals(0,tank.getLives());
    }



}
