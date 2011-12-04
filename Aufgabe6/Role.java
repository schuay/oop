public interface Role {
	/* Always returns the specific type of the role as a String.
	 * The string contains the name of the dynamic class of the role.
	 * (Therefore it is never null or empty.) */
	String getRole();
}

/* vim: set noet ts=4 sw=4: */
