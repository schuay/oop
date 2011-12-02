public class Factory {
	private final String name;
	private final Dictionary robots;
	
	/* TODO: assertions */
	
	public Factory(String name) {
		Util.checkNullArg(name);

		this.name = name;
		this.robots = new Dictionary();
	}

	public String getName() {
		return name;
	}
	
	public void addRobot(Robot robot) {
		Util.checkNullArg(robot);

		robots.put(robot.getId(), robot);
	}
	
	public void delRobot(int id) {
		if (!robots.containsKey(id)) {
			throw new IllegalArgumentException();
		}

		robots.remove(id);
	}

	public void robotIncHoursUsed(int id, int delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		r.incHoursUsed(delta);
	}
	
	public int robotGetHoursUsed(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		return r.getHoursUsed();
	}
	
	public void benderIncRotations(int id, int delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		if (!(r instanceof Bender)) {
			throw new IllegalArgumentException();
		}

		((Bender) r).incRotations(delta);
	}
	
	public int benderGetRotations(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		if (!(r instanceof Bender)) {
			throw new IllegalArgumentException();
		}

		return ((Bender) r).getRotations();
	}
	
	public void crawlerIncTravelled(int id, double delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		if (!(r instanceof Crawler)) {
			throw new IllegalArgumentException();
		}

		((Crawler) r).incTravelledDistance(delta);
	}
	
	public double crawlerGetTravelled(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		if (!(r instanceof Crawler)) {
			throw new IllegalArgumentException();
		}

		return ((Crawler) r).getTravelledDistance();
	}
	
	public void robotSetRole(int id, Role role) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		r.setRole(role);
	}
	
	public double getAvgHoursUsed() {
		Iter it = new AvgHoursUsed().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}
	
	public Dictionary getAvgHoursUsedByRole() {
		return new AvgHoursUsed() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}
	
	public Dictionary getAvgHoursUsedByType() {
		return new AvgHoursUsed() {
			protected String filter(Robot r) {
				return r.getType();
			}
		}.calc();
	}

	private class AvgHoursUsed extends RobotAvg {
		protected Object getData(Robot r) {
			return (double) r.getHoursUsed();
		}

		protected boolean regard(Robot r) {
			return true;
		}
	}

	private abstract class RobotStat {
		protected static final String FILTER_ALL="all";
		protected Dictionary stats;
		
		protected abstract Object getData(Robot r);
		protected abstract boolean regard(Robot r);
		protected abstract void calculateCurrent(Robot r, String key);

		protected String filter(Robot r) {
			return FILTER_ALL;
		}

		public Dictionary calc() {
			stats = new Dictionary();
			Robot r;
			String key;

			for (Iter it = robots.values(); it.hasNext();) {
				r = (Robot) it.next();
				key = filter(r);

				calculateCurrent(r, key);
			}

			return stats;
		}
	}

	private abstract class RobotAvg extends RobotStat {
		private final Dictionary counts = new Dictionary();

		protected void calculateCurrent(Robot r, String key) {
			double avg;
			double count;

			if (!regard(r)) {
				if (!stats.containsKey(key)) {
					stats.put(key, 0.0);
					counts.put(key, 0.0);
				}
				return;
			}

			if (!stats.containsKey(key)) {
				stats.put(key, (Double) getData(r));
				counts.put(key, 1.0);
			} else {
				avg = ((Double) stats.get(key));
				count = ((Double) counts.get(key));

				avg = ((avg * count) + (Double)getData(r))/++count;

				stats.put(key, avg);
				counts.put(key, count);
			}
		}
	}

	public double getAvgRotations() {
		Iter it = new AvgRotations().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}

	public Dictionary getAvgRotationsByRole() {
		return new AvgRotations() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}

	private class AvgRotations extends RobotAvg {
		protected Object getData(Robot r) {
			return (double) ((Bender) r).getRotations();
		}

		protected boolean regard(Robot r) {
			return (r instanceof Bender);
		}
	}

	public double getAvgTravelled() {
		Iter it = new AvgTravelled().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}
	
	public Dictionary getAvgTravelledByRole() {
		return new AvgTravelled() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}

	private class AvgTravelled extends RobotAvg {
		protected Object getData(Robot r) {
			return (double) ((Crawler) r).getTravelledDistance();
		}

		protected boolean regard(Robot r) {
			return (r instanceof Crawler);
		}
	}

	private abstract class RobotMinMax extends RobotStat {
		protected abstract boolean cmp(int old, int current);
		protected void calculateCurrent(Robot r, String key) {
			if (!regard(r)) {
				if (filter(r) != FILTER_ALL && !stats.containsKey(key)) {
					stats.put(key, 0);
				}
				return;
			}

			int current = (Integer) getData(r);
			if (!stats.containsKey(key)) {
				stats.put(key, current);
			} else {
				int old = (Integer) stats.get(key);

				if (cmp(old, current)) {
					stats.put(key, current);
				}
			}
		}
	}

	public int getMinWorkingTemp() {
		Iter it = new MinMaxWorkingTemp() {
			protected boolean cmp(int old, int current) {
				return (current < old);
			}
		}.calc().values();

		if (it.hasNext()) {
			return (Integer) it.next();
		}

		return 0;
	}

	public int getMaxWorkingTemp() {
		Iter it = new MinMaxWorkingTemp() {
			protected boolean cmp(int old, int current) {
				return (current > old);
			}
		}.calc().values();

		if (it.hasNext()) {
			return (Integer) it.next();
		}

		return 0;
	}

	private abstract class MinMaxWorkingTemp extends RobotMinMax {
		protected Object getData(Robot r) {
			Role role = r.getRole();
			return ((Welder) role).getWorkingTemp();
		}

		protected boolean regard(Robot r) {
			Role role = r.getRole();
			return (role instanceof Welder);
		}
	}

	public Dictionary getMinWorkingTempByType() {
		return new MinMaxWorkingTemp() {
			protected boolean cmp(int old, int current) {
				return (current < old);
			}

			protected String filter(Robot r) {
				return r.getType();
			}
		}.calc();
	}

	public Dictionary getMaxWorkingTempByType() {
		return new MinMaxWorkingTemp() {
			protected boolean cmp(int old, int current) {
				return (current > old);
			}

			protected String filter(Robot r) {
				return r.getType();
			}
		}.calc();
	}
}

/* vim: set noet ts=4 sw=4: */
