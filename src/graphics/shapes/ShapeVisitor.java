package graphics.shapes;


public interface ShapeVisitor {
	// Ajouter les méthodes nouvelles
	
	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitText(SText text);	
	public void visitCollection(SCollection collec);
}
