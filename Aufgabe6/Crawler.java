public class Crawler extends Robot {
	private double distance;

	public Crawler(int id) {
		super(id);
	}
	
	public void incTravelledDistance(double delta) {
		if (delta > 0) {
			distance += delta;
		}
	}
	
	public double getTravelledDistance() {
		return distance;
	}

	public String getType() {
		return "Crawler";
	}
}

/* vim: set noet ts=4 sw=4: */
