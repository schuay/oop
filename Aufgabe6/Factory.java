public class Factory {
	/* name never changes */
	private final String name;

	/* The robots Dictionary is never null.
	 * For every id it can only contain one robot.
	 * Therefore each robot in the Dictionary has a unique id. */
	private final Dictionary robots;
	
	/* Creates a new Factory with the name name
	 * (name != null) */
	public Factory(String name) {
		Util.checkNullArg(name);

		this.name = name;
		this.robots = new Dictionary();
	}

	public String getName() {
		return name;
	}
	
	/* Add robot to robots.
	 * If a robot with the same id already exists, replace it.
	 * (robot != null) */
	public void addRobot(Robot robot) {
		Util.checkNullArg(robot);

		robots.put(robot.getId(), robot);
	}
	
	/* Delete the robot with the id id from robots.
	 * If no robot with the given id exists, an IllegalArgumentException
	 * is thrown and the robots dictionary remains unchanged. */
	public void delRobot(int id) {
		if (!robots.containsKey(id)) {
			throw new IllegalArgumentException();
		}

		robots.remove(id);
	}

	/* Increase the hoursUsed counter of the robot with the id id.
	 * If no robot with the given id exists, an IllegalArgumentException
	 * is thrown and no robot is altered. */
	public void robotIncHoursUsed(int id, int delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		r.incHoursUsed(delta);
	}
	
	/* Returns the hoursUsed counter of the robot with the id id.
	 * If no such robot exists, an IllegalArgumentException is thrown. */
	public int robotGetHoursUsed(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		return r.getHoursUsed();
	}
	
	/* Increase the rotations counter of the bender with the id id.
	 * If no robot with the given id exists or this robot is not a Bender,
	 * an IllegalArgumentException is thrown and no robot is altered. */
	public void benderIncRotations(int id, int delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);
		Util.checkInstanceOf(r, "Bender");

		((Bender) r).incRotations(delta);
	}
	
	/* Returns the rotations counter of the bender with the id id.
	 * If no such robot exists or this robot is not a Bender an
	 * IllegalArgumentException is thrown. */
	public int benderGetRotations(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);
		Util.checkInstanceOf(r, "Bender");

		return ((Bender) r).getRotations();
	}
	
	/* Increase the travelled distance of the crawler with the id id.
	 * If no robot with the given id exists or this robot is not a Crawler,
	 * an IllegalArgumentException is thrown and no robot is altered. */
	public void crawlerIncTravelled(int id, double delta) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);
		Util.checkInstanceOf(r, "Crawler");

		((Crawler) r).incTravelledDistance(delta);
	}
	
	/* Returns the travelled distance of the crawler with the id id.
	 * If no such robot exists or this robot is not a Crawler an
	 * IllegalArgumentException is thrown. */
	public double crawlerGetTravelled(int id) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);
		Util.checkInstanceOf(r, "Crawler");

		return ((Crawler) r).getTravelledDistance();
	}
	
	/* Changes the role of the robot with the id id to role.
	 * If no such robot exists an IllegalArgumentException is thrown.
	 * (role != null) */
	public void robotSetRole(int id, Role role) {
		Robot r = (Robot) robots.get(id);
		Util.checkNullArg(r);

		r.setRole(role);
	}
	
	/* Returns the average time a robot was in use.
	 * Doesn't alter the Factory or any robot used in it. */
	public double getAvgHoursUsed() {
		Iter it = new AvgHoursUsed().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}
	
	/* Returns a Dictionary with every role used in the Factory and the average
	 * time a robot of each role was in use.
	 * Doesn't alter the Factory or any robot used in it. */
	public Dictionary getAvgHoursUsedByRole() {
		return new AvgHoursUsed() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}
	
	/* Returns a Dictionary with every robot type used in the Factory and the
	 * average time a robot of each type was in use.
	 * Doesn't alter the Factory or any robot used in it. */
	public Dictionary getAvgHoursUsedByType() {
		return new AvgHoursUsed() {
			protected String filter(Robot r) {
				return r.getType();
			}
		}.calc();
	}

	/* A class to calculate the average of the hoursUsed counter of the robots. */
	private class AvgHoursUsed extends RobotAvg {
		protected Number getData(Robot r) {
			return r.getHoursUsed();
		}

		protected boolean regard(Robot r) {
			return true;
		}
	}

	/* A class to generate statistics for the robots in this factory. */
	private abstract class RobotStat {
		protected static final String FILTER_ALL="all";

		/* Statistical data is stored here. */
		protected Dictionary stats;
		
		/* Returns the data used in the statistic from the given robot.
		 * (r != null) */
		protected abstract Number getData(Robot r);

		/* Determines whether the given robot should be used in the statistic.
		 * (r != null) */
		protected abstract boolean regard(Robot r);

		/* Calculate the statistical data for the given robot and store it in
		 * stats under the given key.
		 * (r != null, key != null)*/
		protected abstract void calculateCurrent(Robot r, String key);

		/* Returns a filter String for the given robot.
		 * This is used to split the statistic into several parts depending on
		 * the robots properties (role, type).
		 * (r != null) */
		protected String filter(Robot r) {
			return FILTER_ALL;
		}

		/* Iterate over all robots in the factory and return the calculated
		 * statistical data as Dictionary. */
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

	/* A class to calculate some average over some properties of robots. */
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
				stats.put(key, getData(r).doubleValue());
				counts.put(key, 1.0);
			} else {
				avg = ((Double) stats.get(key));
				count = ((Double) counts.get(key));

				avg = ((avg * count) + getData(r).doubleValue())/++count;

				stats.put(key, avg);
				counts.put(key, count);
			}
		}
	}

	/* Returns the average over the rotations of all benders.
	 * Doesn't alter the Factory or any robot used in it. */
	public double getAvgRotations() {
		Iter it = new AvgRotations().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}

	/* Returns a Dictionary with every role used in the Factory and the average
	 * rotations a bender of each role made.
	 * Doesn't alter the Factory or any robot used in it. */
	public Dictionary getAvgRotationsByRole() {
		return new AvgRotations() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}

	/* A class to calculate the average of the rotations counter of all benders. */
	private class AvgRotations extends RobotAvg {
		protected Number getData(Robot r) {
			return ((Bender) r).getRotations();
		}

		protected boolean regard(Robot r) {
			return (r instanceof Bender);
		}
	}

	/* Returns the average over the travelled distance of all crawlers.
	 * Doesn't alter the Factory or any robot used in it. */
	public double getAvgTravelled() {
		Iter it = new AvgTravelled().calc().values();

		if (it.hasNext()) {
			return (Double) it.next();
		}

		return 0;
	}
	
	/* Returns a Dictionary with every role used in the Factory and the average
	 * distance a crawler of each role travelled.
	 * Doesn't alter the Factory or any robot used in it. */
	public Dictionary getAvgTravelledByRole() {
		return new AvgTravelled() {
			protected String filter(Robot r) {
				return r.getRole().getRole();
			}
		}.calc();
	}

	/* A class to calculate the average of the travelled distance counter of all
	 * crawlers. */
	private class AvgTravelled extends RobotAvg {
		protected Number getData(Robot r) {
			return ((Crawler) r).getTravelledDistance();
		}

		protected boolean regard(Robot r) {
			return (r instanceof Crawler);
		}
	}

	/* A class to calculate the minimum or maximum of some integer property of
	 * the robots. */
	private abstract class RobotMinMax extends RobotStat {
		/* Returns true of current is lower/greater than old, false otherwise. */
		protected abstract boolean cmp(int old, int current);
		protected void calculateCurrent(Robot r, String key) {
			if (!regard(r)) {
				if (filter(r) != FILTER_ALL && !stats.containsKey(key)) {
					stats.put(key, 0);
				}
				return;
			}

			int current = getData(r).intValue();
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

	/* Returns the minimum working temperature of all Welders used in the
	 * factory.
	 * Doesn't alter the Factory or any robot used in it. */
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

	/* Returns the maximum working temperature of all Welders used in the
	 * factory.
	 * Doesn't alter the Factory or any robot used in it. */
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

	/* A class calculate the minimum/maximum of the working temperature of all
	 * welders. */
	private abstract class MinMaxWorkingTemp extends RobotMinMax {
		protected Number getData(Robot r) {
			Role role = r.getRole();
			return ((Welder) role).getWorkingTemp();
		}

		protected boolean regard(Robot r) {
			Role role = r.getRole();
			return (role instanceof Welder);
		}
	}

	/* Returns a Dictionary with every robot type used in the Factory and the
	 * minimum working temperature of all welders for each type.
	 * Doesn't alter the Factory or any robot used in it. */
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

	/* Returns a Dictionary with every robot type used in the Factory and the
	 * maximum working temperature of all welders for each type.
	 * Doesn't alter the Factory or any robot used in it. */
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
