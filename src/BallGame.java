import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

public class BallGame extends GraphicsProgram {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int BALL_SIZE = 100;
	public static final int BREAK_MS = 30;
	public static final int INIT_X_VELOCITY = 5;

	private GOval ball;
	private int xVelocity;
	private RandomGenerator rgen;

	public void run() {
		rgen = RandomGenerator.getInstance();
		xVelocity = INIT_X_VELOCITY;

		ball = new GOval(WINDOW_WIDTH / 2 - BALL_SIZE / 2, WINDOW_HEIGHT / 2 - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);
		ball.setColor(Color.RED);
		ball.setFilled(true);
		add(ball);

		addMouseListeners();

		animateBall();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	System.out.println("Mouse pressed.");
		if (getElementAt(e.getX(), e.getY()) == ball)
			ball.setColor(rgen.nextColor());
		else
			ball.setLocation(ball.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked.");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered the application window.");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited the application window.");
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("\"mouseMoved\" event was triggered.");
	}

	private void animateBall() {
		while (true) {
			ball.move(xVelocity, 0);
			if (outOfBounds(ball)) {
				xVelocity *= -1;
			}
			pause(BREAK_MS);
		}
	}

	private boolean outOfBounds(GOval o) {
		double x = o.getX();
		return (x < 0 && xVelocity < 0 || x > WINDOW_WIDTH && xVelocity > 0);
	}

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}

	public static void main(String[] args) {
		new BallGame().start();
	}
}