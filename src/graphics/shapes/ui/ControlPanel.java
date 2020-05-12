package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

@SuppressWarnings("serial")
public class ControlPanel extends JMenuBar {
	private JMenu menuShape;
	private SCollection model;
	
	public ControlPanel() {
		this.model = Editor.getModel();
		menuShape = new JMenu("Nouvelle Forme");		
		
		JMenuItem mRectangle = new JMenuItem("Rectangle");
		mRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Rectangle");
				SRectangle r = new SRectangle(new Point(150,40),25,20);
				r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
				r.addAttributes(new SelectionAttributes());
				model.add(r);
				ShapesController.getView().repaint();
			}			
		});
		menuShape.add(mRectangle);
		
		
		JMenuItem mCircle = new JMenuItem("Cercle");
		mCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Cercle");
				SCircle c = new SCircle(new Point(50,200),20);
				c.addAttributes(new ColorAttributes(true,true,Color.ORANGE,Color.BLUE));
				c.addAttributes(new SelectionAttributes());
				model.add(c);
				ShapesController.getView().repaint();
			}			
		});
		menuShape.add(mCircle);
		
		
		JMenuItem mText = new JMenuItem("Texte");
		mText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Texte");
				SText t= new SText(new Point(150,180),"hello");
				t.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.BLUE));
				t.addAttributes(new FontAttributes());
				t.addAttributes(new SelectionAttributes());
				model.add(t);
				ShapesController.getView().repaint();
			}			
		});
		menuShape.add(mText);
		
		this.add(menuShape);
	}
}
