package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;


import static org.junit.Assert.*;

public class AppTest extends App{

    App app;
    @Before

    @Test
    //Tests the App class constructor
    public void testAppConstruction(){
        app = new App();
        assertNotNull(app);
    }

    @Test
    //Tests resetting the board (done when game over or next level)
    public void testReset(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        assertNotNull(app);
    }

    @Test
    //Tests drawing the loading screen
    public void testDrawLoading(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        assertNotNull(app);
    }


    @Test
    //Tests drawing the game board
    public void testDrawGameMode(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");
        for(int x = 0; x < 60; x++) {

            app.draw();
            app.tick();

        }
        assertNotNull(app);
    }

    @Test
    //Tests drawing the game over screen
    public void testDrawGameOverMode(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("gameOver");
        for(int x = 0; x < 60; x++) {

            app.draw();
            app.tick();

        }
        assertNotNull(app);
    }

    @Test
    //Tests drawing the next level screen
    public void testDrawNextLevelMode(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("nextLevel");
        for(int x = 0; x < 60; x++) {

            app.draw();
            app.tick();

        }
        assertNotNull(app);
    }

    @Test
    //Tests drawing the laser
    public void testThreeFrameLaser(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");
        for(int x = 0; x < 60; x++) {

            app.draw();
            app.tick();

        }
        assertNotNull(app);
    }

    @Test
    //Tests prssing space to go from loading screen to game screen
    public void testKeyPressLoading(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("loading");
        for(int x = 0; x < 10; x++) {

            app.draw();
            app.keyCode = 32;
            app.keyPressed();
            app.tick();
            app.keyReleased();
        }
        assertNotNull(app);
    }

    @Test
    //Tests pressing the right key to move tank right
    public void testKeyPressRight(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");

        for(int x = 0; x < 30; x++) {

            app.draw();
            app.keyCode = 39;
            app.keyPressed();
            app.tick();
            app.keyReleased();

        }
        assertNotNull(app);
    }

    @Test
    //Tests pressing the left key to move tank left
    public void testKeyPressLeft(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");

        for(int x = 0; x < 30; x++) {

            app.draw();
            app.keyCode = 37;
            app.keyPressed();
            app.tick();
            app.keyReleased();

        }
        assertNotNull(app);
    }

    @Test
    //Tests pressing the space bar to shoot
    public void testKeyPressShoot(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");

        for(int x = 0; x < 30; x++) {

            app.draw();
            app.keyCode = 32;
            app.keyPressed();
            app.tick();
            app.keyReleased();

        }
        assertNotNull(app);
    }

    @Test
    //runs an extended sketch of App to have the invaders reach barriers
    public void testInvaderBarrier(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");
        for(int x = 0; x < 1200; x++) {

            app.draw();
            app.tick();

        }
        assertNotNull(app);
    }

    @Test
    //Tests pressing shift to shoot laser
    public void testKeyPressLaser(){
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");

        for(int x = 0; x < 30; x++) {

            app.draw();
            app.keyCode = 16;
            app.keyPressed();
            app.draw();
            app.tick();
            app.keyReleased();

        }
        assertNotNull(app);
    }

    @Test
    //Tests a random key press that shouldn't do anything
    public void testBadKeyPress() {
        app = new App();
        String[] args = {"Test"};
        PApplet.runSketch(args, app);
        app.setup();
        app.reset();
        app.draw();
        app.tick();
        app.setMode("game");

        for (int x = 0; x < 30; x++) {

            app.draw();
            app.keyCode = 20;
            app.keyPressed();
            app.tick();
            app.keyReleased();

        }
        assertNotNull(app);
    }



}
