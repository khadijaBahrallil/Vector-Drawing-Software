package graphics.shapes.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public abstract class ControllerWithMenu extends ControllerWithClick {
	private JPopupMenu popup;

	public ControllerWithMenu(Object model) {
		super(model);
		initializeMenu();
	}

	protected abstract void initializeMenu();

	protected JPopupMenu getPopup() {
		return popup;
	}

	protected void setPopup(JPopupMenu menu) {
		popup = menu;
	}

	protected void addMenuItem(JPopupMenu menu, JMenuItem menuItem, Font font, ActionListener action) {
		if (menuItem == null)
			return;
		menuItem.setFont(font);
		menuItem.addActionListener(action);
		menu.add(menuItem);
	}

	protected void addSubMenuItem(JMenu subMenu, JMenuItem menuItem, Font font, ActionListener action) {
		if (menuItem == null)
			return;
		menuItem.setFont(font);
		menuItem.addActionListener(action);
		subMenu.add(menuItem);
	}

	protected void addSubMenu(JPopupMenu menu, String label, Font font, JMenu submenu) {
		if (submenu == null)
			return;
		if (label != null)
			submenu.setName(label);
		submenu.setFont(font);
		menu.add(submenu);
	}

	public void mousePressed(MouseEvent e) {
		updateLastClick(e.getPoint());
		showPopup(e);
	}

	public void mouseReleased(MouseEvent e) {
		showPopup(e);
	}

	protected void showPopup(MouseEvent e) {
		if (e.isPopupTrigger())
			popup.show(e.getComponent(), e.getX(), e.getY());
	}

}
