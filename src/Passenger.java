import java.io.*;
import java.util.*;
import javax.swing.*;

public class Passenger extends User {
    @SuppressWarnings("unused")
    private String name, ID, tripDetails, ticketType;
    private static int selectedTripNumber;
    static ArrayList<String> Reserved = new ArrayList<>();
    static final String RESERVATION_FILE = "Reserved.txt";
/*The constructor Passenger() initializes the passenger object with the provided username, password, name, ID, ticket type,
 and trip details. It also calls the constructor of the superclass User to set the username, password, and ID.

 selectTripFromCurrentTrips() method:
This method takes an ArrayList of Trip objects as a parameter representing the available trips.
It displays a dialog box to the user with a list of available trips and prompts the user to select a trip by entering the corresponding trip number.
The method validates the user input and then calls the bookTicket() method to book the selected trip.

bookTicket() method:
This method takes a Trip object representing the selected trip as a parameter.
It checks if there are enough available seats for the trip. If so, it reduces the number of available seats, adds the trip details to the Reserved list, and displays a success message to the user.
It also calculates the total price for the tickets and displays it to the user.

reviewTickets() method:
This method loads the reserved trips from the file and displays them to the user using a dialog box.

cancelReservedTrip() method:
This method allows the user to cancel a reserved trip.
It prompts the user to select a trip to cancel and removes it from the Reserved list.
It then saves the updated list of reserved trips to the file.

saveReservedTrips() method:
This method saves the list of reserved trips to a file using serialization.

loadReserved() method:
This method loads the list of reserved trips from the file using deserialization.


Getter methods:
getID(): Returns the ID of the passenger.
getName(): Returns the name of the passenger.
 */
    public Passenger(String username, String Password, String name, String ID, String tickettype, String tripDetails) {
        super(username, Password, ID);
        this.name = name;
        this.ID = ID;
        this.ticketType = tickettype;
        this.tripDetails = tripDetails;
    }

    public void selectTripFromCurrentTrips(ArrayList<Trip> availableTrips) {

        // Create a list of trip descriptions for the user to choose from
        StringBuilder tripList = new StringBuilder("Available Trips:\n");
        for (int i = 0; i < availableTrips.size(); i++) {
            tripList.append(i + 1).append(". ").append(availableTrips.get(i)).append("\n");
        }

        // Show a dialog to the user to choose a trip
        String selectedTripNumberString = JOptionPane.showInputDialog(null,
                tripList.toString(), "Choose a Trip to Book",
                JOptionPane.PLAIN_MESSAGE);

        // Check if the user canceled or entered invalid input
        if (selectedTripNumberString == null || selectedTripNumberString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No trip selected.");
            return;
        }

        // Parse the selected trip number
        try {
            selectedTripNumber = Integer.parseInt(selectedTripNumberString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return;
        }

        // Validate the selected trip number
        if (selectedTripNumber < 1 || selectedTripNumber > availableTrips.size()) {
            JOptionPane.showMessageDialog(null, "Invalid trip number.");
            return;
        }

        // Get the selected trip
        Trip selectedTrip = availableTrips.get(selectedTripNumber - 1);

        // Proceed to book the selected trip
        bookTicket(selectedTrip);
    }

    public void bookTicket(Trip selectedTrip) {
        int numberOfTickets = 1;
        if (selectedTrip.getAvailableSeats() >= numberOfTickets) {
            selectedTrip.setAvailableSeats(selectedTrip.getAvailableSeats() - numberOfTickets);
            String tripDetails = selectedTrip.toString();
            Reserved.add(tripDetails);
            JOptionPane.showMessageDialog(null,
                    "You have successfully booked a ticket for the following trip:\n" + tripDetails);
            saveReservedTrips();
            double totalPrice = selectedTrip.getPrice() * numberOfTickets;
            JOptionPane.showMessageDialog(null,
                    "The total price for " + numberOfTickets + " tickets is: $" + totalPrice);
        } else {
            JOptionPane.showMessageDialog(null, "There are not enough available seats for this trip.");
        }
    }

    public void reviewTickets() {
        loadReserved();
        // Display the list of Reserved tickets
        StringBuilder message = new StringBuilder("Your Reserved tickets are:\n");
        for (String reservation : Reserved) {
            message.append(reservation).append("\n");
        }
        JOptionPane.showMessageDialog(null, message);
    }

    public void cancelReservedTrip() {
        if (Reserved.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven't reserved any trips yet.");
            return;
        }

        StringBuilder tripList = new StringBuilder("Your reserved trips:\n");
        for (int i = 0; i < Reserved.size(); i++) {
            tripList.append(i + 1).append(". ").append(Reserved.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(null,
                tripList.toString() + "\nEnter the number of the trip you want to cancel:");
        if (input == null) {
            // User canceled
            return;
        }

        int tripNumber;
        try {
            tripNumber = Integer.parseInt(input);
            if (tripNumber < 1 || tripNumber > Reserved.size()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid trip number.");
            return;
        }

        Reserved.remove(tripNumber - 1);
        saveReservedTrips();
        JOptionPane.showMessageDialog(null, "Trip successfully canceled:");
    }

    private void saveReservedTrips() {
        try (FileOutputStream file = new FileOutputStream(RESERVATION_FILE);
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(Reserved);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save Reserved trips.");
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadReserved() {
        try (FileInputStream fileInputStream = new FileInputStream(RESERVATION_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Reserved = (ArrayList<String>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Reserved = new ArrayList<>();
        }
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
