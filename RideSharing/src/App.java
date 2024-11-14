import RideSharing.RideSharingApplication;

public class App {
    public static void main(String[] args) throws Exception {
        RideSharingApplication app = new RideSharingApplication();

        // Add users
        app.addUser("U1", "John");
        app.addUser("U2", "Alice");

        // Add vehicles
        app.addVehicle("V1", "U1", "Activa");
        app.addVehicle("V2", "U1", "Polo");

        // Offer rides
        app.offerRide("R1", "V1", "Bangalore", "Mumbai", 2);
        app.offerRide("R2", "V2", "Bangalore", "Pune", 3);

        // Select rides
        app.selectRide("U2", "Bangalore", "Mumbai", 1, "Preferred Vehicle");
        app.selectRide("U2", "Bangalore", "Pune", 2, "Most Vacant");

        // End ride
        app.endRide("R1");

        // Print stats
        app.printRideStats();
    }
}
