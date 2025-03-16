import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BunTest {

    @Test
    public void testConstructor() {
        String name = "Флюоресцентная булка R2-D3";
        float price = 988f;
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.name);
        assertEquals(price, bun.price, 0.01f);
    }

    @Test
    public void testGetName() {
        String name = "Флюоресцентная булка R2-D3";
        float price = 988f;
        Bun bun = new Bun(name, price);
        assertNotNull(bun.name);
        assertEquals(name, bun.name);
    }

    @Test
    public void testGetPrice() {
        String name = "Флюоресцентная булка R2-D3";
        float price = 988f;
        Bun bun = new Bun(name, price);
        assertNotNull(bun.price);
        assertEquals(price, bun.price, 0.01f);
    }
}