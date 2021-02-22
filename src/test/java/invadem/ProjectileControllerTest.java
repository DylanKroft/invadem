package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;
import java.util.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ProjectileControllerTest{

    private Tank tank = new Tank(319,450);
    Invader invader = new Invader(300,300);
    ProjectileController pC = new ProjectileController(){};
    PImage[] emptyImgs = new PImage[4];


    public void setup(){
        emptyImgs[0] = new PImage(8,8);
        emptyImgs[1] = new PImage(8,8);
        emptyImgs[2] = new PImage(8,8);
        emptyImgs[3] = new PImage(8,8);
    }


    @Before

    @Test
    //Tests projectile wrapper construction
    public void testProjectileConstruction(){
        Projectile p = new Projectile(200,200);
        assertEquals(0,pC.list().size());
        assertNotNull(pC.list());
    }

    @Test
    //Tests that a bullet, when shot, is added to a list within the projectile wrapper
    public void testAddBullet(){
        pC.addBullet(tank.shoot());
        assertEquals(1,pC.list().size());
        pC.addBullet(tank.shoot());
        assertEquals(2,pC.list().size());
    }

    @Test
    //Tests that bullets held in the projectile wrapper can move correctly
    public void testMove(){
        pC.addBullet(tank.shoot());
        pC.addBullet(invader.shoot());
        pC.move();
        assertEquals(447, pC.list().get(0).getYPos());
        assertEquals(299, pC.list().get(1).getYPos());
        pC.move();
        assertEquals(300, pC.list().get(1).getYPos());
        assertEquals(446, pC.list().get(0).getYPos());
        pC.move();
        assertEquals(301, pC.list().get(1).getYPos());
        assertEquals(445, pC.list().get(0).getYPos());
    }

    @Test
    //Tests that bullets are removed from the projectile wrapper once they are offscreen
    public void testRemoveOffScreenBullet(){
        Tank tank2 = new Tank(300,500);
        pC.addBullet(tank2.shoot());
        pC.removeBullets();
        assertEquals(0,pC.list().size());
        Tank tank3 = new Tank(300,0);
        pC.addBullet(tank3.shoot());
        pC.removeBullets();
        assertEquals(0,pC.list().size());
    }

    @Test
    //Tests that bullets are removed once they have collided with something
    public void testRemoveDestroyedBullet(){
        Invader invader2 = new Invader(323,454);
        pC.addBullet(invader2.shoot());
        assertEquals(1,pC.list().size());
        pC.checkCollision(tank, pC.list().get(0));
        assertTrue(pC.list().get(0).destroyed);
        pC.removeBullets();
        assertEquals(0,pC.list().size());
    }

    @Test
    //Tests that the collision checking is not null
    public void testBulletCollisionCheckNotNull(){

        this.setup();

        Tank tank2 = new Tank(200,200);

        Invader invader2 = new Invader(204,204);
        Invader invader3 = new Invader(250,200);
        Invader invader4 = new Invader(290,200);
        List<Invader> invaders = new ArrayList<>();
        invaders.add(invader2);
        invaders.add(invader3);
        invaders.add(invader4);

        List<BComponent> b = new ArrayList<>();
        BComponent b1 = new BComponent(emptyImgs,200,200);
        BComponent b2 = new BComponent(emptyImgs,220,200);
        BComponent b3 = new BComponent(emptyImgs,240,200);
        b.add(b1);
        b.add(b2);
        b.add(b3);

        Barrier barrier = new Barrier();
        barrier.set(b);

        List<Barrier> barriers = new ArrayList<>();
        barriers.add(barrier);

        assertNotNull(pC.bulletCollisionCheck(barriers, invaders, tank2));
    }

    @Test
    //Tests that bullets can collide with barriers and invaders and get destroyed
    public void testTankBulletCollisionBarrierInvader(){

        this.setup();

        Tank tank2 = new Tank(200,200);

        Invader invader2 = new Invader(207,196);
        Invader invader3 = new Invader(250,200);
        Invader invader4 = new Invader(290,200);
        List<Invader> invaders = new ArrayList<>();
        invaders.add(invader2);
        invaders.add(invader3);
        invaders.add(invader4);

        List<BComponent> b = new ArrayList<>();
        BComponent b1 = new BComponent(emptyImgs,210,195);
        BComponent b2 = new BComponent(emptyImgs,220,200);
        BComponent b3 = new BComponent(emptyImgs,240,200);
        b.add(b1);
        b.add(b2);
        b.add(b3);

        Barrier barrier = new Barrier();
        barrier.set(b);

        List<Barrier> barriers = new ArrayList<>();
        barriers.add(barrier);

        pC.addBullet(tank2.shoot());
        pC.addBullet(tank2.shoot());
        pC.addBullet(tank2.shoot());
        pC.addBullet(tank2.shoot());

        assertEquals(4,pC.list().size());

        pC.bulletCollisionCheck(barriers, invaders, tank2);
        assertTrue(b1.destroyed);
        assertTrue(invader2.destroyed);

    }

    @Test
    //Tests that invaders do not get destroyed by friendly fire
    public void testInvaderBulletNoFriendlyFire(){

        this.setup();

        Tank tank2 = new Tank(200,200);

        Invader invader2 = new Invader(200,200);
        Invader invader3 = new Invader(200,200);
        Invader invader4 = new Invader(200,200);
        List<Invader> invaders = new ArrayList<>();
        invaders.add(invader2);
        invaders.add(invader3);
        invaders.add(invader4);

        List<Barrier> barriers = new ArrayList<>();

        pC.addBullet(invader2.shoot());
        pC.bulletCollisionCheck(barriers, invaders, tank2);

        assertFalse(invader3.isDestroyed());


    }

}
