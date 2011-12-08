/* A transportable object which can be loaded onto Loadables. */

public interface TransportObject {
	
	/* Loads this object onto the given transporter and returns
	 * true if successful, false otherwise. */
	boolean loadObject(Transporter t);
}

/* vim: set noet ts=4 sw=4: */
