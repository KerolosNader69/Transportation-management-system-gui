/*The code defines a class called EmployeeDashboard,
which represents an employee dashboard GUI. It utilizes the Swing library for creating the graphical user interface.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeDashboard extends JFrame {
    Employee employee;
    ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeDashboard(Employee employee) {
        super("Employee Dashboard");
        setSize(850, 616);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        this.employee = (Employee) employee;
        addCommponents();

    }

    public void addCommponents() {

        JLabel AccesBasicInfo = new JLabel(loadImage("src/enerio/imgs/AccessBasicInformation.png"));
        JLabel AssinedTrip = new JLabel(loadImage("src/enerio/imgs/AssignedTrip.png"));

        JLabel label2 = new JLabel("@" + employee.getUsername());
        label2.setForeground(Color.white);
        label2.setBounds(100, 150, 271, 60);
        add(label2);

        AccesBasicInfo.setBounds(0, 190, 271, 60);
        AccesBasicInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AccesBasicInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AccesBasicInfo.setIcon(loadImage("src/enerio/imgs/AccessBasicInformationWhenClicked.png"));
                AssinedTrip.setIcon(loadImage("src/enerio/imgs/AssignedTrip.png"));
                employee.accessBasicInfo();
            }
        });
        AssinedTrip.setBounds(0, 280, 271, 60);
        AssinedTrip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AssinedTrip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AccesBasicInfo.setIcon(loadImage("src/enerio/imgs/BasicInformation.png"));
                AssinedTrip.setIcon(loadImage("src/enerio/imgs/AssignedTripClicked.png"));
                employee.viewAssignedTrips();
            }
        });

        if (employee.getType().equals("driver")) {
            add(AssinedTrip);
            add(AccesBasicInfo);

        } else {
            JButton addEmployee = new JButton("Add Employee");
            addEmployee.setBounds(300, 100, 200, 45);
            addEmployee.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Employee.addEmployee();
                }
            });
            add(addEmployee);

            JButton addVehicle = new JButton("Add Vehicle");
            addVehicle.setBounds(300, 150, 200, 45);
            addVehicle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    employee.addVehicle();
                }
            });
            add(addVehicle);

            JButton manageTrip = new JButton("Manage Trip");
            manageTrip.setBounds(300, 200, 200, 45);
            manageTrip.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    employee.manageTrips();
                }
            });
            add(manageTrip);

            JButton assignDriver = new JButton("Assign Driver");
            assignDriver.setBounds(300, 270, 200, 45);
            assignDriver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    employee.assignDriverWithGUI(employee.selectTripFromCurrentTrips());
                }
            });
            add(assignDriver);

            JButton generatrReport = new JButton("Generate Report");
            generatrReport.setBounds(300, 320, 200, 45);
            generatrReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    employee.generateReport();
                }
            });
            add(generatrReport);

        }

        JLabel label = new JLabel(loadImage("src/enerio/imgs/EmployeeOnly.png"));
        label.setBounds(0, 0, 850, 616);
        add(label);
    }

    private ImageIcon loadImage(String resourcePath) {
        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not find resource");
        return null;
    }
}