package RideSharing;

import java.util.Date;

public class Ride {
    String rideId;
    String vehicleId;
    String driverId;
    String origin;
    String destination;
    int availableSeats;
    boolean isActive;
    Date timestamp;

    public Ride(String rideId, String vehicleId, String driverId, String origin, String destination, int availableSeats) {
        this.rideId = rideId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.isActive = true;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Ride from " + origin + " to " + destination + ", Seats: " + availableSeats + ", Vehicle: " + vehicleId;
    }
}
