package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Iterator;


import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.SPath;
import graphics.shapes.attributes.PathAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	private Shape target;
	private Point mouseStart;

	public ShapesController(Object newModel) {
		super(newModel);
		this.target = null;
	}

	public Shape getTarget() {
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		boolean targeted = false;
		while (!targeted && itr.hasNext()) {
			shape = itr.next();
			Rectangle bounds = shape.getBounds();
			targeted = bounds.contains(this.mouseStart); // Utilisation de la classe Rectangle java
		}
		if (targeted)
			return shape;
		else
			return null;
	}

	private void unselectAll() {
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		while (itr.hasNext()) {
			shape = itr.next();
			if (shape != null && shape != target) {
				// System.out.println("unselect all");
				SelectionAttributes sA = (SelectionAttributes) shape.getAttributes("selectionAttributes");
				if (sA != null)
					sA.unselect();
			}
		}
	}

	private void translateSelected(int posx, int posy) {
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		while (itr.hasNext()) {
			shape = itr.next();
			if (shape != null) {
				SelectionAttributes sA = (SelectionAttributes) shape.getAttributes("selectionAttributes");
				if (sA != null && sA.isSelected()) {
					int dx = (int) (posx - mouseStart.getX());
					int dy = (int) (posy - mouseStart.getY());
					shape.translate(dx, dy);
				}
			}
		}
	}


	public void mousePressed(MouseEvent e) {
		mouseStart = new Point(e.getX(), e.getY());
		try {
			this.target = getTarget();
		} catch (IndexOutOfBoundsException e1) {

		}
		
	}

	public void mouseClicked(MouseEvent e) {
		if (this.target != null) {
			if (e.isShiftDown()) {
			} else {
				this.unselectAll();
			}
			SelectionAttributes sA = (SelectionAttributes) target.getAttributes("selectionAttributes");
			if (sA != null) {
				sA.select();
			}
		} else {
			this.unselectAll();
		}// If Right Click
		if (e.getButton() == 3) {
			PopupMenu rightClick = new PopupMenu(this); // Initialize a RightClight class Object
			rightClick.pop(e); // Call its method
		}
		this.getView().repaint();
	}

	public void mouseDragged(MouseEvent evt) {
		Shape shape = null;
		SPath draw = null;

		boolean containsSelected = false;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		while (itr.hasNext()) {
			shape = itr.next();
			if (shape != null) {
				SelectionAttributes sA = (SelectionAttributes) shape.getAttributes("selectionAttributes");
				if (sA != null && sA.isSelected() && shape == this.target) {
					containsSelected = true;
				}
				PathAttributes pA = (PathAttributes) shape.getAttributes("pathAttributes");
				if (pA != null) {
					draw = (SPath) shape;
				}
			}
		}

		// if (((SCollection) model).getCollection().contains(this.target)) {
		if (containsSelected) {
			this.translateSelected(evt.getX(), evt.getY());
			mouseStart.setLocation(evt.getX(), evt.getY());
			this.getView().repaint();
		}
		if (draw != null) {
			try {
				if (((PathAttributes) draw.getAttributes("pathAttributes")).getPenStatus().equals("IsDown")) {
					draw.addPoint(new Point(evt.getX(), evt.getY()));
					this.getView().repaint();
				}
			} catch (NullPointerException e) {

			}
		}
	}


	public void mouseReleased(MouseEvent evt) {
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		while (itr.hasNext()) {
			shape = itr.next();
			if (shape != null) {
				PathAttributes pA = (PathAttributes) shape.getAttributes("pathAttributes");
				if (pA != null)
					pA.setPenStatus("IsUp");
			}
		}

	}
	

	public void delete() {
		SCollection selectedShapes = this.getSelected(); // Cr�ation de la Collection des formes selectionn�es
		Iterator<Shape> itr = selectedShapes.iterator(); // Crr�ation d'un it�rateur
		Shape shape = null;
		while (itr.hasNext()) { // On parcourt la collection
			shape = itr.next(); // On it�re
			if (shape != null) {
				((SCollection) model).delete(shape);
				System.out.println("Deleted");
			}
		}
		this.getView().repaint(); // R�actualise
	}
	public SCollection getSelected() {
		SCollection selectedShapes = new SCollection();
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		while (itr.hasNext()) {
			shape = itr.next();
			if (shape != null) {
				SelectionAttributes sA = (SelectionAttributes) shape.getAttributes("selectionAttributes");
				if (sA != null && sA.isSelected()) {
					selectedShapes.add(shape);
				}
			}
		}
		return selectedShapes;
	}
	
	
	

}
