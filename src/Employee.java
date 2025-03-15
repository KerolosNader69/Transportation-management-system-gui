/*this code defines a class called "Employee" that represents an employee in a system. This class
inherits from the "User" class, which means it contains the variables and methods inherited from the base class.
 */
import java.awt.*;

import javax.swing.*;
import java.util.ArrayList;

public class Employee extends User {
    private String name, ID;
    private static String type; // "driver" or "manager"
    static ArrayList<Trip> assignedTrips;
    static ArrayList<Employee> employees = new ArrayList<>();
/*METHODS : CONSTRUCTOR ; Used to create an object of the class
 It takes a set of parameters such as username, password, employee name, ID number, and employee type. It uses the base class "User" constructor to initialize its own variables.*/

    @SuppressWarnings("static-access")
    public Employee(String username, String Password, String name, String ID, String type) {
        super(username, Password, ID);
        this.name = name;
        this.ID = ID;
        this.type = type;
    }

    public void viewAssignedTrips() {
        if (assignedTrips != null && !assignedTrips.isEmpty()) {
            JPanel tripPanel = new JPanel();
            tripPanel.setLayout(new GridLayout(assignedTrips.size(), 1));

            for (Trip trip : assignedTrips) {
                JLabel tripLabel = new JLabel("<html><b>Trip ID:</b> " + trip.getId() + "<br>" +
                        "<b>Type:</b> " + trip.getType() + "<br>" +
                        "<b>From:</b> " + trip.getSource() + "<br>" +
                        "<b>To:</b> " + trip.getDestination() + "<br>" +
                        "<b>Trip Type:</b> " + trip.getTripType() + "<br>" +
                        "<b>Stops:</b> " + trip.getStops() + "<br>" +
                        "<b>Available Seats:</b> " + trip.getAvailableSeats() + "<br>" +
                        "<b>Price:</b> " + trip.getPrice() + "</html>");
                tripPanel.add(tripLabel);
            }

            JScrollPane scrollPane = new JScrollPane(tripPanel);
            JFrame frame = new JFrame("Assigned Trips");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No assigned trips.", "Assigned Trips",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void manageTrips() {
        if (type.equals("manager")) {
            String[] options = { "Add Trip", "Cancel Trip" };
            int choice = JOptionPane.showOptionDialog(null, "Choose an action:", "Manage Trips",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addTrip();
                    break;
                case 1:
                    cancelTrip();
                    break;
                default:
                    // Do nothing or handle other cases
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Only managers can manage trips.", "Manage Trips",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addTrip() {
        Trip.addTrip();
    }

    private void cancelTrip() {
        Trip.removeTrip();
    }

    @SuppressWarnings("static-access")
    public void addVehicle() {
        if (this.type.equals("manager")) {
            // GUI implementation for adding a vehicle
            JTextField typeField = new JTextField();
            JTextField capacityField = new JTextField();
            JTextField licensePlateField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Type:"));
            panel.add(typeField);
            panel.add(new JLabel("Capacity:"));
            panel.add(capacityField);
            panel.add(new JLabel("License Plate:"));
            panel.add(licensePlateField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Vehicle",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String type = typeField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                String licensePlate = licensePlateField.getText();
                Vehicle vehicle = new Vehicle(type, capacity, licensePlate);
                Vehicle.vehicles.add(vehicle);
                Vehicle.SaveToFile();
                JOptionPane.showMessageDialog(null, "Vehicle added successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Only managers can add vehicles.");
        }
    }

    public void assignDriverWithGUI(Trip trip) {
        if (type.equals("manager")) {
            // Create a list of available drivers
            ArrayList<Employee> availableDrivers = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getType().equals("driver")) {
                    availableDrivers.add(employee);
                }
            }

            if (availableDrivers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No drivers available.", "Assign Driver",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Create a combo box model for drivers
            DefaultComboBoxModel<Employee> driverComboBoxModel = new DefaultComboBoxModel<>();
            for (Employee driver : availableDrivers) {
                driverComboBoxModel.addElement(driver);
            }

            // Create the combo box
            JComboBox<Employee> driverComboBox = new JComboBox<>(driverComboBoxModel);

            // Create the panel to hold the combo box
            JPanel panel = new JPanel();
            panel.add(new JLabel("Select a Driver:"));
            panel.add(driverComboBox);

            // Show the dialog
            int result = JOptionPane.showConfirmDialog(null, panel, "Assign Driver", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Get the selected driver
                Employee selectedDriver = (Employee) driverComboBox.getSelectedItem();

                // Assign the driver to the trip
                trip.assignDriver(selectedDriver);

                // Display success message
                JOptionPane.showMessageDialog(null, "Driver assigned successfully.", "Assign Driver",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Only managers can assign drivers.", "Assign Driver",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addEmployee() {
        if (type.equals("manager")) {
            JTextField nameField = new JTextField();
            JTextField idField = new JTextField();
            JTextField typeField = new JTextField();
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("ID:"));
            panel.add(idField);
            panel.add(new JLabel("Type:"));
            panel.add(typeField);
            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Employee",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                int id = Integer.parseInt(idField.getText());
                String employeeType = typeField.getText();
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                Employee newEmployee = new Employee(username, password, name, String.valueOf(id), employeeType);
                employees.add(newEmployee);

                JOptionPane.showMessageDialog(null, "Employee added successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Only managers can add employees.");
        }
    }

    public Trip selectTripFromCurrentTrips() {
        // Check if there are any trips available
        if (Trip.trips.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No trips available.", "Select Trip", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        // Create a combo box model for trips
        DefaultComboBoxModel<Trip> tripComboBoxModel = new DefaultComboBoxModel<>();
        for (Trip trip : Trip.trips) {
            tripComboBoxModel.addElement(trip);
        }

        JComboBox<Trip> tripComboBox = new JComboBox<>(tripComboBoxModel);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a Trip:"));
        panel.add(tripComboBox);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Select Trip", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Get the selected trip
            Trip selectedTrip = (Trip) tripComboBox.getSelectedItem();
            return selectedTrip;
        }

        return null; // Return null if the user cancels the selection
    }

    public void generateReport() {
        // Count the number of passengers
        int passengerCount = 0;
        for (User user : LoginFrame.users) {
            if (user instanceof Passenger) {
                passengerCount++;
            }
        }

        // Count the number of employees
        int employeeCount = 0;
        for (User user : LoginFrame.users) {
            if (user instanceof Employee) {
                employeeCount++;
            }
        }

        // Count the number of trips
        int tripCount = Trip.trips.size();

        // Display the report
        JOptionPane.showMessageDialog(null,
                "Number of Passengers: " + passengerCount + "\n" +
                        "Number of Employees: " + employeeCount + "\n" +
                        "Number of Trips: " + tripCount,
                "Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public void accessBasicInfo() {
        if (Trip.trips != null && !Trip.trips.isEmpty()) {
            // Create a panel to hold trip information
            JPanel tripPanel = new JPanel();
            tripPanel.setLayout(new GridLayout(Trip.trips.size(), 1));

            // Iterate over trips and add information to the panel
            for (Trip trip : Trip.trips) {
                JLabel tripLabel = new JLabel("<html><b>Trip ID:</b> " + trip.getId() + "<br>" +
                        "<b>Type:</b> " + trip.getType() + "<br>" +
                        "<b>From:</b> " + trip.getSource() + "<br>" +
                        "<b>To:</b> " + trip.getDestination() + "<br>" +
                        "<b>Trip Type:</b> " + trip.getTripType() + "<br>" +
                        "<b>Stops:</b> " + trip.getStops() + "<br>" +
                        "<b>Available Seats:</b> " + trip.getAvailableSeats() + "<br>" +
                        "<b>Price:</b> " + trip.getPrice() + "</html>");
                tripPanel.add(tripLabel);
            }

            // Create a scroll pane to accommodate a large number of trips
            JScrollPane scrollPane = new JScrollPane(tripPanel);

            // Create and configure the dialog
            JFrame frame = new JFrame("Basic Information about Trips");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the dialog
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No trips available.", "Basic Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    @SuppressWarnings("static-access")
    public String getType() {
        return this.type;
    }

    @SuppressWarnings("static-access")
    public void setType(String type) {
        this.type = type;
    }

}
