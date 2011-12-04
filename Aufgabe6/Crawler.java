public class Crawler extends Robot {
	/* The distance the robot travelled can only increase.
	 * If the robot hasn't been used yet it is 0. */
	private double distance;

	public Crawler(int id) {
		super(id);
		distance = 0;
	}
	
	/* Increase the distance counter by delta. It is never decreased.
	 * (delta > 0) */
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
