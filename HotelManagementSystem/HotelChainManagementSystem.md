# Hotel Chain Management System

### **Requirements**

#### **1. Hotel Management**
- Manage multiple hotels under a single chain.
- Each hotel has:
  - A unique ID and name.
  - Address and contact information.
  - A list of rooms.
  - Facilities (e.g., gym, pool, parking).

#### **2. Room Management**
- Different room types (e.g., Single, Double, Suite).
- Room attributes:
  - Unique room number or ID.
  - Room type.
  - Price per night.
  - Status (Available, Booked, Occupied, Maintenance).

#### **3. Reservation Management**
- Book a room for a guest.
  - Store check-in and check-out dates.
  - Handle overbooked or unavailable rooms.
- Modify or cancel reservations.
- Reservation status: Confirmed, Cancelled, Checked-In, Checked-Out.
- Assign room(s) during check-in.

#### **4. Guest Management**
- Maintain guest information:
  - Name, contact details, and ID.
  - Reservation history.
- Support for corporate or group bookings.

#### **5. Billing and Payment**
- Generate bills based on:
  - Room charges (number of nights × price per night).
  - Additional charges (food, services, etc.).
- Payment options:
  - Credit Card, Debit Card, Cash, Digital Wallets.
- Payment status: Paid, Pending.

#### **6. Services and Amenities**
- Manage hotel services:
  - Room Service.
  - Restaurant and Food Ordering.
  - Concierge, Spa, Gym.
- Charge additional fees for premium services.

#### **7. Staff Management**
- Staff roles:
  - Receptionist, Housekeeping, Management, etc.
- Assign tasks to staff, e.g., cleaning, maintenance.
- Track working hours and shifts.

#### **8. Reporting and Analytics**
- Generate reports:
  - Occupancy rates.
  - Revenue and income per hotel or chain.
  - Guest feedback and ratings.
- Monitor reservations and cancellations.

#### **9. Scalability and Multi-Hotel Support**
- Support multiple hotels in different locations.
- Enable centralized and local hotel management.

#### **10. Security and Access Control**
- Role-based access:
  - Admin: Manage the entire system.
  - Manager: Manage a specific hotel.
  - Staff: Perform assigned tasks.
- Secure storage of guest and payment information.

#### **11. Notifications**
- Notify guests for:
  - Reservation confirmation.
  - Check-in and check-out reminders.
  - Payment receipts.
- Notify staff for tasks like room cleaning or maintenance.

#### **12. Maintenance**
- Mark rooms as "Under Maintenance."
- Record and schedule maintenance tasks.

---

### **Entities and Relationships**
Based on the above requirements, the key entities include:

1. **Hotel**
   - Attributes: ID, name, address, contact info, facilities.
   - Relationships: Has many rooms, managed by staff.

2. **Room**
   - Attributes: Room number, type, price, status.
   - Relationships: Belongs to a hotel, linked to reservations.

3. **Guest**
   - Attributes: ID, name, contact info, booking history.
   - Relationships: Can have multiple reservations.

4. **Reservation**
   - Attributes: ID, guest, room, check-in/out dates, status.
   - Relationships: Links guest and room.

5. **Bill**
   - Attributes: ID, reservation, total amount, payment status.
   - Relationships: Linked to reservation and payment.

6. **Payment**
   - Attributes: ID, amount, method, status.
   - Relationships: Linked to bill.

7. **Staff**
   - Attributes: ID, name, role, tasks.
   - Relationships: Belongs to a hotel, performs tasks.

8. **Task**
   - Attributes: ID, description, status.
   - Relationships: Assigned to staff, related to room or hotel.

---

### **Features for LLD**
Using the requirements, the LLD should include:
- **Classes** for each entity (e.g., `Hotel`, `Room`, `Guest`, `Reservation`, etc.).
- **Relationships** (e.g., aggregation for Hotel and Room, association for Guest and Reservation).
- **Methods** for behavior (e.g., `bookRoom`, `generateBill`, `checkIn`).
- **Enums** for fixed states (e.g., RoomStatus, ReservationStatus, PaymentMethod).
- **Interfaces** for extensibility (e.g., `Payment`).

---

### Example Use Cases for LLD
1. **Booking a Room**:
   - Check room availability for a hotel.
   - Create a reservation with guest and room details.
   - Update room status to Booked.

2. **Checking In**:
   - Update reservation status to Checked-In.
   - Change room status to Occupied.

3. **Processing Payment**:
   - Calculate bill based on room and additional charges.
   - Process payment and update status.

4. **Managing Staff Tasks**:
   - Assign cleaning tasks to housekeeping.
   - Notify staff when tasks are created.

---
### **Class Diagram**
```mermaid
classDiagram

    class HotelManagement {
        +List~Hotel~ hotels
        +addHotel(Hotel hotel)
        +getAllHotels(): List~Hotel~
        +searchAvailableRooms(): List~Room~
    }

    class Hotel {
        +String id
        +String name
        +String address
        +String contactInfo
        +List~Room~ rooms
        +List~Staff~ staff
        +List~Facility~ facilities
        +addRoom(Room room)
        +addStaff(Staff staff)
        +getAvailableRooms(): List~Room~
    }

    class Room {
        +String id
        +RoomType type
        +double price
        +RoomStatus status
        +book()
        +checkIn()
        +checkOut()
        +markUnderMaintenance()
    }

    class Guest {
        +String id
        +String name
        +String email
        +String phoneNumber
        +List~Reservation~ reservations
        +addReservation(Reservation reservation)
    }

    class Reservation {
        +String id
        +LocalDate checkInDate
        +LocalDate checkOutDate
        +ReservationStatus status
        +Guest guest
        +Room room
        +cancel()
        +getTotalCost(): double
    }

    class Payment {
        +String id
        +double amount
        +PaymentMethod method
        +PaymentStatus status
        +processPayment(double amount): boolean
    }

    class CreditCardPayment {
        +processPayment(double amount): boolean
    }

    class CashPayment {
        +processPayment(double amount): boolean
    }

    class Staff {
        +String id
        +String name
        +String role
        +List~Task~ tasks
        +assignTask(Task task)
    }

    class Task {
        +String id
        +String description
        +TaskStatus status
        +completeTask()
    }

    class Facility {
        +String name
        +String description
    }

    class RoomType {
        <<enumeration>>
        +SINGLE
        +DOUBLE
        +DELUXE
        +SUITE
    }

    class RoomStatus {
        <<enumeration>>
        +AVAILABLE
        +BOOKED
        +OCCUPIED
    }

    class ReservationStatus {
        <<enumeration>>
        +CONFIRMED
        +CANCELLED
    }

    class PaymentMethod {
        <<enumeration>>
        +CREDIT_CARD
        +CASH
    }

    class PaymentStatus {
        <<enumeration>>
        PENDING
        IN_PROGRESS
        COMPLETED
    }
    class TaskStatus {
        <<enumeration>>
        PENDING
        IN_PROGRESS
        COMPLETED
    }

    HotelManagement "1" --> "many" Hotel : manages
    Hotel "1" --> "many" Room : contains
    Hotel "1" --> "many" Staff : employs
    Hotel "1" --> "many" Facility : provides
    Room "1" --> "1" Reservation : associated_with
    Room --> RoomType
    Room --> RoomStatus
    
    Reservation "1" --> "1" Guest : belongs_to
    Reservation "1" --> "1" Room : for
    Reservation "1" --> "1" Payment : requires
    Reservation --> ReservationStatus
    
    Guest "1" --> "many" Reservation : has
    Staff "1" --> "many" Task : assigned_to
    
    Payment <|-- CreditCardPayment : implements
    Payment <|-- CashPayment : implements
    Payment --> PaymentStatus
    Payment --> PaymentMethod
    
    Task "1" --> "1" Staff : assigned_to
    Task --> TaskStatus

```
