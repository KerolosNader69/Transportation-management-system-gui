//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
/*Trip has-a driver which is an instance of the Employee class.
Trip has-a vehicle which is likely an instance of the Vehicle

The trips variable is a static attribute of the Trip class.
 It holds a list of Trip objects representing all available trips.
*/



public class Trip implements Serializable {
    private int id;
    private String type;
    private String Source;
    private String destination;
    private String tripType;
    private int stops;
    private int availableSeats;
    private double price;
    private Employee driver;
    private Vehicle vehicle;
    static ArrayList<Trip> trips = new ArrayList();

    public Trip(int id, String type, String Source, String destination, String tripType, int stops, int availableSeats, double price, Vehicle vehicle, Employee driver) {
        this.id = id;
        this.type = type;
        this.Source = Source;
        this.destination = destination;
        this.tripType = tripType;
        this.stops = stops;
        this.availableSeats = availableSeats;
        this.price = price;
        this.vehicle = vehicle;
    }

    public static void addTrip() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Trip ID:"));
        String type = JOptionPane.showInputDialog("Enter Type:");
        String source = JOptionPane.showInputDialog("Enter Source:");
        String destination = JOptionPane.showInputDialog("Enter Destination:");
        String tripType = JOptionPane.showInputDialog("Enter Trip Type:");
        int stops = Integer.parseInt(JOptionPane.showInputDialog("Enter Stops:"));
        int availableSeats = Integer.parseInt(JOptionPane.showInputDialog("Enter Available Seats:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));
        Trip newTrip = new Trip(id, type, source, destination, tripType, stops, availableSeats, price, (Vehicle)null, new Employee(null, null, "", null, "Driver"));
        trips.add(newTrip);
        saveTripsToFile();
        JOptionPane.showMessageDialog((Component)null, "Trip added successfully.");
    }

    public static void removeTrip() {
        if (trips.isEmpty()) {
            JOptionPane.showMessageDialog((Component)null, "No trips available to remove.", "Remove Trip", 1);
        } else {
            loadTripsFromFile();
            StringBuilder tripList = new StringBuilder("Trips Available for Removal:\n");
            Iterator var1 = trips.iterator();

            while(var1.hasNext()) {
                Trip trip = (Trip)var1.next();
                tripList.append("Trip ID: ").append(trip.getId()).append("\n");
            }

            String selectedTripIdString = JOptionPane.showInputDialog((Component)null, tripList.toString(), "Choose a Trip to Remove", -1);
            if (selectedTripIdString != null && !selectedTripIdString.isEmpty()) {
                try {
                    int selectedTripId = Integer.parseInt(selectedTripIdString);
                    Trip selectedTrip = null;
                    Iterator var4 = trips.iterator();

                    while(var4.hasNext()) {
                        Trip trip = (Trip)var4.next();
                        if (trip.getId() == selectedTripId) {
                            selectedTrip = trip;
                            break;
                        }
                    }

                    if (selectedTrip != null) {
                        trips.remove(selectedTrip);
                        saveTripsToFile();
                        JOptionPane.showMessageDialog((Component)null, "Trip removed successfully.", "Remove Trip", 1);
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "Invalid trip ID.", "Remove Trip", 0);
                    }
                } catch (NumberFormatException var6) {
                    JOptionPane.showMessageDialog((Component)null, "Invalid input. Please enter a valid trip ID.", "Remove Trip", 0);
                }
            }

        }
    }

    public void assignDriver(Employee driver) {
        this.driver = driver;
        Employee.assignedTrips.add(new Trip(this.id, this.type, this.Source, this.destination, this.tripType, this.stops, this.availableSeats, this.price, this.vehicle, new Employee(null, null, "", null, "Driver")));
    }

    public String toString() {
        return "Trip{id" + this.id + "type='" + this.type + "', Source='" + this.Source + "', destination='" + this.destination + "', tripType='" + this.tripType + "', stops=" + this.stops + ", availableSeats=" + this.availableSeats + ", price=" + this.price + "}";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return this.Source;
    }

    public void setSource(String source) {
        this.Source = source;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripType() {
        return this.tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public int getStops() {
        return this.stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void saveTripsToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("trips.txt"));

            try {
                oos.writeObject(trips);
                System.out.println("Trips saved successfully.");
            } catch (Throwable var4) {
                try {
                    oos.close();
                } catch (Throwable var3) {
                    var4.addSuppressed(var3);
                }

                throw var4;
            }

            oos.close();
        } catch (IOException var5) {
            var5.printStackTrace();
            System.err.println("Error saving trips: " + var5.getMessage());
        }

    }

    public static void loadTripsFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("trips.txt"));

            try {
                trips = (ArrayList)ois.readObject();
                System.out.println("Trips loaded successfully.");
            } catch (Throwable var4) {
                try {
                    ois.close();
                } catch (Throwable var3) {
                    var4.addSuppressed(var3);
                }

                throw var4;
            }

            ois.close();
        } catch (ClassNotFoundException | IOException var5) {
            System.err.println("Error loading trips: " + var5.getMessage());
        }

    }
}
