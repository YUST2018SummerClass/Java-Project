package jdbc.cecom.yust.edu;

public class Item {
	
	private String baseDate;
	private String baseTime;
	private String category;
	private String nx;
	private String ny;
	private double obsrValue;
	
	public Item(){}

	public String getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	public String getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNx() {
		return nx;
	}

	public void setNx(String nx) {
		this.nx = nx;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public double getObsrValue() {
		return obsrValue;
	}

	public void setObsrValue(double obsrValue) {
		this.obsrValue = obsrValue;
	}

	
}
