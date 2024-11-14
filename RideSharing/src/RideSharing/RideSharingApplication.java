package RideSharing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideSharingApplication {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Vehicle> vehicles = new HashMap<>();
    private List<Ride> rides = new ArrayList<>();

    // Method to add a user
    public void addUser(String userId, String name) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId, name));
            System.out.println(name + " added successfully.");
        } else {
            System.out.println("User already exists.");
        }
    }

    // Method to add a vehicle
    public void addVehicle(String vehicleId, String userId, String model) {
        if (users.containsKey(userId)) {
            vehicles.put(vehicleId, new Vehicle(vehicleId, userId, model));
            System.out.println("Vehicle " + model + " added successfully.");
        } else {
            System.out.println("User does not exist. Please register first.");
        }
    }

    // Method to offer a ride
    public void offerRide(String rideId, String vehicleId, String origin, String destination, int availableSeats) {
        if (vehicles.containsKey(vehicleId)) {
            Vehicle vehicle = vehicles.get(vehicleId);
            User user = users.get(vehicle.userId);
            Ride ride = new Ride(rideId, vehicleId, vehicle.userId, origin, destination, availableSeats);
            rides.add(ride);
            user.ridesOffered.add(ride);

            System.out.println("Ride offered successfully." );
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // Method to select a ride based on strategy
    public void selectRide(String userId, String source, String destination, int seats, String selectionStrategy) {
        List<Ride> availableRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.isActive && ride.origin.equals(source) && ride.destination.equals(destination) && ride.availableSeats >= seats) {
                availableRides.add(ride);
            }
        }

        if (availableRides.isEmpty()) {
            System.out.println("No rides available.");
            return;
        }

        if (selectionStrategy.equalsIgnoreCase("Preferred Vehicle")) {
            availableRides.sort(Comparator.comparing(ride -> vehicles.get(ride.vehicleId).model));
        } else if (selectionStrategy.equalsIgnoreCase("Most Vacant")) {
            availableRides.sort((r1, r2) -> r2.availableSeats - r1.availableSeats);
        }

        Ride selectedRide = availableRides.get(0);
        selectedRide.availableSeats -= seats;
        if (selectedRide.availableSeats == 0) {
            selectedRide.isActive = false;
        }
        users.get(userId).ridesTaken.add(selectedRide);
        System.out.println("Ride selected successfully: " + selectedRide);
    }

    // Method to end a ride
    public void endRide(String rideId) {
        for (Ride ride : rides) {
            if (ride.rideId.equals(rideId)) {
                ride.isActive = false;
                System.out.println("Ride ended successfully.");
                return;
            }
        }
        System.out.println("Ride not found.");
    }

    // Method to print ride stats
    public void printRideStats() {
        for (User user : users.values()) {
            System.out.println(user.name + " offered " + user.ridesOffered.size() + " rides and took " + user.ridesTaken.size() + " rides.");
        }
    }
}
