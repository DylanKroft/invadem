package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;
import java.util.*;

import static org.junit.Assert.*;

public class InvaderControllerTest{

    InvaderController iC = new InvaderController();

    @Before

    @Test
    //Tests the construction of the invader wrapper
    public void testInvaderControllerConstruction(){
        assertNotNull(iC);
    }

    @Test
    //Tests loading the 40 invaders for round of invadem
    public void testLoad40Invaders(){

        PImage sprite1 = new PImage(16,16);
        PImage sprite2 = new PImage(16,16);

        List<Invader> invaders = iC.loadInvaders(sprite1,sprite2,sprite1,sprite2,sprite1,sprite2);
        assertEquals(40,invaders.size());
    }

    @Test
    //Tests listing out invaders
    public void listInvaders(){

        PImage sprite1 = new PImage(16,16);
        PImage sprite2 = new PImage(16,16);

        iC.loadInvaders(sprite1,sprite2,sprite1,sprite2,sprite1,sprite2);

        assertNotNull(iC.list());
    }

    @Test
    //Tests that one of the random invaders shoots every 5 (or fewer) seconds
    public void testRandomShot(){

        PImage sprite1 = new PImage(16,16);
        PImage sprite2 = new PImage(16,16);

        iC.loadInvaders(sprite1,sprite2,sprite1,sprite2,sprite1,sprite2);

        Projectile p = iC.randomShot(0,6400);

        assertNotNull(p);
    }

    @Test
    //Tests that invader rate of fire is capped and that even though it decreases
    //Each time a player wins a round, it is capped so that even when you win more
    //than 5 rounds and the bullet speed increases (millis between shots decreases)
    //a bullet is always created (not null) and the rate of fire never goes negative
    public void testFireRateCap(){

        PImage sprite1 = new PImage(16,16);
        PImage sprite2 = new PImage(16,16);

        iC.loadInvaders(sprite1,sprite2,sprite1,sprite2,sprite1,sprite2);

        Projectile p = iC.randomShot(6,3400);

        assertNotNull(p);
    }










}
