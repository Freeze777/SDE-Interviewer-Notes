package leetcode.medium.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Google interview question:<br>
 * We have routers placed at various coordinates in a 2D plane.
 * All the routers have a signal radial range of 10.
 * If a router receives a message it will broadcast the message immediately to all its reachable routers and will shut itself down.
 * <p>
 * Assume you already are provided with an implementation of the distance(a,b) method.
 * <p>
 * Given a list of router with a source router and target router, return true if source router can broadcast a message to target router.
 * <p>
 * Follow up:
 * Refactor the code to handle the case where a router will only broadcast to the nearest reachable routes which are within the minimum distance from it.
 * <p>
 * class Router{ <br>
 * String name; <br>
 * int x,y; <br>
 * }
 * <p>
 * public isReachable(Router source, Router target, List<Router> allRouters) {}
 * <p>
 * very similar to : <a href="https://leetcode.com/problems/detonate-the-maximum-bombs/">https://leetcode.com/problems/detonate-the-maximum-bombs/</a>
 **/
public class RouterBroadCast {

    private static class Router {
        String name;
        int x, y;

        public Router(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Router router = (Router) o;
            return x == router.x && y == router.y && name.equals(router.name);
        }
    }

    private double distance(Router a, Router b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    private boolean isInRange(Router router, Router otherRouter) {
        return distance(router, otherRouter) <= 10.0;
    }

    public boolean isReachable(Router start, Router target, List<Router> allRouters) {
        Queue<Router> bfsQ = new LinkedList<>();
        Set<String> shutdownRouters = new HashSet<>();
        bfsQ.add(start);
        while (!bfsQ.isEmpty()) {
            Router broadcastRouter = bfsQ.remove();
            if (broadcastRouter.equals(target)) return true;
            if (shutdownRouters.contains(broadcastRouter.name)) continue;
            List<Router> routersInRange = allRouters.stream().filter(nextRouter -> !nextRouter.equals(broadcastRouter)
                            && !shutdownRouters.contains(nextRouter.name)
                            && isInRange(broadcastRouter, nextRouter))
                    .collect(Collectors.toList());
            //bfsQ.addAll(routersInRange);
            bfsQ.addAll(nearestRouters(broadcastRouter, routersInRange)); // for follow-up
            shutdownRouters.add(broadcastRouter.name);
        }
        return false;
    }

    private List<Router> nearestRouters(Router source, List<Router> routersInRange) {
        double minDistance = Double.MAX_VALUE;
        HashMap<Double, List<Router>> routersByDistance = new HashMap<>();
        for (Router router : routersInRange) {
            double d = distance(source, router);
            minDistance = Math.min(d, minDistance);
            routersByDistance.putIfAbsent(d, new LinkedList<>());
            routersByDistance.computeIfPresent(d, (k, v) -> {
                v.add(router);
                return v;
            });
        }
        return routersByDistance.getOrDefault(minDistance, new LinkedList<>());
    }

    public static void main(String[] args) {
        RouterBroadCast rbc = new RouterBroadCast();
        Router A = new Router("A", 0, 0);
        Router B = new Router("B", 0, 8);
        Router C = new Router("C", 0, 17);
        Router D = new Router("D", 11, 0);
        System.out.println(rbc.isReachable(A, B, Arrays.asList(A, B, C, D))); //true
        System.out.println(rbc.isReachable(A, C, Arrays.asList(A, B, C, D))); //true
        System.out.println(rbc.isReachable(A, D, Arrays.asList(A, B, C, D))); //false

        // follow-up question testcases
        Router E = new Router("E", 4, 0);
        Router F = new Router("F", 0, 4);
        Router G = new Router("G", 0, 17);
        Router H = new Router("H", 6, 8);
        System.out.println(rbc.isReachable(A, G, Arrays.asList(A, B, E, F, G))); //true
        System.out.println(rbc.isReachable(A, G, Arrays.asList(A, B, E, F, G, H))); //false
    }
}
