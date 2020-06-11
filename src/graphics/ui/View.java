package graphics.ui;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class View extends JPanel {
	protected Object model;
	private Controller controller;
	private Controller clickController;

	public View(Object model) {
		this.model = model;
		this.controller = defaultController(model);
		this.clickController = defaultClickController(model);
		this.controller.setView(this);
		this.clickController.setView(this);
		this.addMouseListener(this.controller);
		this.addMouseMotionListener(this.controller);
		this.addKeyListener(this.controller);
		this.addMouseListener(this.clickController);
		this.addMouseMotionListener(this.clickController);
		this.addKeyListener(this.clickController);
	}

	public void setModel(Object model) {
		this.model = model;
		this.controller.setModel(model);
		this.clickController.setModel(model);

	}

	public Object getModel() {
		return this.model;
	}

	public Controller defaultController(Object model) {
		return new Controller(model);
	}

	public Controller defaultClickController(Object model) {


		return new Controller(model);
	}

	final public Controller getController() {
		return this.controller;
	}
}