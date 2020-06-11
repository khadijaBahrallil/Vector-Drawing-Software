package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graphics.ui.Controller;

public class ControllerWithClick extends Controller {
	private Point lastClick = new Point();

	public ControllerWithClick(Object model) {

		super(model);

	}

	protected void updateLastClick(Point p) {
		lastClick.setLocation(p);
	}

	public Point getLastClick() {
		return new Point(lastClick);
	}

	public void mousePressed(MouseEvent e) {
		updateLastClick(e.getPoint());
	}

	public void mouseClicked(MouseEvent e) {
		updateLastClick(e.getPoint());
	}
}
