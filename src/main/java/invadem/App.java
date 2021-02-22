package invadem;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.util.*;

public class App extends PApplet {

    //GAME VARIABLES --------------------------------------------------------------------------------------------------

    //GENERAL GAME SETTINGS
    private boolean start = false;
    private String mode;
    private int wins = 0;
    private long time = 0;
    private int highScore = 10000;
    private int score = 0;

    private PFont gameFont;

    private Tank tank;
    private boolean[] keys = new boolean[3];

    private InvaderController invadersC;
    private List<Invader> invaders = new ArrayList<>();
    private List<Invader> invadersToRemove = new ArrayList<>();


    //IMAGES
    private PImage bulletImg;
    private PImage powerBulletImg;
    private PImage gameOverImg;
    private PImage nextLevelImg;
    private PImage laserImg;
    private PImage laserBall;
    private PImage explosion;

    private PImage invader1;
    private PImage invader2;
    private PImage powerInvader1;
    private PImage powerInvader2;
    private PImage armorInvader1;
    private PImage armorInvader2;

    //SHOT SETTINGS
    private ProjectileController bullets;
    private Laser laser;
    private int laserFrameCount;

    //BARRIER SETTINGS
    private Barrier middleBarrier;
    private Barrier leftBarrier;
    private Barrier rightBarrier;
    private List<Barrier> barriers;

    //GAME OBJECT SETUP -----------------------------------------------------------------------------------------------

    public App() {

        tank = new Tank(319,450);
        bullets = new ProjectileController();
        invadersC = new InvaderController();
        barriers = new ArrayList<>();
        middleBarrier = new Barrier();
        leftBarrier = new Barrier();
        rightBarrier = new Barrier();
        laserFrameCount = 0;
    }


    //GENERAL SETUP ---------------------------------------------------------------------------------------------------

    public void setup() {

        frameRate(60);
        if (millis() < 2000){
            this.setMode("loading");
        }
        else{
            this.setMode("game");
        }
        gameOverImg = loadImage("gameover.png");
        nextLevelImg = loadImage("nextlevel.png");
        keys[0] = false; //keycheck for moving left
        keys[1] = false; //keycheck for moving right

        //image from: https://8bitweapon.com/8-bit-weapon-bits-with-byte-background-png/
        background(loadImage("Space.png"));
        gameFont = createFont("PressStart2P-Regular.ttf", 10);

        tank.setImg(loadImage("tank1.png"));
        explosion = loadImage("explode1.png");

        invader1 = loadImage("invader1.png");
        invader2 = loadImage("invader2.png");
        powerInvader1 = loadImage("invader1_power.png");
        powerInvader2 = loadImage("invader2_power.png");
        armorInvader1 = loadImage("invader1_armoured.png");
        armorInvader2 = loadImage("invader2_armoured.png");

        invaders = invadersC.loadInvaders(invader1, invader2, powerInvader1, powerInvader2, armorInvader1, armorInvader2);

        //BARRIER SETUP
        this.loadBarrier(middleBarrier, 326, 410);
        this.loadBarrier(leftBarrier, 220, 410);
        this.loadBarrier(rightBarrier, 440, 410);
        barriers.add(middleBarrier);
        barriers.add(leftBarrier);
        barriers.add(rightBarrier);

        //PROJECTILES/LASER SETUP
        bulletImg = loadImage("projectile.png");
        laserImg = loadImage("laser.png");
        powerBulletImg = loadImage("projectile_lg.png");
        laserBall = loadImage("laserBall.png");
    }

    public void settings() {
        size(640, 480);
    }

    //Main Game Loop --------------------------------------------------------------------------------------------------

    public void draw() {

        //LOADING SCREEN **********************************************************************************************
        if(mode.equals("loading")){
            //image from https://www.pinterest.com.au/pin/291819250840266640/
            background(loadImage("loading.png"));
        }

        //GAME MODE SCREEN ********************************************************************************************
        else if (mode.equals("game")) {
            background(loadImage("Space.png"));
            textFont(gameFont);
            textAlign(CENTER);

            //Calls all animations & movement
            this.tick();

            //draws tank lives and lasers left
            tank.drawLives(this);
            tank.drawLasersLeft(this, laserBall);

            //draws out lives and lasers left, and accuracy tracker
            this.accuracyTracker();

            //draw tank
            tank.draw(this);

            //draw barriers
            middleBarrier.drawBarrier(this);
            leftBarrier.drawBarrier(this);
            rightBarrier.drawBarrier(this);

            //draw projectiles
            bullets.draw(this, bulletImg, powerBulletImg);

            //draw invaders
            invadersC.draw(this);

            //draws out explosion when invader is hit
            invadersC.drawExplosion(this,explosion,invadersToRemove);

            //draw LASER SHOT (special weapon that lasts three frames)
            if(tank.laserFired() || laserFrameCount > 0){
                laserFrameCount++;
                laser.draw(this, laserImg, tank.getXPos());
                keyReleased();

                if(laserFrameCount == 3){
                    laserFrameCount = 0;
                }
            }

        }

        //GAME OVER SCREEN ********************************************************************************************
        else if (mode.equals("gameOver")) {
            background(178, 34, 34);
            image(gameOverImg, 260, 240);

            //2 second delay
            if (frameCount == 120) {
                this.reset();
                this.setMode("game");
            }
        }

        //NEXT LEVEL SCREEN *******************************************************************************************
        else if (mode.equals("nextLevel")) {
            background(0, 100, 0);
            image(nextLevelImg, 250, 240);

            //2 second delay
            if (frameCount == 120) {
                wins++;
                this.reset();
                this.setMode("game");
            }
        }
    }

    //ANIMATION & ACTIONS ---------------------------------------------------------------------------------------------
    public void tick() {

        time = millis();

        //Movement controls
        if (keys[0]) {
            tank.move("left");
        }
        if (keys[1]) {
            tank.move("right");
        }

        //random invader shoots a bullet
        Projectile p = invadersC.randomShot(wins,time);
        if(p != null){
            bullets.addBullet(p);
        }

        bullets.move(); //tank projectiles go upwards, invader ones go downwards

        //bullets added to list for removal if collision occurs
        invadersToRemove = bullets.bulletCollisionCheck(barriers, invaders, tank); //checks for collision

        //laser fired & check for collision
        invadersToRemove.addAll(bullets.laserCollisionCheck(barriers, invaders, laser, tank.laserFired()));

        //updates scores
        HashSet<Invader> deadInvaders = new HashSet<>(invadersToRemove);
        this.scoreTracker(deadInvaders);

        //invader movement
        invaders.stream().forEach(Invader::move);


        //objects to be removed after each frame
        invaders.removeAll(invadersToRemove);
        bullets.removeBullets();

        //removes barrier component completely if it's been hit three times
        barriers.forEach(barrier -> barrier.list().removeIf(BComponent::isDestroyed));

        //GAME OVER if invaders reach barrier
        for(Invader invader: invaders){
            if(invader.getYPos() >= 384){
                this.setMode("gameOver");
                frameCount = 0;
            }
        }

        //GAME OVER if tank is hit three times
        if(tank.isDestroyed()){
            this.setMode("gameOver");
            frameCount = 0;
        }

        //NEXT LEVEL if no more invaders left
        if(invaders.size() == 0){
            this.setMode("nextLevel");
            frameCount = 0;
        }
    }

    //KEYBOARD INPUT --------------------------------------------------------------------------------------------------

    public void keyPressed(){

        //Exits loading screen if enter key is pressed
        if(keyCode == 32 && mode.equals("loading")){
            this.setMode("game");
        }

        //Available keys (left, right, space, shift)
        else if(keyCode == 32 || keyCode == 39 || keyCode == 37 || keyCode == 16){

            //adds a bullet to list of bullets fired if space bar is pressed
            if(keyCode == 32 && !tank.fired()){
                bullets.addBullet(this.tank.shoot());
            }

            //tank moves left 1px
            if(keyCode == 37){
                keys[0] = true;
            }

            //tank moves right 1px
            if(keyCode == 39){
                keys[1] = true;
            }

            //laser fired
            if(keyCode == 16 && mode == "game"){
                laser = tank.laser();
            }
        }
    }

    public void keyReleased() {

        if(keyCode == 32 || keyCode == 39 || keyCode == 37 || keyCode == 16) {

            //stops moving left
            if(keyCode == 37){
                keys[0] = false;
            }

            //stops moving right
            if(keyCode == 39){
                keys[1] = false;
            }

            //tank can shoot again
            tank.resetCannon();
            tank.resetLaser();
        }
    }

    //GAME RESET UPON "NEXT LEVEL" OR "GAME OVER" ---------------------------------------------------------------------
    public void reset(){

        //objects and lists reset
        tank = new Tank(319,450);
        barriers.clear();
        invaders.clear();
        bullets.list().clear();

        //setup is run again
        this.setup();

    }

    //CREATES AND POSITIONS BARRIERS ----------------------------------------------------------------------------------

    public void loadBarrier(Barrier b, int x, int y){

        //Each barrier components image states are loaded once
        List<BComponent> components = new ArrayList<>();
        String[] imgFiles = new String[] {"bt1.png", "bt2.png", "bt3.png", "e.png","bl1.png", "bl2.png", "bl3.png", "e.png",
                                            "br1.png", "br2.png", "br3.png", "e.png", "bs1.png", "bs2.png", "bs3.png", "e.png" };

        int bImgCounter = 0; //counter for iterating through imgFiles to make sure images match their barrier type

        //An array of image arrays for each barrier component
        PImage[][] imgSets = new PImage[7][4];

        //iterating through barrier component image arrays
        for(PImage[] imgSet: imgSets){

            //iterating through the four types of images for each barrier component
            for(int i = 0; i < 3; i++){
                imgSet[i] = loadImage(imgFiles[i+bImgCounter]);
            }
            //the last four images in imgFiles are reused for the four solid barriers
            if(bImgCounter < 12) {
                bImgCounter += 4;
            }
        }

        //adding each new barrier component to the barrier with a correct position with respect to the other barrier components
        components.add(new BComponent(imgSets[0],x,y));
        components.add(new BComponent(imgSets[1],x-8,y));
        components.add(new BComponent(imgSets[2],x+8,y));
        components.add(new BComponent(imgSets[3],x-8,y+16));
        components.add(new BComponent(imgSets[4],x-8,y+8));
        components.add(new BComponent(imgSets[5],x+8,y+16));
        components.add(new BComponent(imgSets[6],x+8,y+8));

        //adds barrier component objects to barrier
        b.set(components);
    }

    //ACCURACY TRACKER ------------------------------------------------------------------------------------------------
    public void accuracyTracker(){

        String accuracy = "0.00%"; //before shots fired

        if(tank.getShotsFired() > 0) {

            float acc = ((40-invaders.size()) / (tank.getShotsFired())) * 100;

            if(acc >= 100) {
                acc = 100;
            }

            accuracy = String.format("%.2f", (acc)) + "%";
        }
        //displaying accuracy
        text(accuracy, 551, 218);

    }

    //SCORE TRACKER ---------------------------------------------------------------------------------------------------
    public void scoreTracker(HashSet<Invader> outvaders){

        for(Invader invader: outvaders){
            if (invader instanceof PowerInvader || invader instanceof ArmorInvader){
                score += 250;
            }
            else {
                score += 100;
            }
        }
        if (score > highScore){
            highScore = score;
        }

        //draws out score and high score
        text(Integer.toString(highScore), 551, 78);
        text(Integer.toString(score), 551, 150);
    }

    //SET GAME MODE ---------------------------------------------------------------------------------------------------
    public void setMode(String mode){
        this.mode = mode;
    }



    //Rad
    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }
}
