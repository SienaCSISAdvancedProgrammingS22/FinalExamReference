import edu.siena.csis225.threadgraphics.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JComponent;

/**
 * The BreakoutBall class is responsible for managing the life
 * of a ball that can bounce off a paddle and hit bricks, as well
 * as bouncing off the walls and ceiling.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
class BreakoutBall extends AnimatedGraphicsObject {

	// delay time between frames of animation (ms)
	public static final int DELAY_TIME = 33;

	// a static Random object shared among all BreakoutBall objects
	private static Random r = new Random();

	// range of possible start speeds in x and y
	public static final double MIN_SPEED = 3;
	public static final double MAX_SPEED = 10;

	// ball size
	public static final int SIZE = 25;

	// pixels to move each iteration
	private double xSpeed, ySpeed;

	// latest location of the ball
	private double upperLeftX, upperLeftY;

	// reference to the paddle
	private BreakoutPaddle paddle;

	// reference to the bricks
	private BrickCollection bricks;

	/**
	 * Construct a new BreakoutBall object, choosing a random location and
	 * speed.
	 * 
	 * @param paddle    the BreakoutPaddle we need to see if we hit
	 * @param container the Swing component in which this ball is being
	 *                  drawn to allow check sizes
	 */
	public BreakoutBall(JComponent container, BrickCollection bricks,
			BreakoutPaddle paddle) {

		super(container);

		// we place the ball initially so its half way up, near the
		// center
		int width = container.getWidth();
		upperLeftX = width / 4 - SIZE / 2 + r.nextInt(width / 2);
		upperLeftY = container.getHeight() / 2;
		xSpeed = r.nextDouble() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED;
		ySpeed = r.nextDouble() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED;
		this.paddle = paddle;
		this.bricks = bricks;
	}

	/**
	 * Draw the ball at its current location.
	 * 
	 * @param g the Graphics object on which the ball should be drawn
	 */
	@Override
	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillOval((int) upperLeftX, (int) upperLeftY, SIZE, SIZE);
	}

	/**
	 * This object's run method, which manages the life of the ball as it
	 * bounces around the screen.
	 */
	@Override
	public void run() {

		done = false;
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

			int xMax = container.getWidth() - SIZE;
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

			// bounce off bricks, for now just reverse y speed
			Point brickHit = bricks.hitBrick((int) upperLeftX,
					(int) upperLeftY, SIZE / 2);
			if (brickHit != null) {
				// if moving up, position at the bottom of the brick we just hit
				if (ySpeed < 0) {
					upperLeftY = brickHit.y + BrickCollection.BRICK_HEIGHT;
				}
				// if moving down, position at the top
				else {
					upperLeftY = brickHit.y;
				}
				ySpeed = -ySpeed;
			}

			// bounce off the paddle
			if (paddle.overlapsBall((int) upperLeftX + SIZE / 2,
					(int) upperLeftY + SIZE / 2, SIZE / 2)) {
				bounced = true;
				ySpeed = -Math.abs(ySpeed);
			}

			// if we've gone off the bottom, we're done
			if (upperLeftY > container.getHeight())
				done = true;

		}
	}
}
