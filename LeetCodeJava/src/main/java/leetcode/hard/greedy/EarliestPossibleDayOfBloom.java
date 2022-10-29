package leetcode.hard.greedy;

import java.util.*;

public class EarliestPossibleDayOfBloom {
    private static class Plant implements Comparable<Plant> {
        int plantTime, growTime;

        Plant(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        @Override
        public int compareTo(Plant o) {
            if (o.growTime == this.growTime) return (o.plantTime - this.plantTime);
            else return (o.growTime - this.growTime);
        }

        @Override
        public String toString() {
            return "Plant{" + " plantTime=" + plantTime + ", growTime=" + growTime + '}';
        }
    }

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < plantTime.length; i++) {
            plants.add(new Plant(plantTime[i], growTime[i]));
        }
        Collections.sort(plants); // highest grow time first
        int movingDays = 0, lastBloomDay = Integer.MIN_VALUE;
        for (Plant plant : plants) {
            movingDays += plant.plantTime;
            lastBloomDay = Math.max(lastBloomDay, movingDays + plant.growTime);
        }
        return lastBloomDay;
    }

    public static void main(String[] args) {
        EarliestPossibleDayOfBloom epdb = new EarliestPossibleDayOfBloom();
        System.out.println(epdb.earliestFullBloom(new int[]{1, 4, 3}, new int[]{2, 3, 1}));
        System.out.println(epdb.earliestFullBloom(new int[]{1, 2, 3, 2}, new int[]{2, 1, 2, 1}));
        System.out.println(epdb.earliestFullBloom(new int[]{1}, new int[]{1}));
        System.out.println(epdb.earliestFullBloom(new int[]{7, 5, 1, 2}, new int[]{5, 1, 6, 4}));
        System.out.println(epdb.earliestFullBloom(new int[]{7, 5, 1, 2}, new int[]{5, 1, 6, 5}));
    }
}
