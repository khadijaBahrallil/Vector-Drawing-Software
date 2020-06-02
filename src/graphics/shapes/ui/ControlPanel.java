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
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.PathAttributes;
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
		
		JMenuItem mTriangle = new JMenuItem("Triangle");
		mTriangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Triangle");
				Point[] points = {new Point(130,10),new Point(90,70),new Point(170,70)};
				SPolygon triangle = new SPolygon(points);
				triangle.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.BLACK));
				triangle.addAttributes(new SelectionAttributes());
				model.add(triangle);
				ShapesController.getView().repaint();
			}			
		});
		menuShape.add(mTriangle);
		
		JMenuItem mStar = new JMenuItem("Star");
		mStar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Etoile");
				Point[] points = {new Point(27,198),new Point(153,162),new Point(153,18)
						,new Point(234,135),new Point(360,90),new Point(288,198)
						,new Point(360,306),new Point(234,270),new Point(153,387)
						,new Point(153,243)};
				SPolygon star = new SPolygon(points);
				star.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLACK));
				star.addAttributes(new SelectionAttributes());
				model.add(star);
				ShapesController.getView().repaint();
			}	
		});
		menuShape.add(mStar);
	
		JMenuItem mPathPoints = new JMenuItem("Path points");
		mPathPoints.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Path points");
				
				SPath pathPts = new SPath();
				pathPts.addAttributes(new ColorAttributes(false,true,Color.YELLOW,Color.BLACK));
				pathPts.addAttributes(new SelectionAttributes());
				pathPts.addAttributes(new PathAttributes("Points","IsDown"));
				model.add(pathPts);
				ShapesController.getView().repaint();
			}	
		});
		menuShape.add(mPathPoints);
		
		JMenuItem mPathLines = new JMenuItem("Path lines");
		mPathLines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Path lines");
				
				SPath pathLines = new SPath();
				pathLines.addAttributes(new ColorAttributes(false,true,Color.YELLOW,Color.BLACK));
				pathLines.addAttributes(new SelectionAttributes());
				pathLines.addAttributes(new PathAttributes("Lines","IsDown"));
				model.add(pathLines);
				ShapesController.getView().repaint();
			}	
		});
		menuShape.add(mPathLines);
		
		JMenuItem mPathInterpolation = new JMenuItem("Path Interpolation");
		mPathInterpolation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Création Path interpolations");
				
				SPath pathInterpolation = new SPath();
				pathInterpolation.addAttributes(new ColorAttributes(false,true,Color.YELLOW,Color.BLACK));
				pathInterpolation.addAttributes(new SelectionAttributes());
				pathInterpolation.addAttributes(new PathAttributes("Interpolation","IsDown"));
				model.add(pathInterpolation);
				ShapesController.getView().repaint();
			}	
		});
		menuShape.add(mPathInterpolation);
	}
	
	
}
