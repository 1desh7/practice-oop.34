package Pack7;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testItemConversion() {
        Item2d item = new Item2d();
        item.setResults(255, Integer.toBinaryString(255), Integer.toOctalString(255), Integer.toHexString(255).toUpperCase());
        
        assertEquals(255, item.getValue());
        assertEquals("11111111", item.getBinary());
        assertEquals("377", item.getOctal());
        assertEquals("FF", item.getHex());
    }

    @Test
    public void testItemEquality() {
        Item2d item1 = new Item2d();
        item1.setResults(100, Integer.toBinaryString(100), Integer.toOctalString(100), Integer.toHexString(100).toUpperCase());
        
        Item2d item2 = new Item2d();
        item2.setResults(100, Integer.toBinaryString(100), Integer.toOctalString(100), Integer.toHexString(100).toUpperCase());
        
        assertEquals(item1, item2);
    }
}
