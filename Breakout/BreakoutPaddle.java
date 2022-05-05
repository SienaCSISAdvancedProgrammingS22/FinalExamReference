import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 * Implementation for the paddle for our breakout game.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
class BreakoutPaddle extends MouseAdapter {

    // dimensions of the paddle
    public static final int PADDLE_WIDTH = 50;
    public static final int PADDLE_HEIGHT = 20;

    // current x-coordinate of the left side of the paddle
    private int paddleX;

    // the JComponent we're drawing in and staying within its bounds
    private JComponent component;

    /**
     * Construct the paddle, and add it as a mouse motion listener
     * on the JComponent in which it lives.
     * 
     * @param c the JComponent in which we will be drawing
     */
    public BreakoutPaddle(JComponent c) {

        component = c;
        component.addMouseMotionListener(this);
    }

    /**
     * Draw the paddle at its current position
     * 
     * @param g the Graphics object in which to draw
     */
    protected void paint(Graphics g) {

        // paddle at its current position
        g.setColor(Color.black);
        g.fillRect(paddleX, component.getHeight() - 2 * PADDLE_HEIGHT,
                PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    /**
     * Mouse move event handler to control the paddle
     * 
     * @param e mouse event info
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        paddleX = e.getPoint().x;
        if (paddleX > component.getWidth() - PADDLE_WIDTH) {
            paddleX = component.getWidth() - PADDLE_WIDTH;
        }

    }

    /**
     * Check if the paddle overlaps the ball, as defined by its center
     * and radius.
     * 
     * @param ballX x-coordinate of the center of the ball
     * @param ballY y-coordinate of the center of the ball
     * @param ballR radius of the ball
     * @return true if the paddle overlaps the bal
     */
    public boolean overlapsBall(int ballX, int ballY, int ballR) {

        return Collision.circleOverlapsRectangle(ballX, ballY, ballR,
                paddleX,
                component.getHeight() - 2 * PADDLE_HEIGHT,
                PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
