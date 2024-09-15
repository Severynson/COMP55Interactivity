import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

public class Dragging extends GraphicsProgram {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int SHAPE_SIZE = 300;

	private GObject toDrag;
	private double lastX;
	private double lastY;

	public void run() {
		GOval oval = new GOval(100, 100, SHAPE_SIZE, SHAPE_SIZE);
		oval.setColor(Color.blue);
		oval.setFilled(true);
		add(oval);

		GRect rect = new GRect(500, 500, SHAPE_SIZE, SHAPE_SIZE);
		rect.setColor(Color.green);
		rect.setFilled(true);
		add(rect);

		addMouseListeners();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		toDrag = getElementAt(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (toDrag != null)
			toDrag.setLocation(e.getX() - (0.5 * SHAPE_SIZE), e.getY() - (0.5 * SHAPE_SIZE));

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}

	public static void main(String[] args) {
		new Dragging().start();
	}
}
