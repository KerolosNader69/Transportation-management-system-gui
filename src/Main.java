public class Main {
    public static void main(String[] args) {
        Trip.trips.add(new Trip(2658, "round", "Alexandria", "Alamein", "rouund", 2, 55, 50,
                new Vehicle("BUS", 50, "4587"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(7846, "round", "benha", "alexandria", "rouund", 1, 14, 140,
                new Vehicle("mini BUS", 50, "1235"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(9854, "round", "ZAGAZIG", "alexandria", "rouund", 1, 4, 400,
                new Vehicle("Lemousine", 50, "5687"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(3897, "round", "aswan", "Alamein", "rouund", 8, 55, 400,
                new Vehicle("bus", 50, "7845"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(9658, "round", "matrouh", "alexandria", "rouund", 3, 55, 150,
                new Vehicle("BUS", 50, "9735"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(9080, "round", "beheira", "aswan", "rouund", 8, 14, 350,
                new Vehicle("mini bus", 50, "8000"), new Employee(null, null, "", null, "Driver")));
        Trip.trips.add(new Trip(1111, "round", "giza", "damnhour", "rouund", 1, 4, 450,
                new Vehicle("Lemousine", 50, "1313"), new Employee(null, null, "", null, "Driver")));
        new LoginFrame().setVisible(true);
    }
}