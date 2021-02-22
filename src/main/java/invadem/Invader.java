package invadem;

import processing.core.PImage;


public class Invader extends GameObject {

    private PImage sprite1;
    private PImage sprite2;
    private PImage currentSprite;
    private int frameCount = 0; //controls invader horizontal movement to 1px per 2 frames
    private int moveCount = 0; //tracks pixels moved left and right
    private int downCount = 0; //controls invader horizontal movement to 1px per 2 frames
    private boolean horizontal = true; //checks whether invader should move horizontally
    private int movesDown = 0; //number of pixels invader moves down

    //Invader Constructor
    public Invader(int xPos, int yPos){

        this.width = 16;
        this.height = 16;
        this.xPos = xPos;
        this.yPos = yPos;
        this.destroyed = false;
    }

    //switches between sprites to animate invaders
    public void changeSprite(){

        if(currentSprite == sprite1){
            currentSprite = sprite2;
        }

        else{
            currentSprite = sprite1;
        }
    }

    //sets two sprites for invader
    public void setSprite(PImage sprite1, PImage sprite2){
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        this.currentSprite = this.sprite1;
    }


    @Override
    //returns invader sprite in its current state
    public PImage getImg(){
        return currentSprite;
    }



    //moves invader in specified pattern
    public void move(){

        frameCount++;

        //invader only moves 1px every 2 frames
        if(frameCount == 2 || moveCount == 60 || moveCount == 120) {

            //invader has moved 30 frames right
            if(moveCount == 60){
                this.moveDown();
            }

            else if(moveCount <= 60){
                frameCount = 0;
                this.xPos++;
            }

            else if(moveCount == 120){
                this.moveDown();
            }

            //invader has moved 30 frames left
            else{
                frameCount = 0;
                this.xPos--;
            }

        }

        downCount++;

        //moving horizontally
        if(horizontal) {
            moveCount++;
        }
    }

    public void moveDown(){
        horizontal = false; //don't move horizontally
        frameCount = 0; //frames passed

        //sprite change when about to move down
        if(movesDown == 0){
            if(moveCount == 120){
                this.xPos--; //invader x-coordinate reset to starting position
            }
            this.changeSprite();

        }

        if(downCount%2 == 0) {
            movesDown++;
            this.yPos++;

            //sprite change when moved down by 8px
            if(movesDown == 8){
                movesDown = 0;
                horizontal = true;
                this.changeSprite();

                //number of pixels moved horizontally is reset each cycle (of right 30px and left 30px)
                if(moveCount == 120){
                    moveCount = 0;
                }
            }
        }
    }

    //shoots (creates a new bullet)
    public Projectile shoot(){
        return new Projectile(this.xPos,this.yPos);
    }

}
