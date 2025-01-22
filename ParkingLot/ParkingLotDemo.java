public class ParkingLotDemo {
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 5));
        parkingLot.addLevel(new Level(2, 5));
        parkingLot.addLevel(new Level(3, 5));

        Vehicle car = new Car("ABC123");
        Vehicle truck = new Truck("XYZ789");
        Vehicle motorcycle = new Motorcycle("M1234");

        // Park vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        // Display availability
        parkingLot.displayAvailability();

        // Unpark vehicle
        parkingLot.unparkVehicle(motorcycle);

        // Display updated availability
        parkingLot.displayAvailability();
    }    
}