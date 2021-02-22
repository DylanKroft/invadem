package invadem;

public class PowerProjectile extends Projectile {

    private boolean friendly;


    protected PowerProjectile(int xPos, int yPos) {

        super(xPos, yPos);
        height = 5;
        width = 2;
        destroyed = false;
        friendly = false;

    }
}
