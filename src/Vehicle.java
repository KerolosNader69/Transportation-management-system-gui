import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/*
Constructor:
Vehicle(String type, int capacity, String licensePlate):
 This constructor initializes a new Vehicle object with the provided type, capacity, and license plate.

Encapsulation: The data members (type, capacity, licensePlate) are private,
and access is controlled through getter and setter methods. This promotes data protection and restricts direct modification.
Static Attribute: The vehicles attribute is static,
meaning there's only one copy of this list shared by all instances of the Vehicle class. This allows centralized storage and management of all created vehicles.
 */
public class Vehicle {
    private String type;
    private int capacity;
    private String licensePlate;
    static ArrayList<Vehicle> vehicles;

    static {
        vehicles = new ArrayList<>();
    }



    public Vehicle(String type, int capacity, String licensePlate) {
        this.type = type;
        this.capacity = capacity;
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public void display() {
        System.out.println("Vehicle Information:");
        System.out.println("Type: " + getType());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("License Plate: " + getLicensePlate());
    }
    public static void SaveToFile(){
        try {
            FileOutputStream file = new FileOutputStream("Vehicles.txt",true);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(vehicles);
            out.close();
            file.close();
        }catch (Exception e){

        }
    }
}