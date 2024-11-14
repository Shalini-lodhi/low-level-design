package RideSharing;

public class Vehicle {
    String vehicleId;
    String userId;
    String model;

    public Vehicle(String vehicleId, String userId, String model) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.model = model;
    }

    @Override
    public String toString() {
        return model;
    }
}
