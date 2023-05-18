import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <a href="https://whimsical.com/system-design-interviews-UHCZM4RoLZgxqfifsb56tv"> White Board</a>
 */
public class TalabatInterviewTest {

    @Test
    public void test() {
        assertEquals(multiply(2, 3), 6);
        assertEquals(multiply(-2, 3), -6);
        assertEquals(multiply(2, -3), -6);
        assertEquals(multiply(-2, -3), 6);
        assertEquals(multiply(2, 0), 0);
        assertEquals(multiply(0, 2), 0);
        assertEquals(multiply(0, -2), 0);
        assertEquals(multiply(-2, 0), 0);
        assertEquals(multiply(0, 0), 0);
        assertTrue(multiply(Integer.MAX_VALUE, Integer.MAX_VALUE) > 0);
        assertTrue(multiply(Integer.MIN_VALUE, Integer.MIN_VALUE) > 0);//failed
    }


    /**
     * multiply 2 numbers with using x operator.
     * NOTE: focus on correctness
     **/
    public long multiply(int x, int y) {
        if (x < 0 && y < 0) return multiplyHelper(-x, -y);
        if (x >= 0 && y >= 0) return multiplyHelper(x, y);
        return multiplyHelper(Math.max(x, y), Math.min(x, y));// one of them is -ve
    }

    public long multiplyHelper(int x, int y) {
        long ans = 0L;
        for (int i = 0; i < x; i++) {
            ans += y;
        }
        return ans;
    }
}


