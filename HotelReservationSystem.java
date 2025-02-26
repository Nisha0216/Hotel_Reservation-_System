import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void freeRoom() {
        this.isAvailable = true;
    }
}

class Reservation {
    private Room room;
    private String guestName;
    private boolean isPaid;

    public Reservation(Room room, String guestName) {
        this.room = room;
        this.guestName = guestName;
        this.isPaid = false;
    }

    public void makePayment() {
        this.isPaid = true;
    }

    public void showBookingDetails() {
        System.out.println("Guest: " + guestName);
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Category: " + room.getCategory());
        System.out.println("Price: " + room.getPrice());
        System.out.println("Payment Status: " + (isPaid ? "Paid" : "Pending"));
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Deluxe", 4000.0));
        rooms.add(new Room(102, "Standard", 2500.0));
        rooms.add(new Room(103, "Suite", 3000.0));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your  name please : ");
        String guestName = scanner.nextLine();

        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Available Room: " + room.getRoomNumber() + " (" + room.getCategory() + ") - $" + room.getPrice());
            }
        }

        System.out.println("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        Room selectedRoom = null;
        
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            selectedRoom.bookRoom();
            Reservation reservation = new Reservation(selectedRoom, guestName);
            reservation.showBookingDetails();
            System.out.println("Do you want to make payment? (yes/no)");
            scanner.nextLine();
            String paymentChoice = scanner.nextLine();
            
            if (paymentChoice.equalsIgnoreCase("yes")) {
                reservation.makePayment();
                System.out.println("Payment successful!");
            } else {
                System.out.println("Payment pending!");
            }
            reservation.showBookingDetails();
        } else {
            System.out.println("Invalid room selection or room not available!");
        }

        scanner.close();
    }
}
