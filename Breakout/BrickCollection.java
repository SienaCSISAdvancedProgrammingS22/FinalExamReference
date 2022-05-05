import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 * The set of bricks that the ball can hit in Breakout.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class BrickCollection {

    // dimensions of the array of bricks and brick heights, positions
    public static final int BRICKS_PER_ROW = 20;
    public static final int NUM_ROWS = 6;
    public static final int BRICK_HEIGHT = 20;
    public static final int TOP_ROW_OFFSET = 75;

    // which bricks are still in existence?
    private boolean[][] bricks;

    // some colors to use to define our rows of bricks
    private static final Color[] colors = { Color.RED, Color.ORANGE,
            Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.MAGENTA };

    // JComponent we'll live in, need for sizes
    private JComponent component;

    /**
     * Construct a BrickCollection on the given JComponent
     * 
     * @param bricksPerRow the number of bricks per row
     * @param numRows      the number of rows of bricks
     * @param brickHeight  the height (in pixels) of a row of bricks
     * @param yPos         the y-coordinate of the topmost row of bricks
     * @param xMin         the x-coordinate of the leftmost brick
     * @param width        the width of the playing area to be filled with bricks
     * @param canvas       the DrawingCanvas on which to place the bricks
     */
    public BrickCollection(JComponent c) {

        // construct the array
        bricks = new boolean[NUM_ROWS][BRICKS_PER_ROW];

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < BRICKS_PER_ROW; col++) {
                bricks[row][col] = true;
            }
        }
        component = c;
    }

    /**
     * Paint the current collection of bricks on the component.
     * 
     * @param g the Graphics object on which to draw
     */
    public void paint(Graphics g) {

        int rowTop = TOP_ROW_OFFSET;
        int brickWidth = component.getWidth() / BRICKS_PER_ROW;
        for (int row = 0; row < NUM_ROWS; row++) {
            g.setColor(colors[row]);
            int nextBrickX = 0;
            for (int col = 0; col < BRICKS_PER_ROW; col++) {
                // only draw bricks that have not been destroyed
                if (bricks[row][col]) {
                    g.fillRect(nextBrickX, rowTop, brickWidth, BRICK_HEIGHT);
                }
                nextBrickX += brickWidth;
            }
            rowTop += BRICK_HEIGHT;
        }

    }

    /**
     * Check if the given ball overlaps any brick, if so, remove it
     * and return true, otherwise return false.
     * 
     * @param ballX the x-coordinate of the BreakoutBall that is to be
     *              checked for overlap with the existing bricks
     * @param ballY the y-coordinate of the BreakoutBall that is to be
     *              checked for overlap with the existing bricks
     * @param ballR the radius of the BreakoutBall that is to be
     *              checked for overlap with the existing bricks
     * 
     * 
     * @return the upper left coordinates of a brick that was hit, or null
     *         if no brick was hit
     */
    public synchronized Point hitBrick(int ballX, int ballY, int ballR) {

        int rowTop = TOP_ROW_OFFSET;
        int brickWidth = component.getWidth() / BRICKS_PER_ROW;
        for (int row = 0; row < NUM_ROWS; row++) {
            int nextBrickX = 0;
            for (int col = 0; col < BRICKS_PER_ROW; col++) {
                if (bricks[row][col] &&
                        Collision.circleOverlapsRectangle(ballX, ballY, ballR,
                                nextBrickX, rowTop,
                                brickWidth,
                                BRICK_HEIGHT)) {
                    bricks[row][col] = false;
                    return new Point(nextBrickX, rowTop);
                }
                nextBrickX += brickWidth;
            }
            rowTop += BRICK_HEIGHT;
        }

        return null;
    }
}
