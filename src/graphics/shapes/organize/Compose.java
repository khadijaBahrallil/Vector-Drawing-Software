package graphics.shapes.organize;
import java.util.ArrayList;
import java.util.Iterator;
import graphics.shapes.SCollection;
import graphics.shapes.Shape;

import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

public class Compose {
	
	private ShapesController controller;
	
	private SelectionAttributes s = new SelectionAttributes();
	
	public Compose(ShapesView sview) {
		this.controller = (ShapesController) sview.getController();
	}
	
	
	
	public void join() {
		ArrayList<Shape> toJoin = selected();
		SCollection model = (SCollection) controller.getModel();
		SCollection sc = new SCollection();
		for (Shape currentShape : toJoin) {
			sc.add(currentShape.clone());
		}
		controller.delete();
		model.add(sc);
		controller.getView().repaint();
	}

	


	public void split() {
		SCollection model = (SCollection) controller.getModel();
		for (Shape col : selected()) {
			if (col instanceof SCollection) {
				for (Shape currentShape : ((SCollection) col).getCollection()) {
					Shape s = currentShape.clone();
					model.add(s);
				}
				controller.delete();
				
				controller.getView().repaint();
			}
		}
	}
	
	public ArrayList<Shape> selected() {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		SCollection sc = (SCollection) controller.getModel();
		System.out.println(sc);
		for (Iterator<Shape> it = sc.iterator(); it.hasNext();) {
			Shape currentShape = it.next();
			
			SelectionAttributes selectAtt = (SelectionAttributes) currentShape.getAttributes(s.getId());
			if (selectAtt != null) {
				if (selectAtt.isSelected()) {
					shapes.add(currentShape);
				}
			}
		}
		return shapes;
	}



	public void delete() {
		controller.delete();
	}
	
	
	
	
	


}
