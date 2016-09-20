package siepinski.triangle;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Draws a siepinski triangle using coordinates set by user mouse clicks
 * 
 * @author Tiger He
 */
public class TriangleSimulator extends JFrame {

    public static Dimension size; // size of the window

    public DisplayScreen displayScreen; // DisplayScreen object

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TriangleSimulator triangleSimulator = new TriangleSimulator();
    }

    /**
     * Constructor for TrangleSimulator
     */
    public TriangleSimulator() {
        super();
        displayScreen = new DisplayScreen(this);

        // set window properties
        super.setTitle("triangle thing");
        size = new Dimension(1440, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // set initial state to log in screen
        setContentPane(displayScreen);
        pack();

    }

}
