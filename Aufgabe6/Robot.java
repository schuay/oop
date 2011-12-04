public abstract class Robot {
	/* Represents a Robot.
	 * A Robot initially has no role and has been used for 0 hours. */

	/* The id of a robot never changes. */
	private final int id;

	/* The hoursUsed counter can only increase.
	 * If the robot hasn't been used yet it is 0. */
	private int hoursUsed;

	/* The role is never null. */
	private Role role;
	
	/* NoRole is a dummy role, every robot which hasn't been assigned to a role
	 * has the NoRole role. */
	private static class NoRole implements Role {
		public String getRole() {
			return "(none)";
		}
	}

	/* Create a new Robot with the given id. */
	public Robot(int id) {
		this.hoursUsed = 0;
		this.id = id;
		this.role = new NoRole();
	}
	
	/* Increase the hoursUsed counter by delta. It is never decreased.
	 * (delta > 0) */
	public void incHoursUsed(int delta) {
		if (delta > 0) {
			hoursUsed += delta;
		}
	}
	
	public int getHoursUsed() {
		return hoursUsed;
	}
	
	/* Assign role role to the robot. The old role is lost.
	 * (role != null). */
	public void setRole(Role role) {
		Util.checkNullArg(role);

		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}

	public int getId() {
		return id;
	}

	/* Always returns the specific type of the robot as a String.
	 * The string contains the name of the dynamic class of the robot.
	 * (Therefore it is never null or empty.) */
	public abstract String getType();
}

/* vim: set noet ts=4 sw=4: */
