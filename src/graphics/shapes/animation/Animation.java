package graphics.shapes.animation;

import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.SCollection;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Animation {
	private Timer timer;
	private int vx, vy;
	private SCollection model;

	public Animation(SCollection newModel) {
		this.model = newModel;
		this.vx = 1;
		this.vy = 1;
	}

	public void animatedSelected(ShapesView shapesView, int speed) {
		this.timer = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Shape shape = null;
				Iterator<Shape> itr = ((SCollection) model).iterator();
				while (itr.hasNext()) {
					shape = itr.next();
					if (shape != null) {
						SelectionAttributes sA = (SelectionAttributes) shape.getAttributes("selectionAttributes");
						if (sA != null && sA.isSelected()) {
							if (shape.getBounds().x <= 0) {
								vx = 1;
							}
							if (shape.getBounds().x + shape.getBounds().width >= shapesView.getWidth()) {
								vx = -1;
							}
							if (shape.getBounds().y <= 0) {
								vy = 1;
							}
							if (shape.getBounds().y + shape.getBounds().height >= shapesView.getHeight()) {
								vy = -1;
							}
							shape.translate(vx, vy);
						}
					}
				}
				shapesView.repaint();
			}

		});
		this.timer.start();
	}

	public Timer getTimer() {
		return this.timer;
	}
}
