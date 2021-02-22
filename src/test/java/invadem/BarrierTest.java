package invadem;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BarrierTest {

    private List<BComponent> barrierParts = new ArrayList<>();
    private Barrier b = new Barrier();
    @Before

    @Test
    //Tests barrier construction
    public void barrierConstruction() {
        b.set(barrierParts);
        assertNotNull(b);
   }

   @Test
   //Tests that barrier can be set with a list of barrier components
    public void testListBarrierComponents(){
        b.set(barrierParts);
        assertNotNull(b.list());
   }

}
