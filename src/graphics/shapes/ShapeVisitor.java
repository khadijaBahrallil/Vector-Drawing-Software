package graphics.shapes;


public interface ShapeVisitor {
	// Ajouter les m�thodes nouvelles
	
	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitText(SText text);	
	public void visitCollection(SCollection collec);
	public void visitSelection(Selection selection);
}
