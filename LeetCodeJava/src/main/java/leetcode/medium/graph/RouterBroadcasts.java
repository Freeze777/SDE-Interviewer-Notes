package leetcode.medium.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * We have several WiFi routers placed at various (x,y) coordinates in a given 2D plane.
 * All the routers have a signal range of 10 units and each router has a unique name.
 * If a router receives a message, it will broadcast it to all reachable routers within its range and shut itself down almost instantly.
 * For simplicity, assume you are already provided with an implementation of the distance(Router a, Router b) method.
 * Given a list of routers, a source router, and a target router, return true if the source router can broadcast a message to the target router.
 * <p>
 * Router {
 * string name;
 * int x,y;
 * }
 * bool isReachable(Router source, Router target, List<Router> routers) {
 * }
 **/
public class RouterBroadcasts {

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
            //bfsQ.addAll(routersInRange); // first version
            bfsQ.addAll(nearestRouters(broadcastRouter, routersInRange)); // follow-up version
            shutdownRouters.add(broadcastRouter.name);
        }
        return false;
    }

    private List<Router> nearestRouters(Router source, List<Router> routersInRange) {
        double minDistance = Double.MAX_VALUE;
        HashMap<Double, List<Router>> routersByDistance = new HashMap<>();
        for (Router router : routersInRange) {
            double dist = distance(source, router);
            minDistance = Math.min(dist, minDistance);
            routersByDistance.putIfAbsent(dist, new LinkedList<>());
            routersByDistance.computeIfPresent(dist, (d, routers) -> {
                routers.add(router);
                return routers;
            });
        }
        return routersByDistance.getOrDefault(minDistance, new LinkedList<>());
    }

    public static void main(String[] args) {
        // initial version testcases
        RouterBroadcasts rbc = new RouterBroadcasts();
        Router A = new Router("A", 0, 0);
        Router B = new Router("B", 0, 8);
        Router C = new Router("C", 0, 17);
        Router D = new Router("D", 11, 0);
        System.out.println(rbc.isReachable(A, B, Arrays.asList(A, B, C, D))); //true
        System.out.println(rbc.isReachable(A, C, Arrays.asList(A, B, C, D))); //true
        System.out.println(rbc.isReachable(A, D, Arrays.asList(A, B, C, D))); //false

        // follow-up version testcases
        Router E = new Router("E", 4, 0);
        Router F = new Router("F", 0, 4);
        Router H = new Router("H", 6, 8);
        System.out.println(rbc.isReachable(A, C, Arrays.asList(A, B, C, E, F))); //true
        System.out.println(rbc.isReachable(A, C, Arrays.asList(A, B, C, E, F, H))); //false
    }
}
