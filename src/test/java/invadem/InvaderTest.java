package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;


public class InvaderTest {

    private PImage sprite1;
    private PImage sprite2;


    Invader invader = new Invader(200,100);

    @Before

    @Test
    //Tests invader construction
    public void testInvaderConstruction(){
        assertNotNull(invader);
    }

    @Test
    //Tests that invader sprite can be changed
    public void testSetSprite(){

        sprite1 = new PImage(16,16);
        sprite2 = new PImage(16,16);

        Invader invader = new Invader(200,200);
        invader.setSprite(sprite1,sprite2);
        assertTrue(invader.getImg() == sprite1);
        invader.changeSprite();
        assertTrue(invader.getImg() == sprite2);

    }

    @Test
    //Tests that changing invader sprite changes the image associated with the current
    //state of the invader
    public void testChangeSpriteGetImg(){
        assertTrue(invader.getImg() == sprite1);
        invader.changeSprite();
        assertTrue(invader.getImg() == sprite2);
        invader.changeSprite();
        assertTrue(invader.getImg() == sprite1);
        invader.changeSprite();
        invader.changeSprite();
        invader.changeSprite();
        invader.changeSprite();
        assertTrue(invader.getImg() == sprite1);

    }

    @Test
    //Tests that an invader can move right by 1 pixel
    public void testMoveRight1Px(){
        invader.move();
        invader.move();
        assertEquals(201,invader.getXPos());
    }

    @Test
    //Tests that an invader can move right for 1 second
    public void testMoveRight1second(){

        for(int i = 0; i < 61; i++) {
            invader.move();
        }

        assertEquals(230,invader.getXPos());
    }

    @Test
    //Tests that an invader can move left by 1 pixel
    public void testMoveLeft1Px(){

        for(int i = 0; i < 77; i++) {
            invader.move();
        }

        assertEquals(229,invader.getXPos());
    }

    @Test
    //Tests that an invader can move left for 1 second
    public void testMoveLeft1second(){

        for(int i = 0; i <137; i++) {
            invader.move();
        }
        assertEquals(200,invader.getXPos());
    }

    @Test
    //Tests that an invader can move down by 8 pixels at 30fps as per the spec sheet
    public void testMoveDown(){
        for(int i = 0; i < 8; i++){
            invader.moveDown();
        }
        assertEquals(108,invader.getYPos());

    }

    @Test
    //Tests that the invader block moves a maximum of 30 pixels in one direction and not more
    public void testLeftRightMoveBounds(){
        for(int i = 0; i < 1200; i++){
            invader.move();
        }
        assertTrue(invader.getXPos() <= 230 && invader.getXPos() >= 200);

    }

    @Test
    //Tests that invaders can shoot
    public void testInvaderShoot(){
        assertNotNull(invader.shoot());
    }




}
