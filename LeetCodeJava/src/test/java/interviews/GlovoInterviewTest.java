package interviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

public class GlovoInterviewTest {

    // Problem 1
    public List<Integer> missingProducts(List<Integer> requested, List<Integer> delivered) {
        var map = new TreeMap<Integer, Integer>();
        requested.forEach(p -> map.merge(p, 1, Integer::sum));
        delivered.forEach(p -> map.merge(p, -1, Integer::sum));

        return map.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .toList();
    }


    // Problem 2
    public int countPairs(List<Integer> socks) {
        var map = new HashMap<Integer, Integer>();
        socks.forEach(s -> map.merge(s, 1, Integer::sum));
        return map.values().stream().mapToInt(sockCount -> sockCount / 2).sum();
    }

    @Test
    public void testMissingProducts() {
        assertEquals(missingProducts(Arrays.asList(1, 3, 5), Arrays.asList(5, 3, 1)), Collections.emptyList());
        assertEquals(missingProducts(Arrays.asList(11, 2, 4, 11, 11, 2, 3), Arrays.asList(2, 4, 2, 11)), Arrays.asList(3, 11));
    }

    @Test
    public void testCountPairs() {
        assertEquals(countPairs(Arrays.asList(4, 4, 4, 4, 4, 5, 5)), 3);
        assertEquals(countPairs(Arrays.asList(4, 7, 3, 7, 4, 1, 8)), 2);
        assertEquals(countPairs(List.of(4)), 0);
        assertEquals(countPairs(Arrays.asList(4, 5, 6)), 0);
        assertEquals(countPairs(Arrays.asList(4, 5, 6, 6)), 1);
    }

}
