package RideSharing;

import java.util.*;

class User {
    String userId;
    String name;
    List<Ride> ridesOffered;
    List<Ride> ridesTaken;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.ridesOffered = new ArrayList<>();
        this.ridesTaken = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }
}