package interviews;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * We need to support electric and mechanic bikes
 * There will be stations where the bikes can be parked.
 * The stations have a max number of available parking slots.
 * User can rent a bike, the price will be different depending on the type of bike:
 * - Mechanic bikes: free
 * - Electric bikes: price is calculated based on the distance of the ride
 */
public class GlovoBikeRentalSystem {

    static abstract class IBike {
        private final String bikeId;
        protected double basePrice;

        protected IBike(String bikeId, double basePrice) {
            this.bikeId = bikeId;
            this.basePrice = basePrice;
        }

        public final double price(double kms) {
            return kms * basePrice;
        }

    }

    static class Electric extends IBike {
        public Electric(String bikeId) {
            super(bikeId, 0.8);
        }
    }

    static class Mechanical extends IBike {
        public Mechanical(String bikeId) {
            super(bikeId, 0.0);
        }
    }

    static class Station {
        private final String stationId;
        private final int maxSpots;
        private final List<IBike> dockedBikes;

        public Station(String stationId, int maxSpots) {
            this.stationId = stationId;
            this.maxSpots = maxSpots;
            dockedBikes = new ArrayList<>();
        }
    }

    static class User {
        private final int userId;

        public User(int userId) {
            this.userId = userId;
        }
    }

    static class Rental {
        private final String rideId;
        private final User user;
        private final IBike bike;
        private final LocalDateTime start;
        private final LocalDateTime end;
        private final double distanceTravelledKm;
        private final double price; // computed

        public Rental(String rideId,
                      User user,
                      IBike bike,
                      LocalDateTime start,
                      LocalDateTime end,
                      double distanceTravelledKm) {
            this.rideId = rideId;
            this.user = user;
            this.bike = bike;
            this.start = start;
            this.end = end;
            this.distanceTravelledKm = distanceTravelledKm;
            this.price = bike.price(distanceTravelledKm);
        }
    }
}