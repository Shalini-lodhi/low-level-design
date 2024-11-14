import RideSharing.RideSharingApplication;

public class TestCases {
    public static void main(String[] args) {
        RideSharingApplication app = new RideSharingApplication();

        // Onboard 5 users
        app.addUser("U1", "Rohan");
        app.addVehicle("V1", "U1", "Swift");
        app.addUser("U2", "Shashank");
        app.addVehicle("V2", "U2", "Baleno");
        app.addUser("U3", "Nandini");
        app.addUser("U4", "Shipra");
        app.addVehicle("V3", "U4", "Polo");
        app.addVehicle("V4", "U4", "Activa");
        app.addUser("U5", "Gaurav");
        app.addUser("U6", "Rahul");
        app.addVehicle("V5", "U6", "XUV");

        // Offer 4 rides by 3 users
        app.offerRide("R1", "V1", "Hyderabad", "Bangalore", 1);
        app.offerRide("R2", "V4", "Bangalore", "Mysore", 1);
        app.offerRide("R3", "V3", "Bangalore", "Mysore", 2);
        app.offerRide("R4", "V2", "Hyderabad", "Bangalore", 2);
        app.offerRide("R5", "V5", "Hyderabad", "Bangalore", 5);

        // Attempt to offer an already existing ride by Rohan
        app.offerRide("R6", "V1", "Bangalore", "Pune", 1); // This should fail

        // Find rides for users
        app.selectRide("U3", "Bangalore", "Mysore", 1, "Most Vacant"); // Expected: R3
        app.selectRide("U5", "Bangalore", "Mysore", 1, "Preferred Vehicle"); // Expected: R2
        app.selectRide("U2", "Mumbai", "Bangalore", 1, "Most Vacant"); // Expected: No rides found
        app.selectRide("U1", "Hyderabad", "Bangalore", 1, "Preferred Vehicle"); // Expected: R4
        app.selectRide("U2", "Hyderabad", "Bangalore", 1, "Preferred Vehicle"); // Expected: No rides found

        // End rides
        app.endRide("R1");
        app.endRide("R2");
        app.endRide("R3");
        app.endRide("R4");

        // Print ride stats
        app.printRideStats();
        // Expected output:
        // Nandini: 1 Taken, 0 Offered
        // Rohan: 1 Taken, 1 Offered
        // Shashank: 0 Taken, 1 Offered
        // Gaurav: 1 Taken, 0 Offered
        // Rahul: 0 Taken, 0 Offered
        // Shipra: 0 Taken, 2 Offered
    }
}
