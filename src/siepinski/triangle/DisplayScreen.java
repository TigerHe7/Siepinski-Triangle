package siepinski.triangle;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Draws a siepinski triangle using coordinates set by user mouse clicks
 *
 * @author Tiger He
 */
public class DisplayScreen extends JPanel {

    // surrounding JFrame
    private TriangleSimulator triangleSimulator;

    // triangle point being set by mouse click
    private int pointTurn = 0;

    // triangle corner points
    int[] x;
    int[] y;

    /**
     * Constructor for instantiating display screen
     *
     * @param triangleSimulator surrounding JFrame to hold panel
     */
    DisplayScreen(TriangleSimulator triangleSimulator) {
        super(); // calls JPanel constructor
        setLayout(null); // no layout manager
        setPreferredSize(triangleSimulator.size); // set panel to be same size as window
        this.triangleSimulator = triangleSimulator;
        init();
    }

    /**
     * Initialize display screen variables
     */
    private void init() {

        // set default tirangle x values
        x = new int[3];
        x[0] = 30;
        x[1] = 450;
        x[2] = 240;

        // set default tirangle y values
        y = new int[3];
        y[0] = 20;
        y[1] = 20;
        y[2] = 450;

        // create mouse listener object
        this.addMouseListener(new MouseListener() {
            /**
             * Do nothing when mouse clicked
             *
             * @param e mouse event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            /**
             * Redefine point of triangle when mouse is pressed
             *
             * @param e mouse event
             */
            @Override
            public void mousePressed(MouseEvent e) {
                // set triangle point coordinate to mouse coordinate
                x[pointTurn] = e.getX();
                y[pointTurn] = e.getY();

                // change triangle point being set next time
                pointTurn++;
                if (pointTurn == 3) {
                    pointTurn = 0;
                }

                // redraw new triangle
                repaint();
            }

            /**
             * Do nothing when mouse button is released
             *
             * @param e mouse event
             */
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            /**
             * Do nothing when mouse enters window
             *
             * @param e mouse event
             */
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            /**
             * Do nothing when mouse exits window
             *
             * @param e mouse event
             */
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /**
     * Display graphics
     *
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        
        // refresh screen
        super.paintComponent(g);

        // draw sierpinski triangle
        sierpinski(g, x, y, 6);

        // draw prompt text to redefine sierpinski triangle 
        g.drawString("Click screen to redefine", 1240, 50);
    }

    /**
     * Draw sierpinski triangle
     *
     * @param g graphics object
     * @param x list of x coordinates for the three corners
     * @param y list of y coordinates for the three corners
     * @param layers depth of triangle detail
     */
    private void sierpinski(Graphics g, int[] x, int[] y, int layers) {
        // draw outline of triangle
        g.drawPolygon(x, y, 3);

        // draw all interior filled triangles
        sierpinskiInner(g, x, y, layers);
    }

    /**
     * Draw an interior sierpinski triangle
     *
     * @param g graphics object
     * @param x list of x coordinates for the three corners
     * @param y list of y coordinates for the three corners
     * @param layers depth of triangle detail
     */
    private void sierpinskiInner(Graphics g, int[] x, int[] y, int layers) {

        // set coordinates for interior filled triangle
        int[] halfX = new int[3];
        halfX[0] = (x[0] + x[1]) / 2;
        halfX[1] = (x[1] + x[2]) / 2;
        halfX[2] = (x[2] + x[0]) / 2;

        int[] halfY = new int[3];
        halfY[0] = (y[0] + y[1]) / 2;
        halfY[1] = (y[1] + y[2]) / 2;
        halfY[2] = (y[2] + y[0]) / 2;

        // draw interior filled triangle
        g.fillPolygon(halfX, halfY, 3);

        // check if depth of detail not reached
        if (layers > 0) {

            // draw first sierpinski
            halfX[2] = x[1];
            halfY[2] = y[1];
            sierpinski(g, halfX, halfY, layers - 1);

            // draw second sierpinski
            halfX[2] = (x[2] + x[0]) / 2;
            halfY[2] = (y[2] + y[0]) / 2;
            halfX[0] = x[2];
            halfY[0] = y[2];
            sierpinski(g, halfX, halfY, layers - 1);

            // draw third sierpinski
            halfX[0] = (x[0] + x[1]) / 2;
            halfY[0] = (y[0] + y[1]) / 2;
            halfX[1] = x[0];
            halfY[1] = y[0];
            sierpinski(g, halfX, halfY, layers - 1);
        }
    }

}
