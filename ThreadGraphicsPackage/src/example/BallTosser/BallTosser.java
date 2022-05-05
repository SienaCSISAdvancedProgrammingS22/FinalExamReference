package example.BallTosser;

import edu.siena.csis225.threadgraphics.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * A program to demonstrate a simple animation of a ball that is launched in
 * a given direction, and then is subject to a gravitational pull as it
 * bounces off the sides of the window.
 * 
 * This version uses the ThreadGraphicsController and
 * AnimatedGraphicsObject classes from the
 * edu.siena.csis225.threadgraphics package.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class BallTosser extends ThreadGraphicsController implements MouseListener, MouseMotionListener {

    // multiplier to convert the press/release distances to initial
    // speeds in the x and y directions
    public static final double SLING_FACTOR = 0.25;

    // press/drag points for launching, and if we are dragging
    private boolean dragging;
    private Point pressPoint;
    private Point dragPoint;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public BallTosser() {

        super("BallTosser", 500, 500);
    }

    /**
     * Draw the sling line when the mouse is being dragged.
     * 
     * @param g the Graphics object in which to draw
     */
    @Override
    protected void paint(Graphics g) {

        // if we are currently dragging, draw a sling line
        if (dragging) {
            g.drawLine(pressPoint.x, pressPoint.y,
                    dragPoint.x, dragPoint.y);
        }

    }

    /**
     * Add the mouse listeners to the panel. Here, we need methods
     * from both MouseListener and MouseMotionListener.
     * 
     * @param p the JPanel to which the mouse listeners will be
     *          attached
     */
    @Override
    protected void addListeners(JPanel panel) {

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    /**
     * Mouse press event handler to set up to create a new
     * BouncingGravityBall on subsequent release.
     * 
     * @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        pressPoint = e.getPoint();
        panel.repaint();
    }

    /**
     * Mouse drag event handler to create remember the current point
     * for sling line drawing.
     * 
     * @param e mouse event info
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        dragPoint = e.getPoint();
        dragging = true;
        panel.repaint();
    }

    /**
     * Mouse release event handler to create a new BouncingGravityBall
     * centered at the release point, initial velocities depending on
     * distance from press point.
     * 
     * @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        BouncingGravityBall newBall = new BouncingGravityBall(e.getPoint(),
                SLING_FACTOR * (pressPoint.x - e.getPoint().x),
                SLING_FACTOR * (pressPoint.y - e.getPoint().y),
                panel);
        synchronized (lock) {
            list.add(newBall);
        }

        newBall.start();
        dragging = false;
        panel.repaint();
    }

    // fill in unused methods needed to satify the interfaces, which
    // are needed since we can't use the MouseAdapter, as this class
    // now needs to extend the abstract class
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public static void main(String args[]) {

        // construct our object and have its run method invoked to
        // set up the GUI once its thread is ready
        javax.swing.SwingUtilities.invokeLater(new BallTosser());
    }
}
