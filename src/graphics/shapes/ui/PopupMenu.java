package graphics.shapes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import graphics.ui.Controller;


public class PopupMenu extends ControllerWithMenu {
	
	protected ShapesController c;

	
	public PopupMenu(Object model) {

		super(model);
	}
	
	

	@Override
	protected void initializeMenu() {
		
		
		this.setPopup(new JPopupMenu());

		
		JMenu edit = new JMenu("Edit");
		
		this.addMenuItem(getPopup(), edit, null, null);
		
		JMenuItem split = new JMenuItem("Split");
		ActionListener splitListener = new ActionListener()
        {
            
            public void actionPerformed(ActionEvent action)
            {
            	c = (ShapesController) Controller.getView().getController();
                c.split(	);
            }
        };
	
		
		this.addSubMenuItem(edit, split, null, splitListener);
		
		JMenuItem join = new JMenuItem("Join");
		ActionListener joinListener = new ActionListener()
        {
            
            public void actionPerformed(ActionEvent action)
            {
            	c = (ShapesController) Controller.getView().getController();
                c.join(	);
            }
        };
	
		
		this.addSubMenuItem(edit, join, null, joinListener);
		
		JMenuItem resize = new JMenuItem("Resize");
		ActionListener resizeListener = new ActionListener()
        {
            
            public void actionPerformed(ActionEvent action)
            {
            	c = (ShapesController) Controller.getView().getController();
                c.resize(	);
            }
        };
	
		
		this.addSubMenuItem(edit, resize, null, resizeListener);
		
	}
/*
	JPopupMenu menu = new JPopupMenu();

	private Compose c;

	public PopupMenu(ShapesController ctrl) {
		this.c = new Compose((ShapesView) ctrl.getView());
	}

	public JPopupMenu pop(MouseEvent e) {

		JMenu edit = new JMenu("Edit");

		JMenuItem del = new JMenuItem("Delete");
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.delete();
			}
		});
		edit.add(del);

		JMenuItem und = new JMenuItem("Undelete");
		und.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//c.undo();
			}
		});
		edit.add(und);

		edit.addSeparator();
		
		
		
		
		
		
		edit.addSeparator();

		JMenuItem cop = new JMenuItem("Copy");
		cop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//c.copy();
			}
		});
		edit.add(cop);

		JMenuItem pas = new JMenuItem("Paste");
		pas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//c.join();
			}
		});
		edit.add(pas);
		
		edit.addSeparator();

		JMenuItem spl = new JMenuItem("Split");
		spl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.split();
			}
		});
		edit.add(spl);

		JMenuItem joi = new JMenuItem("Join");
		joi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.join();
			}
		});
		edit.add(joi);

		menu.add(edit);

		
		this.menu.show(e.getComponent(), e.getX(), e.getY());
		return this.menu;
	}
	*/

}
