package graphics.shapes.ui;

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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Editor extends JFrame
{
	private ShapesView sview;
	//Shape model;
	private static SCollection model;
	private ControlPanel controlPanel;
	
	public Editor() throws FileNotFoundException
	{
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel(); 
		
		// Affichage Mod�le :
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(300,300));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
		
	    // Affichage Menu :
		controlPanel = new ControlPanel(sview);
	    this.getContentPane().add(this.controlPanel, java.awt.BorderLayout.NORTH);
	    
	   
	}
	
	public Editor(SCollection model) throws FileNotFoundException {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});

		this.buildModel();

		// Affichage Modele :
		this.sview = new ShapesView((Object) model);
		this.sview.setPreferredSize(new Dimension(300, 300));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);

		// Affichage Menu Principal :
			controlPanel = new ControlPanel(this.sview);
			this.getContentPane().add(this.controlPanel, java.awt.BorderLayout.NORTH);
		//this.setJMenuBar(controlPanel.getMenuBar());
	}

	
	private void buildModel()
	{
		model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(10,10),20,30);
		r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		this.model.add(r);
		
		SCircle c = new SCircle(new Point(100,100),10);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);
		
		SText t= new SText(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);
		
		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r= new SRectangle(new Point(20,30),30,30);
		r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		c = new SCircle(new Point(150,100),20);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		this.model.add(sc);
		
		Point pts[] = {new Point(60,30), new Point(23,56), new Point(98,135)};
		SPolygon p = new SPolygon(pts);
		p.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.BLACK));
		p.addAttributes(new SelectionAttributes());
		this.model.add(p);
		
		ArrayList<Point> ps = new ArrayList<>();
		ps.add(new Point(60,30));
		ps.add(new Point(61,30));
		ps.add(new Point(62,31));
		ps.add(new Point(23,56));
		ps.add(new Point(98,135));
		SPath path = new SPath(ps);
		path.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLACK));
		path.addAttributes(new SelectionAttributes());
		path.addAttributes(new PathAttributes(PathAttributes.Type.method_Lines,null));
		this.model.add(path);
	}
	
	public static SCollection getModel() {
		return model;
	}
	
	public static void main(String[] args)
	{
		
		try {
			Editor self;
			self = new Editor();
			self.pack();
			self.setVisible(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}