package graphics.shapes.attributes;

public class PathAttributes extends Attributes{

	private String method;
	private String penStatus;
	
	public PathAttributes(String s, String status) {
		this.id = "pathAttributes";
		this.method = s;
		this.penStatus = status;
	}
	
	public String getPenStatus() {
		return penStatus;
	}

	public void setPenStatus(String penStatus) {
		this.penStatus = penStatus;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String getId() {
		return this.id;
	}

}
