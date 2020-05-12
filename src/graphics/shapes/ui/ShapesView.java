package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

@SuppressWarnings("serial")
public class ShapesView extends View {
	// Hérite du JPanel via View
	
	public ShapesView(Object model) {
		super(model);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		ShapeDraftman draftman = new ShapeDraftman(g);
		((Shape) model).accept(draftman);
	}	
	
	public Controller defaultController(Object model) {
		return new ShapesController(model);
	}
}
