import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <a href="https://whimsical.com/system-design-interviews-UHCZM4RoLZgxqfifsb56tv"> White Board</a>
 */
public class TestClass {

    public int add(int x, int y) {
        return x + y;
    }

    @Test
    public void test() {
        assertEquals(add(2, 3), 5);
    }
}
