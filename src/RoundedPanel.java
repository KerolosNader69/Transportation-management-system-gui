import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
/*Constructor:
The constructor RoundedPanel(Color color) creates a rounded panel with the specified background color.
It calls the setOpaque(false) method to make the panel transparent.
It also sets the background color using the setBackground(color) method.

paintComponent(Graphics g) method:
This method is overridden from the JPanel class and is responsible for painting the component.
It first calls the super.paintComponent(g) method to ensure that the parent's painting is performed.
It then casts the Graphics object to Graphics2D to access advanced 2D drawing capabilities.
It sets the color of the graphics object to the panel's background color using g2d.setColor(getBackground()).
It uses the Graphics2D object to fill a rounded rectangle shape using the fill() method.
The rounded rectangle is created using the RoundRectangle2D.Double class, specifying the dimensions and corner radius.
The dimensions of the rounded rectangle are based on the panel's width and height (getWidth() - 1 and getHeight() - 1).
The corner radius is set to 50 in this example, but it can be adjusted as needed.

By using this RoundedPanel class instead of a regular JPanel,
 you can create panels with rounded corners, which can be visually appealing in certain GUI designs.
 */
class RoundedPanel extends JPanel {

    public RoundedPanel(Color color) {
        setOpaque(false);
        setBackground(color);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 50, 50));
    }
}