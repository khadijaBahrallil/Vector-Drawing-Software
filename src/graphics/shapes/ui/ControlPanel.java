package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
import graphics.shapes.save.Reader;
import graphics.shapes.save.SVGVisitor;
import graphics.shapes.save.Writer;
import graphics.shapes.save.XMLReader;
import graphics.shapes.save.XMLVisitor;

@SuppressWarnings("serial")
public class ControlPanel extends JMenuBar {
	private JMenu menuShape;
	private JMenu menuFile;
	private JMenu menuSave;
	private JMenu menuOpen;
	
	private SCollection model;
	private Writer writer;
	private Reader reader;
	
	public ControlPanel(ShapesView sview) throws FileNotFoundException {
		this.model = Editor.getModel();
		

		
		
		
		menuFile = new JMenu(" Fichier ");
		menuSave = new JMenu(" Enregistrer ");
		
		JMenuItem saveXml = new JMenuItem(" XML ");
		saveXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					writer = new Writer(sview);
					writer.write(new XMLVisitor(writer.getWriter()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}			
		});
		
		menuSave.add(saveXml);
		
		JMenuItem saveSVG = new JMenuItem(" SVG ");
		saveSVG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					writer = new Writer(sview);
					writer.write(new SVGVisitor(writer.getWriter()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}			
		});
		
		menuSave.add(saveSVG);
		
		menuFile.add(menuSave);
		
		
menuOpen = new JMenu(" Ouvrir ");
		
		JMenuItem openXml = new JMenuItem(" XML ");
		openXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reader = new Reader();
				reader.read(new XMLReader());
				
				
			}			
		});
		
		menuOpen.add(openXml);
		
		JMenuItem openSVG = new JMenuItem(" SVG ");
		openSVG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}			
		});
		
		menuOpen.add(openSVG);
		
		menuFile.add(menuOpen);
		
		
		
		
		
		
		
		
		
		
		this.add(menuFile);
		menuShape = new JMenu(" Nouvelle Forme ");		
		
		JMenuItem mRectangle = new JMenuItem("Rectangle");
		mRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
