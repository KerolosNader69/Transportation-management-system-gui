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

public class PassengerDashBoard extends JFrame {
    Passenger user ;
    public PassengerDashBoard(User user) {
        super("Login");
        setSize(850, 558);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        this.user=(Passenger)user;
        addCommponents();

    }
    /*Instance Variables:
user: Represents the Passenger object associated with the dashboard.

addComponents() method:
This method adds various GUI components, such as labels, buttons, and images, to the frame.
It creates and configures labels to display the passenger's username, name, reserved tickets count, and ID.
It creates a button for canceling a reserved trip. When clicked, it calls the cancelReservedTrip() method of the passenger user and updates the reserved tickets count label.
It creates three labels representing different sections of the dashboard: "Dashboard," "Reserved," and "Book Now." These labels are clickable and change their appearance when clicked.
The Reserved label, when clicked, calls the reviewTickets() method of the passenger user to display the reserved tickets.
The Book Now label, when clicked, calls the selectTripFromCurrentTrips() method of the passenger user to allow the user to select and book a trip from the available trips.
It creates a label to display a welcome message and sets an image as the background for the frame.

loadImage(String resourcePath) method:
This method loads an image from the provided resource path and returns it as an ImageIcon object.
It uses the ImageIO class from the javax.imageio package to read the image file.
If the image file is not found or an error occurs during reading, it prints an error message and returns null.
     */
    public void addCommponents(){
        JLabel username = new JLabel("@"+user.getUsername());
        username.setBounds(75,110,226,53);
        username.setForeground(Color.white);
        username.setFont(new Font("GUI",Font.BOLD,24));
        add(username);

        JLabel Name = new JLabel("Name : " + user.getName());
        Passenger.loadReserved();
        JLabel RservedTickets = new JLabel("Reserved Tickets : " + Passenger.Reserved.size());
        JLabel ID = new JLabel("ID: "+user.getID());

        Name.setBounds(350,80,200,100);
        Name.setForeground(Color.white);
        Name.setFont(new Font("GUI",Font.BOLD,24));
        Name.setVisible(false);
        add(Name);

        ID.setBounds(350,110,200,100);
        ID.setForeground(Color.white);
        ID.setFont(new Font("GUI",Font.BOLD,24));
        ID.setVisible(false);
        add(ID);

        JButton button2= new JButton("Cancel Trip");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.cancelReservedTrip();
                RservedTickets.setText("Reserved Tickets : " + Passenger.Reserved.size());
            }
        });
        button2.setBounds(350,200,100,25);
        add(button2);


        RservedTickets.setBounds(350,140,300,100);
        RservedTickets.setForeground(Color.white);
        RservedTickets.setFont(new Font("GUI",Font.BOLD,24));
        RservedTickets.setVisible(false);
        add(RservedTickets);


        JLabel Book = new JLabel(loadImage("src/enerio/imgs/BookNowButton.png"));
        JLabel dashBoard = new JLabel(loadImage("src/enerio/imgs/PanelClicked.png"));
        JLabel Reserved = new JLabel(loadImage("src/enerio/imgs/Reservation.png"));

        dashBoard.setBounds(0,160,226,53);
        dashBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashBoard.setIcon(loadImage("src/enerio/imgs/PanelClicked.png"));
                Reserved.setIcon(loadImage("src/enerio/imgs/Reservation.png"));
                Book.setIcon(loadImage("src/enerio/imgs/BookNowButton.png"));
                RservedTickets.setVisible(true);
                ID.setVisible(true);
                Name.setVisible(true);
            }
        });
        add(dashBoard);

        Reserved.setBounds(0,220,226,53);
        Reserved.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Reserved.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Reserved.setIcon(loadImage("src/enerio/imgs/ReservationClicked.png"));
                dashBoard.setIcon(loadImage("src/enerio/imgs/PanelClicked.png"));
                Book.setIcon(loadImage("src/enerio/imgs/BookNowButton.png"));
                RservedTickets.setVisible(true);
                ID.setVisible(true);
                Name.setVisible(true);
                user.reviewTickets();

            }
        });
        add(Reserved);

        Book.setBounds(0,275,226,53);
        Book.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Book.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Book.setIcon(loadImage("src/enerio/imgs/BookClicked.png"));
                Reserved.setIcon(loadImage("src/enerio/imgs/Reservation.png"));
                dashBoard.setIcon(loadImage("src/enerio/imgs/PanelClicked.png"));
                RservedTickets.setVisible(true);
                ID.setVisible(true);
                Name.setVisible(true);
                user.selectTripFromCurrentTrips(Trip.trips);
                RservedTickets.setText("Reserved Tickets : " + Passenger.Reserved.size());
            }
        });
        add(Book);

        JLabel label = new JLabel(loadImage("src/enerio/imgs/WelcomePassenger.png"));
        label.setBounds(0,0,850,558);
        add(label);


    }
    private ImageIcon loadImage(String resourcePath){
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Could not find resource");
        return null;
    }
}

