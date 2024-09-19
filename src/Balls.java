import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Ball extends GOval {
	private double xVelocity;

	public Ball(double x, double y, double width, double height, double xVelocity) {
		super(x, y, width, height);
		this.xVelocity = xVelocity;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public void changeXDirection() {
		xVelocity *= -1;
	}

}

public class Balls extends GraphicsProgram {
	public static final int NUM_BALLS = 2;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int BALL_SIZE = 100;
	public static final int BREAK_MS = 30;
	public static final int INIT_X_VELOCITY = 5;

	private ArrayList<Ball> balls;
	private GOval lastBallClicked;
	private int xVelocity;
	private RandomGenerator rgen;

	public void run() {
		rgen = RandomGenerator.getInstance();
		xVelocity = INIT_X_VELOCITY;

		balls = new ArrayList<Ball>();

		for (int i = 1; i <= NUM_BALLS; i++) {
			final Ball ball = new Ball((i * (WINDOW_WIDTH / (NUM_BALLS + 1))) - BALL_SIZE / 2,
					WINDOW_HEIGHT / 2 - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE, xVelocity);
			balls.add(ball);
		}

		for (Ball ball : balls) {
			ball.setColor(Color.RED);
			ball.setFilled(true);
			add(ball);
		}

		addMouseListeners();

		animateBall();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Ball ball : balls) {
			if (getElementAt(e.getX(), e.getY()) == ball) {
				ball.setColor(rgen.nextColor());
				lastBallClicked = ball;
			} else {
				if (lastBallClicked != null)
					lastBallClicked.setLocation(ball.getX(), e.getY());
			}
		}
	}

	private void animateBall() {

		while (true) {
			for (Ball ball : balls) {
				ball.move(ball.getXVelocity(), 0);
				if (outOfBounds(ball)) {
					ball.changeXDirection();
				}
				pause(BREAK_MS);
			}
		}
	}

	private boolean outOfBounds(Ball o) {
		double x = o.getX();
		return (x < 0 && o.getXVelocity() < 0 || x > WINDOW_WIDTH && o.getXVelocity() > 0);
	}

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}

	public static void main(String[] args) {
		new Balls().start();
	}
}