package invadem;

public class PowerInvader extends Invader {


    public PowerInvader(int xPos, int yPos) {

        super(xPos, yPos);
    }

    //shoots (creates a new bullet)
    public PowerProjectile shoot(){
        return new PowerProjectile(this.xPos,this.yPos);
    }

}
