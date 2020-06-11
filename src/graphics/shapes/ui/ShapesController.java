package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.PathAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

@SuppressWarnings("static-access")

public class ShapesController extends Controller {
	private Shape target;
	private Point mouseStart;
	private ArrayList<Shape> del;

	public ShapesController(Object newModel) {
		super(newModel);
		this.target = null;
		this.del = new ArrayList<Shape>();

	}

	public Shape getTarget() {
		Shape shape = null;
		Iterator<Shape> itr = ((SCollection) model).iterator();
		boolean targeted = false;
		while (!targeted && itr.hasNext()) {
			shape = itr.next();
			Rectangle bounds = shape.getBounds();
			targeted = bounds.contains(this.mouseStart);
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

	
	public ArrayList<Shape> selected() {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> it = sc.iterator(); it.hasNext();) {
			Shape currentShape = it.next();
			SelectionAttributes selectAtt = (SelectionAttributes) currentShape.getAttributes("selectionAttributes");
			if (selectAtt != null) {
				if (selectAtt.isSelected()) {
					shapes.add(currentShape);
				}
			}
		}
		return shapes;
	}

	public void delete() {
		SCollection toDelete = getSelected();
		if (!selected().isEmpty())
			del.clear();
		for (Iterator<Shape> it = toDelete.iterator(); it.hasNext();) {
			Shape currentShape = it.next();
			this.del.add(currentShape);
			((SCollection) (this.getModel())).remove(currentShape);
			this.getView().repaint();
		}
		unselectAll();
	}

	public void clear() {
		ArrayList<Shape> toClear = selected();
		for (Shape shape : toClear) {
			((SCollection) (this.getModel())).remove(shape);
		}
	}

	

	public void split() {
		SCollection model = (SCollection) this.getModel();
		for (Shape col : selected()) {
			if (col instanceof SCollection) {
				for (Shape currentShape : ((SCollection) col).getCollection()) {
					Shape s = currentShape.clone();
					model.add(s);
				}
				delete();
				del.clear();
				getView().repaint();
			}
		}
	}

	public void join() {
		SCollection toJoin = getSelected();
		SCollection model = (SCollection) this.getModel();
		SCollection sc = new SCollection();
		for (Iterator<Shape> it = toJoin.iterator(); it.hasNext();) {
			Shape currentShape = it.next();
			sc.add(currentShape.clone());
		}
		delete();
		del.clear();
		model.add(sc);
		getView().repaint();
	}

	public void resize() {
		ArrayList<Shape> toResize = selected();
		for (Shape s : toResize) {
			if (s instanceof SRectangle) {
				((SRectangle) s).resize();
			} else if (s instanceof SCircle) {
				((SCircle) s).resize();
			} else if (s instanceof SText) {
				((SText) s).resize();
			} else if (s instanceof SPolygon) {
				((SPolygon) s).resize();
			} else if (s instanceof SCollection) {
				((SCollection) s).resize(s);
				getView().repaint();
			}
		}
		getView().repaint();
	}

	public void selectAll() {
		Shape s;
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> i = sc.iterator(); i.hasNext();) {
			s = (Shape) i.next();
			SelectionAttributes sa = new SelectionAttributes();
			sa.select();
			s.addAttributes(sa);
		}
	}

}
