package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.animation.Animation;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.PathAttributes;
import graphics.shapes.attributes.SelectionAttributes;

@SuppressWarnings("serial")
public class ControlPanel extends JMenuBar {
	private ShapesView shapesView;
	private Map<String, Integer> speedMap;
	private JMenu menuShape;
	private JMenu menuAnim;
	private SCollection model;
	private Animation animation;
	private boolean animationOn;
	private String sText, path, speed, gridState;
	private JMenuBar menuBar;
	
	public ControlPanel(ShapesView shapesView) {
		this.shapesView = shapesView;
		this.model = Editor.getModel();
		this.menuBar = new JMenuBar();
		this.menuBar.setSize(this.shapesView.getWidth(), 100);
		this.menuShape = new JMenu("Nouvelle Forme");		
		this.menuAnim = new JMenu("Animation");	
		this.animation = new Animation(model);
		this.speedMap = new TreeMap<String, Integer>();
		this.animationOn = false;
		this.speed = "Normal";
		this.speedMap.put("Slow", 18);
		this.speedMap.put("Normal", 8);
		this.speedMap.put("Fast", 2);
		
		JMenuItem mRectangle = new JMenuItem("Rectangle");
		mRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Cr�ation Rectangle");
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
				System.out.println("Cr�ation Cercle");
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
				System.out.println("Cr�ation Texte");
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
				System.out.println("Cr�ation Triangle");
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
				System.out.println("Cr�ation Etoile");
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
				System.out.println("Cr�ation Path points");
				
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
				System.out.println("Cr�ation Path lines");
				
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
				System.out.println("Cr�ation Path interpolations");
				
				SPath pathInterpolation = new SPath();
				pathInterpolation.addAttributes(new ColorAttributes(false,true,Color.YELLOW,Color.BLACK));
				pathInterpolation.addAttributes(new SelectionAttributes());
				pathInterpolation.addAttributes(new PathAttributes("Interpolation","IsDown"));
				model.add(pathInterpolation);
				ShapesController.getView().repaint();
			}	
		});
		menuShape.add(mPathInterpolation);
		
		
		
		JMenuItem mStart = new JMenuItem(" Start ");
		mStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("Lancement Anim : "+speed+" = "+speedMap.get(speed));
				animation.animatedSelected(shapesView, speedMap.get(speed));
				mStart.setEnabled(false);
				animationOn = true;
			}
		});
		mStart.setAccelerator(KeyStroke.getKeyStroke('a'));
		menuAnim.add(mStart);

		JMenuItem mStop = new JMenuItem(" Stop ");
		mStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mStart.setEnabled(true);
				// System.out.println("Stop Anim");
				animation.getTimer().stop();
				animationOn = false;
			}
		});
		mStop.setAccelerator(KeyStroke.getKeyStroke('z'));
		menuAnim.add(mStop);
		menuAnim.addSeparator();

		JMenu mSpeed = new JMenu(" Speed ");
		JRadioButtonMenuItem mSlow = new JRadioButtonMenuItem("Slow");
		JRadioButtonMenuItem mNormal = new JRadioButtonMenuItem("Normal");
		JRadioButtonMenuItem mFast = new JRadioButtonMenuItem("Fast");
		JLabel speedInfo = new JLabel("   (Normal)");

		ButtonGroup bgSpeed = new ButtonGroup();
		bgSpeed.add(mSlow);
		bgSpeed.add(mNormal);
		bgSpeed.add(mFast);

		class SpeedListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				speed = ((JRadioButtonMenuItem) e.getSource()).getText();
				speedInfo.setText("   (" + ((JRadioButtonMenuItem) e.getSource()).getText() + ")");
				
				// Restart Animation to take the new speed account
				if (animationOn) {
					animation.getTimer().stop();
					animation.animatedSelected(shapesView, speedMap.get(speed));
				}
			}
		}

		SpeedListener speedLis = new SpeedListener();
		mSlow.addActionListener(speedLis);
		mSlow.setAccelerator(KeyStroke.getKeyStroke('1'));
		mNormal.addActionListener(speedLis);
		mNormal.setAccelerator(KeyStroke.getKeyStroke('2'));
		mFast.addActionListener(speedLis);
		mFast.setAccelerator(KeyStroke.getKeyStroke('3'));
		mFast.addActionListener(speedLis);
		mSpeed.add(mSlow);
		mSpeed.add(mNormal);
		mSpeed.add(mFast);
		mNormal.setSelected(true);
		menuAnim.add(mSpeed);
		menuAnim.add(speedInfo);
		
		menuBar.add(menuAnim);
		menuBar.add(menuShape);
	}
	
	public JMenuBar getMenuBar() {
		return this.menuBar;
	}
	
	
}
