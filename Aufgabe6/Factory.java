public class Factory {
	
	/* TODO: assertions */
	
	public Factory(String name) {
		
	}
	
	public void addRobot(Robot robot) {
		
	}
	
	public void delRobot(int id) {
		
	}

	public void robotIncHoursUsed(int id, int delta) {
		
	}
	
	public int robotGetHoursUsed(int id) {
		return -1;
	}
	
	public void benderIncRotations(int id, int delta) {
		
	}
	
	public int benderGetRotations(int id) {
		return -1;
	}
	
	public void crawlerIncTravelled(int id, double delta) {
		
	}
	
	public double crawlerGetTravelled(int id) {
		return -1;
	}
	
	public void robotSetRole(int id, Role role) {
		
	}
	
	public double getAvgHoursUsed() {
		return -1;
	}
	
	public Dictionary getAvgHoursUsedByRole() {
		return null;
	}
	
	public Dictionary getAvgHoursUsedByType() {
		return null;
	}
	
	public double getAvgRotations() {
		return -1;
	}
	
	public Dictionary getAvgRotationsByRole() {
		return null;
	}
	
	public double getAvgTravelled() {
		return -1;
	}
	
	public Dictionary getAvgTravelledByRole() {
		return null;
	}
	
	public int getMinWorkingTemp() {
		return -1;
	}

	public int getMaxWorkingTemp() {
		return -1;
	}
	
	public Dictionary getMinWorkingTempByType() {
		return null;
	}

	public Dictionary getMaxWorkingTempByType() {
		return null;
	}
}

/* vim: set noet ts=4 sw=4: */
