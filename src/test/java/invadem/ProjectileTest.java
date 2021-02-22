package invadem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectileTest {

    private Tank tank = new Tank(319,450);
    private Projectile projectile = new Projectile(tank.getXPos(),tank.getYPos());
    @Before

    @Test
    //Tests projectile construction
    public void testProjectileConstruction() {
        assertNotNull(projectile);
    }

    @Test
    //Tests that tank projectiles move upwards
    public void testFriendlyMove(){
        projectile.moveUp();
        assertEquals(447,projectile.getYPos());
    }

    @Test
    //Tests that invader projectiles move downwards
    public void testEnemyMove(){
        projectile.moveDown();
        assertEquals(449,projectile.getYPos());
    }

}