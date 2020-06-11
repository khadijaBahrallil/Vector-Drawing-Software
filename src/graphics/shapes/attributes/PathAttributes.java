package graphics.shapes.attributes;

public class PathAttributes extends Attributes{
	private Type method;
	private String penStatus;
	
	public PathAttributes(Type m, String status) {
		this.id = "pathAttributes";
		this.method = m;
		this.penStatus = status;
	}
	
	public PathAttributes() {
		this.id = "pathAttributes";
	}

	public String getPenStatus() {
		return penStatus;
	}

	public void setPenStatus(String penStatus) {
		this.penStatus = penStatus;
	}
	
	public Type getMethod() {
		return method;
	}

	public void setMethod(Type method) {
		this.method = method;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public enum Type {
		  method_Points,
		  method_Lines,
		  method_Interpolation,
		  method_Test;	
		}

}
