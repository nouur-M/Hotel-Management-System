import java.util.*;

class Rooms {

    int roomNo;
    int price;
    String type;
    boolean isBooked;

}

class Customer {

    String name;
    String phoneNo;
    int CustomerId;
}

class Reservation {

    Customer customer;
    Rooms room;
    int Nodays;
    int TotalPrice;

}



class Hotel {

    ArrayList<Rooms> room = new ArrayList<>();
    ArrayList<Reservation> res = new ArrayList<>();

    public Hotel () {
        // single rooms
        for (int i = 1; i <= 2; i++) {

            Rooms r = new Rooms();
            r.roomNo = i;
            r.type = "Single";
            r.price = 100;
            r.isBooked = false;
            room.add(r);

        }

        // double rooms
        for (int i = 3; i <= 4; i++) {

            Rooms r = new Rooms();
            r.roomNo = i;
            r.type = "Double";
            r.price = 200;
            r.isBooked = false;
            room.add(r);


        }

        // suites
        Rooms r = new Rooms();
        r.roomNo = 5;
        r.type = "Suite";
        r.price = 350;
        r.isBooked = false;
        room.add(r);



    }

// *************************************************************************************** //

         public void ViewAllRooms() {

            for (int i = 0; i < room.size(); i++) {

                Rooms r = room.get(i);

                System.out.println("Room No: " + r.roomNo);
                System.out.println("Type: " + r.type);
                System.out.println("Price: " + r.price);
                System.out.println("Booked: " + r.isBooked);
                System.out.println("----------------------");
            }
        }

// *************************************************************************************** //

    public void ViewAvailableRooms() {

        for (int i = 0; i < room.size(); i++) {

            Rooms r = room.get(i);

            if (!r.isBooked) {

                System.out.println("Room No: " + r.roomNo);
                System.out.println("Type: " + r.type);
                System.out.println("Price: " + r.price);
                System.out.println("Booked: " + r.isBooked);
                System.out.println("          ");
            }
        }
    }

// *************************************************************************************** //

        public void BookRoom(Customer customer, int roomNo, int days)
        {
            for (Rooms r : room) {
                if (r.roomNo == roomNo && !r.isBooked) {
                    r.isBooked = true;

                    Reservation reserv = new Reservation();
                    reserv.customer = customer;
                    reserv.room = r;
                    reserv.Nodays = days;
                    reserv.TotalPrice = r.price * days;

                    res.add(reserv);

                    System.out.println("Total price: " + reserv.TotalPrice);
                    System.out.println("Room booked successfully!");

                    return;
                }
            }
            System.out.println("Room not available.");
        }

// *************************************************************************************** //


        public void ViewReservations ()
        {
            if (!res.isEmpty()) {

                for (Reservation r : res) {
                    System.out.println("Customer: " + r.customer.name +
                            " | Room: " + r.room.roomNo +
                            " | Days: " + r.Nodays +
                            " | Total: " + r.TotalPrice);
                }
            } else{
                System.out.println("No reservations yet!");
            }

        }

 // *************************************************************************************** //

    public void CancelReservation(int Id) {

        for (int i = 0; i < res.size(); i++) {

            Reservation r = res.get(i);

            if (r.customer.CustomerId == Id) {

                r.room.isBooked = false;   // free the room
                res.remove(i);             // remove reservation

                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }


}
// *************************************************************************************** //
// *************************************************************************************** //

public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel();  // hotel object
        int x;

        do{

        System.out.println( "*******************************************" +
                "\n          Choose Service's Number:         \n" +
                "1. view all rooms \n" +
                "2. view available rooms \n" +
                "3. book a room \n" +
                "4. cancel reservation \n" +
                "5. view all reservations \n" +
                "6. exit \n"+
                "*******************************************");

        x = input.nextInt();


            switch (x) {

                case 1:
                    hotel.ViewAllRooms();
                    break;

                case 2:
                    hotel.ViewAvailableRooms();
                    break;

                case 3:
                    input.nextLine(); // clear

                    Customer cr = new Customer();

                    System.out.print("Enter name: ");
                    cr.name = input.nextLine();

                    System.out.print("Enter phone number: ");
                    cr.phoneNo = input.nextLine();

                    System.out.print("Enter Id: ");
                    cr.CustomerId = input.nextInt();

                    System.out.print("Enter room number: ");
                    int roomNo = input.nextInt();

                    System.out.print("Enter number of days: ");
                    int days = input.nextInt();

                    hotel.BookRoom(cr, roomNo, days);

                    break;

                case 4:

                    input.nextLine(); // clear

                    System.out.print("Enter your Id to cancel the reservation: ");
                    int Id = input.nextInt();
                    hotel.CancelReservation(Id);

                    break;

                case 5:
                    hotel.ViewReservations();

                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
            while (x != 6);

    }


 }







