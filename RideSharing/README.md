# Ride-Sharing Application

## Description
Implement a ride-sharing application with the following expected features.

## Features

- The application allows users to share rides on a route.
- Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
- Users can search and select from multiple available rides on a route with the same source and destination.

## Requirements

### 1. User Onboarding
- `add_user(user_detail)`
  - Add basic user details.
- `add_vehicle(vehicle_detail)`
  - Add the user’s vehicle(s) details.

### 2. Offer a Shared Ride
- `offer_ride(ride_detail)`
  - Ride will have details such as vehicle, origin, destination, and available seats. (A ride will have no intermediate stops.)

### 3. Select a Ride
- `select_ride(source, destination, seats, selection_strategy)`
  - Users can select a ride from multiple offered rides using a selection strategy. (A user can only request a ride for 1 or 2 people.)
  - **Selection Strategies:**
    - Preferred Vehicle (e.g., Activa/Polo/XUV)
    - Most Vacant

### 4. End a Ride
- `end_ride(ride_details)`
  - The system should be able to end the ride. Users can only offer a ride for a given vehicle once there are no active offered rides for that vehicle.

### 5. Ride Statistics
- `print_ride_stats()`
  - Find the total number of rides offered/taken by all users.

## Bonus Question
- If the user’s origin/destination is not directly available but can be reached via multiple rides, the application should output the rides.  
  **Example**: For input `Bangalore to Mumbai`, the output could be `Bangalore to Goa` and `Goa to Mumbai`.

## Sample Test Cases

### Onboard 5 Users
```plaintext
add_user(“Rohan, M, 36”); add_vehicle(“Rohan, Swift, KA-01-12345”)
add_user(“Shashank, M, 29”); add_vehicle(“Shashank, Baleno, TS-05-62395”)
add_user(“Nandini, F, 29”)
add_user(“Shipra, F, 27”); add_vehicle(“Shipra, Polo, KA-05-41491”); add_vehicle(“Shipra, Activa, KA-12-12332”)
add_user(“Gaurav, M, 29”)
add_user(“Rahul, M, 35”); add_vehicle(“Rahul, XUV, KA-05-1234”)
```
#### Offer 4 Rides by 3 Users
```
offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Bangalore”)
offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, Vehicle=Activa, KA-12-12332, Destination=Mysore”)
offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, Vehicle=Polo, KA-05-41491, Destination=Mysore”)
offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, Vehicle=Baleno, TS-05-62395, Destination=Bangalore”)
offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5, Vehicle=XUV, KA-05-1234, Destination=Bangalore”)
offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Pune”)
The last call should fail as the vehicle is already offering an active ride.
```

#### Find Rides for 4 Users
```
select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”)
  - Expected output: 2(c)
select_ride(“Gaurav, Origin=Bangalore, Destination=Mysore, Seats=1, Preferred Vehicle=Activa”)
  - Expected output: 2(b)
select_ride(“Shashank, Origin=Mumbai, Destination=Bangalore, Seats=1, Most Vacant”)
  - Expected output: No rides found
select_ride(“Rohan, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Baleno”)
  - Expected output: 2(d)
select_ride(“Shashank, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Polo”)
  - Expected output: No rides found
```
#### End Rides
```
end_ride(2-a)
end_ride(2-b)
end_ride(2-c)
end_ride(2-d)
```
#### Print Ride Statistics
```
Nandini: 1 Taken, 0 Offered
Rohan: 1 Taken, 1 Offered
Shashank: 0 Taken, 1 Offered
Gaurav: 1 Taken, 0 Offered
Rahul: 0 Taken, 0 Offered
Shipra: 0 Taken, 2 Offered
```