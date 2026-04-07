package Task4;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

public class MainTest {
    private static final String FNAME = "items.bin";

    @Test
    public void testTable() {
        ViewTable vt = new ViewTable();
        vt.addResult(255);
        assertEquals("FF", vt.getItems().get(0).getHex());
    }

    @Test
    public void testSerialization() {
        try {
            // Clean up any existing file
            File file = new File(FNAME);
            if (file.exists()) {
                file.delete();
            }

            ViewTable v1 = new ViewTable();
            v1.addResult(100);
            v1.viewSave();
            
            ViewTable v2 = new ViewTable();
            v2.viewRestore();
            
            assertFalse("v2 should have items", v2.getItems().isEmpty());
            assertEquals(v1.getItems().get(0), v2.getItems().get(0));
            
            // Clean up
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}