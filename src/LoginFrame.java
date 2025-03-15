/*The code you provided is a Java program that represents a login frame for a user authentication system.
 It utilizes the Swing library for creating the graphical user interface
 */
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
/*The constructor LoginFrame() initializes the login frame. It sets properties such as the title, layout, and dimensions of the frame. It also creates and adds GUI components to the frame, including a background panel with an image, a white box panel for the login form, labels for username and password,
text fields for user input, buttons for login and signup, and event listeners to handle user actions.
 */
class LoginFrame extends JFrame implements ActionListener {
    static ArrayList<User> users = new ArrayList<User>();
    static File file = new File("src/enerio/db/users.txt");

    private Container c;
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField tusername, tpassword;
    private JButton login, signup;
    private JPanel backgroundPanel;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public static SignUpFrame signUpFrame;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        LoadFromFile();

        c = getContentPane();

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = loadImage("src/enerio/imgs/Login.png");
                if (imageIcon != null) {
                    g.drawImage(imageIcon.getImage(), 0, 0, size.width, size.height, this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
        c.add(backgroundPanel);

        RoundedPanel whiteBoxPanel = new RoundedPanel(Color.white);
        whiteBoxPanel.setBounds(size.width / 2 - 150, size.height / 2 - 190, 300, 300);
        whiteBoxPanel.setLayout(null);
        backgroundPanel.add(whiteBoxPanel, 0);

        title = new JLabel("Login");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(size.width / 2 - 50, 150, 200, 30);
        backgroundPanel.add(title);

        int fieldWidth = 200;
        int fieldHeight = 30;
        int centerX = whiteBoxPanel.getWidth() / 2;
        int centerY = whiteBoxPanel.getHeight() / 2;
        int x = 110;
        int y = 20;

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.black);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setBounds(centerX - x, centerY - 7 * y, 100, fieldHeight);
        whiteBoxPanel.add(usernameLabel);

        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 15));
        tusername.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tusername.setBounds(centerX - x, centerY - 5 * y, fieldWidth, fieldHeight);
        whiteBoxPanel.add(tusername);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.black);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(centerX - x, centerY - 2 * y, 100, fieldHeight);
        whiteBoxPanel.add(passwordLabel);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        tpassword.setBounds(centerX - x, centerY, fieldWidth, fieldHeight);
        whiteBoxPanel.add(tpassword);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setBounds(centerX - 130, centerY + 4 * y, 100, fieldHeight);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLACK);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String UN = tusername.getText();
                String PW = tpassword.getText();
                Login(UN, PW);
            }
        });
        whiteBoxPanel.add(login);

        signup = new JButton("Sign up");
        signup.setFont(new Font("Arial", Font.PLAIN, 15));
        signup.setBounds(centerX + 30, centerY + 4 * y, 100, fieldHeight);
        signup.setBackground(Color.WHITE);
        signup.setForeground(Color.BLACK);
        whiteBoxPanel.add(signup);

        pack();
        setPreferredSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("error");
                    setVisible(false);
                    signUpFrame = new SignUpFrame();
                    signUpFrame.setVisible(true);

            }
        });

//        login.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String UN = tusername.getText();
//                String PW = tpassword.getText();
//                Login(UN, PW);
//            }
//        });
        //signup.addActionListener(signUpListener);
        setVisible(true);
        backgroundPanel.setBounds(0, 0, size.width, size.height);
    }

    private ActionListener signUpListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signup) {
                setVisible(false);
                signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
            }
        }
    };

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String def = "";
            tusername.setText(def);
            tpassword.setText(def);
            login.setText(def);
            signup.setText(def);
        }
        if (e.getSource() == signup) {
            setVisible(false);
            new SignUpFrame();
        }
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

    public void PloginSuccessful(Passenger passenger) {
        new PassengerDashBoard(passenger).setVisible(true);
        setVisible(false);
    }

    public void EloginSuccessful(Employee employee) {
        new EmployeeDashboard(employee).setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    public void Login(String UN, String PW) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Passenger) {
                Passenger passenger = (Passenger) users.get(i);
                if (passenger.getUsername().equals(UN) && passenger.getPassword().equals(PW)) {
                    System.out.println("Succes...!");
                    PloginSuccessful(passenger);
                    return;
                }
            } else if (users.get(i) instanceof Employee) {
                Employee employee = (Employee) users.get(i);
                if (employee.getUsername().equals(UN) && employee.getPassword().equals(PW)) {
                    System.out.println("Succes...!");
                    EloginSuccessful(employee);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Username or Password is incorrect");
    }


    public static void LoadFromFile() {
        try {
            FileInputStream file = new FileInputStream("src/enerio/db/users.txt");
            ObjectInputStream out = new ObjectInputStream(file);
            users.clear();

            User user;
            try {
                while ((user = (User) out.readObject()) != null) {
                    users.add(user);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Load File Error, CODE: 404");
            }

            out.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Load File Error, CODE: 404");
        }
    }
}