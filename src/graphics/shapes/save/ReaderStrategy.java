package graphics.shapes.save;


import org.w3c.dom.Element;

import graphics.shapes.Shape;

public abstract class ReaderStrategy {
	
	public ReaderStrategy() {
		
		
		
	}
	
	public abstract Shape parse(Element shape);
	

}
