package invadem;

public class ArmorInvader extends Invader {

    private int lives;

    public ArmorInvader(int xPos, int yPos) {
        super(xPos, yPos);
        lives = 3;
    }

    @Override
    //increments "lives". If state = 3, the barrier component is marked as destroyed
    public void destroy(GameObject g) {

        if(lives > 0){
            lives--;
        }

        if(lives == 0 || g.getHeight() == 500){
            this.destroyed = true;
        }
    }

}
