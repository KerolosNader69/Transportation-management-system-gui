
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
/*SignUpFrame has an association with RoundedPanel.
 An instance of RoundedPanel (GrayBoxPanel) is created and added to the SignUpFrame.

 SignUpFrame has an association with classes like Passenger and Employee through the methods SignUpPassenger and SignUpEmployee.

 SignUpFrame has an association with LoginFrame. It creates a new instance of LoginFrame when the login button is clicked.

 LoginFrame likely has an aggregation relationship with Passenger and Employee.
 It potentially holds a collection of Passenger and Employee objects representing users.

 The code utilizes inheritance by extending JFrame for the SignUpFrame class.
The code demonstrates polymorphism through the ActionListener interface.
 Different functionalities are attached to the login button depending on the button clicked.
 */
class SignUpFrame extends JFrame {
    static File file = new File("src/enerio/db/users.txt");

    private Container c;
    private JLabel title, usernameLabel, passwordLabel, nameLabel, idLabel, typeLabel, etypeLabel, ptypeLabel,
            loginLabel, tripLabel;
    private JTextField tusername, tpassword, tname, tid, TDetails;
    private JComboBox<String> Dtype, Etype, Ttype;
    private JButton SignUp, login;
    private JPanel backgroundPanel;
    private RoundedPanel GrayBoxPanel;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    String[] DropDownType = { "Passenger", "Employee" };
    String[] TicketType = { "one-way", "round-trip" };
    String[] EmployeeType = { "manger", "driver" };

    private static LoginFrame loginFrame;

    public SignUpFrame() {
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        c = getContentPane();

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = loadImage("src/enerio/imgs/signup_bg.png");
                if (imageIcon != null) {
                    g.drawImage(imageIcon.getImage(), 0, 0, size.width, size.height, this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
        c.add(backgroundPanel);

        GrayBoxPanel = new RoundedPanel(Color.LIGHT_GRAY);
        GrayBoxPanel.setBounds(size.width / 2 - 7, size.height / 2 - 190, 450, 400);
        GrayBoxPanel.setLayout(null);
        backgroundPanel.add(GrayBoxPanel, 0);

        title = new JLabel("Sign up");
        title.setForeground(Color.black);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(size.width / 2 - 5, 150, 200, 30);
        int off = -470;
        int titleCenterX = size.width - title.getPreferredSize().width + off;
        backgroundPanel.add(title);

        int fieldWidth = 200;
        int fieldHeight = 30;
        int centerX = GrayBoxPanel.getWidth() / 2;
        int centerY = GrayBoxPanel.getHeight() / 2;
        int x = 200;
        int y = 20;

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setBounds(centerX - x, centerY - 9 * y, 100, fieldHeight);
        GrayBoxPanel.add(usernameLabel);

        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 15));
        tusername.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tusername.setBounds(centerX - x, centerY - 7 * y, fieldWidth, fieldHeight);
        GrayBoxPanel.add(tusername);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(centerX - x + 200, centerY - 9 * y, 100, fieldHeight);
        GrayBoxPanel.add(passwordLabel);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tpassword.setBounds(centerX - x + 200, centerY - 7 * y, fieldWidth, fieldHeight);
        GrayBoxPanel.add(tpassword);

        nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(centerX - x, centerY - 5 * y, 100, fieldHeight);
        GrayBoxPanel.add(nameLabel);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tname.setBounds(centerX - x, centerY - 3 * y, fieldWidth * 2, fieldHeight);
        GrayBoxPanel.add(tname);

        typeLabel = new JLabel("Type:");
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        typeLabel.setBounds(centerX - x, centerY - y, 100, fieldHeight);
        GrayBoxPanel.add(typeLabel);

        Dtype = new JComboBox<>(DropDownType);
        Dtype.setBackground(Color.WHITE);
        Dtype.setForeground(Color.BLACK);
        Dtype.setFont(new Font("Arial", Font.PLAIN, 15));
        Dtype.setBounds(centerX - x, centerY + y, fieldWidth - 5, fieldHeight);
        Dtype.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) Dtype.getSelectedItem();
                if (selected.equals("Passenger")) {
                    GrayBoxPanel.setBounds(size.width / 2 - 225, size.height / 2 - 250, 450, 500);
                    title.setBounds(size.width / 2 - 50, 90, 200, 30);
                    SignUp.setBounds(centerX - 75, centerY + 11 * y, 150, fieldHeight);
                    login.setBounds(centerX + 20, centerY + 13 * y, 100, fieldHeight);
                    loginLabel.setBounds(centerX - 110, centerY + 13 * y, 250, fieldHeight);
                    ptypeLabel.setVisible(true);
                    Ttype.setVisible(true);
                    tripLabel.setVisible(true);
                    TDetails.setVisible(true);
                    etypeLabel.setVisible(false);
                    Etype.setVisible(false);
                } else if (selected.equals("Employee")) {
                    GrayBoxPanel.setBounds(size.width / 2 - 225, size.height / 2 - 250, 450, 500);
                    title.setBounds(size.width / 2 - 50, 90, 200, 30);
                    SignUp.setBounds(centerX - 75, centerY + 11 * y, 150, fieldHeight);
                    login.setBounds(centerX + 20, centerY + 13 * y, 100, fieldHeight);
                    loginLabel.setBounds(centerX - 110, centerY + 13 * y, 250, fieldHeight);
                    ptypeLabel.setVisible(false);
                    Ttype.setVisible(false);
                    tripLabel.setVisible(false);
                    TDetails.setVisible(false);
                    etypeLabel.setVisible(true);
                    Etype.setVisible(true);
                }
            }
        });
        GrayBoxPanel.add(Dtype);

        ptypeLabel = new JLabel("Passenger Type:");
        ptypeLabel.setForeground(Color.BLACK);
        ptypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ptypeLabel.setBounds(centerX - x, centerY + 4 * y, fieldWidth, fieldHeight);
        ptypeLabel.setVisible(false);
        GrayBoxPanel.add(ptypeLabel);

        ptypeLabel = new JLabel("Ticket Type:");
        ptypeLabel.setForeground(Color.BLACK);
        ptypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ptypeLabel.setBounds(centerX - x, centerY + 3 * y, fieldWidth, fieldHeight);
        ptypeLabel.setVisible(false);
        GrayBoxPanel.add(ptypeLabel);

        Ttype = new JComboBox<>(TicketType);
        Ttype.setBackground(Color.WHITE);
        Ttype.setForeground(Color.BLACK);
        Ttype.setFont(new Font("Arial", Font.PLAIN, 15));
        Ttype.setBounds(centerX - x, centerY + 5 * y, 2 * fieldWidth, fieldHeight);
        Ttype.setVisible(false);
        GrayBoxPanel.add(Ttype);

        tripLabel = new JLabel("Trip Details:");
        tripLabel.setForeground(Color.BLACK);
        tripLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tripLabel.setBounds(centerX - x, centerY + 7 * y, 2 * fieldWidth, fieldHeight);
        tripLabel.setVisible(false);
        GrayBoxPanel.add(tripLabel);

        TDetails = new JTextField();
        TDetails.setFont(new Font("Arial", Font.PLAIN, 15));
        TDetails.setBounds(centerX - x, centerY + 9 * y, 2 * fieldWidth, fieldHeight);
        TDetails.setVisible(false);
        GrayBoxPanel.add(TDetails);

        etypeLabel = new JLabel("Employee Type:");
        etypeLabel.setForeground(Color.BLACK);
        etypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        etypeLabel.setBounds(centerX - x, centerY + 4 * y, fieldWidth, fieldHeight);
        etypeLabel.setVisible(false);
        GrayBoxPanel.add(etypeLabel);

        Etype = new JComboBox<>(EmployeeType);
        Etype.setBackground(Color.WHITE);
        Etype.setForeground(Color.BLACK);
        Etype.setFont(new Font("Arial", Font.PLAIN, 15));
        Etype.setBounds(centerX - x, centerY + 6 * y, 2 * fieldWidth, fieldHeight);
        Etype.setVisible(false);
        GrayBoxPanel.add(Etype);

        idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idLabel.setBounds(centerX - x + 200, centerY - y, 100, fieldHeight);
        GrayBoxPanel.add(idLabel);

        tid = new JTextField();
        tid.setFont(new Font("Arial", Font.PLAIN, 15));
        tid.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tid.setBounds(centerX - x + 200, centerY + y, fieldWidth, fieldHeight);
        GrayBoxPanel.add(tid);

        SignUp = new JButton("Sign Up");
        SignUp.setFont(new Font("Arial", Font.PLAIN, 15));
        SignUp.setBounds(centerX - 75, centerY + 4 * y, 150, fieldHeight);
        SignUp.setBackground(Color.WHITE);
        SignUp.setForeground(Color.BLACK);
        SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tusername.getText();
                String password = tpassword.getText();
                String name = tname.getText();
                String ut = (String) Dtype.getSelectedItem();
                String id = tid.getText();
                String Ttyp = (String) Ttype.getSelectedItem();
                String tripDetails = TDetails.getText();
                String Etyp = (String) Etype.getSelectedItem();
                if (ut.equals("Passenger")) {
                    SignUpPassenger(username, password, name, id, Ttyp, tripDetails);
                } else if (ut.equals("Employee")) {
                    SignUpEmployee(username, password, name, id, Etyp);
                }
            }
        });
        GrayBoxPanel.add(SignUp);

        loginLabel = new JLabel("Have an account ?");
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        loginLabel.setBounds(centerX - 110, centerY + 7 * y, 250, fieldHeight);
        GrayBoxPanel.add(loginLabel);

        login = new JButton("Log in");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setBounds(centerX + 20, centerY + 7 * y, 100, fieldHeight);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.blue);
        login.setBorderPainted(false);
        GrayBoxPanel.add(login);

        pack();
        setPreferredSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        login.addActionListener(loginListener);
        setVisible(true);
        backgroundPanel.setBounds(0, 0, size.width, size.height);
    }

    private ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                setVisible(false);
                loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        }
    };

    public static void main(String[] args) {
        new SignUpFrame();
    }

    private ImageIcon loadImage(String resourcePath) {
        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void SignUpPassenger(String username, String password, String name, String id, String tickettype,
                                String tripDetails) {
        for (Object obj : LoginFrame.users) {
            if (obj instanceof Passenger && ((Passenger) obj).getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                return;
            }
            if (obj instanceof Passenger && ((Passenger) obj).getId().equals(id)) {
                JOptionPane.showMessageDialog(this, "ID already exists.");
                return;
            }
        }
        Passenger passenger = new Passenger(username, password, name, id, tickettype, tripDetails);
        LoginFrame.users.add(passenger);
        SaveToFile();
        new LoginFrame().setVisible(true);
        setVisible(false);
    }

    public void SignUpEmployee(String username, String password, String name, String id, String empType) {
        for (Object obj : LoginFrame.users) {
            if (obj instanceof Employee && ((Employee) obj).getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                //return;
            }
            if (obj instanceof Employee && ((Employee) obj).getId().equals(id)) {
                JOptionPane.showMessageDialog(this, "ID already exists.");
                //return;
            }
        }
        Employee employee = new Employee(username, password, name, id, empType);
        LoginFrame.users.add(employee);
        SaveToFile();
        new LoginFrame().setVisible(true);
        setVisible(false);
    }

    public static void SaveToFile() {
        try {
            FileOutputStream file = new FileOutputStream("src/enerio/db/users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            for (Object obj : LoginFrame.users) {
                out.writeObject(obj);
            }

            out.close();
            file.close();
        } catch (Exception e) {
            System.out.println("File Create Error, CODE: 0");
        }
    }
}