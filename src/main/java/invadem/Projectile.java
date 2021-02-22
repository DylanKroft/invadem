package invadem;

public class Projectile extends GameObject {

    private boolean friendly;

    //Projectile constructor
    protected Projectile(int xPos, int yPos){

        this.xPos = xPos+11;
        this.yPos = yPos-2;
        height = 3;
        width = 1;
        destroyed = false;
        friendly = false;

    }

    //tank bullets move up screen towards enemy
    public void moveUp(){
        this.yPos--;
    }

    //enemy bullets move down screen towards tank
    public void moveDown(){
        this.yPos++;
    }

    public void setFriendly(){
        this.friendly = true;
    }

    public boolean isFriendly(){
        return this.friendly;
    }

}
