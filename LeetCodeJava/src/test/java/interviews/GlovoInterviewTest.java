package interviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

public class GlovoInterviewTest {

    // Problem 1
    public List<Integer> missingProducts(List<Integer> requested, List<Integer> delivered) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer product : requested) {
            map.compute(product, (k, v) -> v == null ? 1 : v + 1);
        }

        for (Integer product : delivered) {
            map.compute(product, (k, v) -> v == null ? -1 : v - 1);
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    // Problem 2
    public int countPairs(List<Integer> socks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer sock : socks) {
            map.compute(sock, (k, v) -> v == null ? 1 : v + 1);
        }
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
