import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Balls extends GraphicsProgram {
	public static final int NUM_BALLS = 2;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int BALL_SIZE = 100;
	public static final int BREAK_MS = 30;
	public static final int INIT_X_VELOCITY = 5;

	private ArrayList<GOval> balls;
	private int xVelocity;
	private RandomGenerator rgen;

	public void run() {
		rgen = RandomGenerator.getInstance();
		xVelocity = INIT_X_VELOCITY;

		balls = new ArrayList<GOval>();

		for (int i = 1; i <= NUM_BALLS; i++) {
			final GOval ball = new GOval((i * (WINDOW_WIDTH / (NUM_BALLS + 1))) - BALL_SIZE / 2,
					WINDOW_HEIGHT / 2 - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);
			balls.add(ball);
		}

		for (GOval ball : balls) {
			ball.setColor(Color.RED);
			ball.setFilled(true);
			add(ball);
		}

		addMouseListeners();

		animateBall();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (GOval ball : balls) {
			if (getElementAt(e.getX(), e.getY()) == ball)
				ball.setColor(rgen.nextColor());
			else
				ball.setLocation(ball.getX(), e.getY());
		}
	}

	private void animateBall() {

		while (true) {
			for (GOval ball : balls) {
				ball.move(xVelocity, 0);
				if (outOfBounds(ball)) {
					xVelocity *= -1;
				}
				pause(BREAK_MS);
			}
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
		new Balls().start();
	}
}