public abstract class Robot {

	private final int id;
	private int hoursUsed;
	private Role role;
	
	/* TODO: assertions */

	private static class NoRole implements Role {
		public String getRole() {
			return "(none)";
		}
	}
	
	public Robot(int id) {
		this.hoursUsed = 0;
		this.id = id;
		this.role = new NoRole();
	}
	
	public void incHoursUsed(int delta) {
		if (delta > 0) {
			hoursUsed += delta;
		}
	}
	
	public int getHoursUsed() {
		return hoursUsed;
	}
	
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

	public abstract String getType();
}

/* vim: set noet ts=4 sw=4: */
