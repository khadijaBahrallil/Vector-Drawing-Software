package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ColorPanel extends JMenuBar {
	private JMenu menuShape;
	private JMenuItem menuItem;
	private SCollection model;
	
	public ColorPanel() {
		this.model = Editor.getModel();
		menuShape = new JMenu("Couleur");	
	
	JMenuItem mGreen = new JMenuItem("Vert");
	
	menuShape.add(mGreen);
	
	/*
	JMenuItem mCircle = new JMenuItem("Cercle");
	menuShape.add(mCircle);
	
	JMenuItem mText = new JMenuItem("Texte");
	menuShape.add(mText);
	*/
	
	this.add(menuShape);
	}
}