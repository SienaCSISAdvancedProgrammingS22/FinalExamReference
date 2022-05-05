package example.BallTosser;

import edu.siena.csis225.threadgraphics.*;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 * The BouncingGravityBall class is responsible for managing the life
 * of one ball that starts out with a given x and y speed, moves
 * subject to acceleration due to gravity, until it falls off the
 * bottom of the window.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
class BouncingGravityBall extends AnimatedGraphicsObject {

	// ball size
	public static final int SIZE = 50;

	// delay time between frames of animation (ms)
	public static final int DELAY_TIME = 33;

	// what is slow enough to consider to be stopped?
	public static final double ALMOST_STOPPED = 0.4;

	// what to add to ySpeed to simulate gravity?
	public static final double GRAVITY = 0.3;

	// how much momentum to lose on a bounce
	public static final double DAMPING = 0.9;

	// pixels to move each iteration
	private double xSpeed, ySpeed;

	// latest location of the ball
	private double upperLeftX, upperLeftY;

	// max allowed coordinates of the upper left corner
	private int xMax, yMax;

	/**
	 * Construct a new BouncingGravityBall object.
	 * 
	 * @param startCenter the initial point at which the center of the
	 *                    ball should be drawn
	 * @param xSpeed      initial x speed, pixels per second
	 * @param ySpeed      initial y speed, pixels per second
	 * @param container   the Swing component in which this ball is being
	 *                    drawn to allow it to call that component's repaint method
	 */
	public BouncingGravityBall(Point startCenter,
			double xSpeed, double ySpeed,
			JComponent container) {

		super(container);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		upperLeftX = startCenter.x - SIZE / 2;
		upperLeftY = startCenter.y - SIZE / 2;
		this.yMax = container.getHeight() - SIZE;
		this.xMax = container.getWidth() - SIZE;
	}

	/**
	 * Draw the ball at its current location.
	 * 
	 * @param g the Graphics object on which the ball should be drawn
	 */
	@Override
	public void paint(Graphics g) {

		g.fillOval((int) upperLeftX, (int) upperLeftY, SIZE, SIZE);
	}

	/**
	 * This object's run method, which manages the life of the ball as it
	 * bounces around the screen.
	 */
	@Override
	public void run() {

		while (!done) {

			sleepWithCatch(DELAY_TIME);

			// every iteration, update the coordinates
			// by a pixel
			upperLeftX += xSpeed;
			upperLeftY += ySpeed;

			boolean bounced = false;
			// bounce off the walls
			if (upperLeftX < 0) {
				upperLeftX = 0;
				bounced = true;
				xSpeed = -xSpeed;
			}

			if (upperLeftX > xMax) {
				upperLeftX = xMax;
				bounced = true;
				xSpeed = -xSpeed;
			}

			if (upperLeftY < 0) {
				upperLeftY = 0;
				bounced = true;
				ySpeed = -ySpeed;
			}

			if (upperLeftY > yMax) {
				upperLeftY = yMax;
				bounced = true;
				ySpeed = -ySpeed;
			}

			// if we bounced, we're going to dampen speed in both dimensions
			if (bounced) {
				xSpeed *= DAMPING;
				ySpeed *= DAMPING;
			}

			// if we've almost stopped moving, let's say we're done
			done = (upperLeftY == yMax &&
					Math.abs(ySpeed) < ALMOST_STOPPED &&
					Math.abs(xSpeed) < ALMOST_STOPPED);

			// gravity factor also
			ySpeed += GRAVITY;

			container.repaint();
		}
	}
}
