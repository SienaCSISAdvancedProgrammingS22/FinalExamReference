import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Poorly-documented tester for the Collision.circleOverlapsSquare method.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
public class CollisionTester extends MouseAdapter implements Runnable {

	public static final int SIZE = 50;

	private int circleX, circleY, squareX, squareY;

	protected JPanel panel;

	private Point lastMouse;

	@Override
	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Test");
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				super.paintComponent(g);

				g.setColor(Color.BLACK);
				g.fillRect(squareX, squareY, SIZE, SIZE);
				if (Collision.circleOverlapsRectangle(circleX + SIZE / 2,
						circleY + SIZE / 2,
						SIZE / 2,
						squareX, squareY,
						SIZE, SIZE)) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.GREEN);
				}
				g.fillOval(circleX, circleY, SIZE, SIZE);
			}
		};
		frame.add(panel);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		frame.pack();
		frame.setVisible(true);

		circleX = 50;
		circleY = 50;
		squareX = 150;
		squareY = 150;
	}

	public void mousePressed(MouseEvent e) {

		lastMouse = null;
		if (e.getPoint().distance(new Point(circleX + SIZE / 2, circleY + SIZE / 2)) <= SIZE) {
			lastMouse = e.getPoint();
		}
	}

	public void mouseDragged(MouseEvent e) {

		if (lastMouse != null) {
			int dx = e.getPoint().x - lastMouse.x;
			int dy = e.getPoint().y - lastMouse.y;
			circleX += dx;
			circleY += dy;
			lastMouse = e.getPoint();
			panel.repaint();
		}
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new CollisionTester());
	}
}
