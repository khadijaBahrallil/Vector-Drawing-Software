package graphics.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {
	protected Object model;
	protected static View view;

	public Controller(Object newModel) {
		model = newModel;

	}

	@SuppressWarnings("static-access")
	public void setView(View view) {
		this.view = view;
	}

	final public static View getView() {
		return view;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public Object getModel() {
		return this.model;
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent evt) {
	}

	public void mouseDragged(MouseEvent evt) {
	}

	public void keyTyped(KeyEvent evt) {
	}

	public void keyPressed(KeyEvent evt) {
	}

	public void keyReleased(KeyEvent evt) {
	}
}