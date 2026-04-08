package Task5;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testSingleton() {
        Application app1 = Application.getInstance();
        Application app2 = Application.getInstance();
        assertSame(app1, app2);
    }

    @Test
    public void testUndo() {
        Application app = Application.getInstance();
        ViewResult view = new ViewResult();
        app.addCommand(new ViewCommand(view));
        app.execute('v');
        app.undo();
    }

    @Test
    public void testItemEquality() {
        Item2d item1 = new Item2d();
        item1.setResults(255, Integer.toBinaryString(255), Integer.toOctalString(255), Integer.toHexString(255).toUpperCase());
        
        Item2d item2 = new Item2d();
        item2.setResults(255, Integer.toBinaryString(255), Integer.toOctalString(255), Integer.toHexString(255).toUpperCase());
        
        assertEquals(item1, item2);
    }
}