import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

public class Dragging extends GraphicsProgram {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int SHAPE_SIZE = 100;
	
	private GObject toDrag;
	
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
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	
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
