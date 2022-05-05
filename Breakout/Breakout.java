import edu.siena.csis225.threadgraphics.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A basic "Breakout" game, with a bouncing ball, paddle, and bricks to
 * hit for points.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class Breakout extends ThreadGraphicsController implements MouseListener {

    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // the paddle
    private BreakoutPaddle paddle;

    // the bricks
    private BrickCollection bricks;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public Breakout() {

        super("Breakout", 800, 800);
    }

    /**
     * Draw the bricks and paddle.
     * 
     * @param g the Graphics object in which to draw
     */
    @Override
    protected void paint(Graphics g) {

        paddle.paint(g);
        bricks.paint(g);
    }

    /**
     * Add the mouse listeners to the panel. Here, we need methods
     * from both MouseListener, as the MouseMotionListener will be
     * the BreakoutPaddle.
     * 
     * @param p the JPanel to which the mouse listeners will be
     *          attached
     */
    @Override
    protected void addListeners(JPanel panel) {

        panel.addMouseListener(this);
    }

    /**
     * Add the panel to the frame, and set up additional components, which
     * here are the BreakoutBall and the BreakoutBricks, also setting up
     * a repaint thread.
     * 
     * @param frame the JFrame to which the panel is added
     *              be added
     * @param panel the JPanel where graphics will be drawn
     */
    protected void buildGUI(JFrame frame, JPanel panel) {

        paddle = new BreakoutPaddle(panel);
        bricks = new BrickCollection(panel);
        frame.add(panel);

        // repaint regularly forever thread
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    AnimatedGraphicsObject.sleepWithCatch(DELAY_TIME);
                    panel.repaint();
                }

            }
        }.start();
    }

    /**
     * Mouse press event handler to
     * 
     * @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        BreakoutBall newBall = new BreakoutBall(panel, bricks, paddle);
        synchronized (lock) {
            list.add(newBall);
        }
        newBall.start();
        panel.repaint();
    }

    // fill in unused methods needed to satify the interfaces, which
    // are needed since we can't use the MouseAdapter, as this class
    // now needs to extend the abstract class
    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public static void main(String args[]) {

        // construct our object and have its run method invoked to
        // set up the GUI once its thread is ready
        javax.swing.SwingUtilities.invokeLater(new Breakout());
    }
}
