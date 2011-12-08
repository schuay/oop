import java.util.List;

/* An object which can be loaded with transport objects. */ 
public interface Loadable {
	
	/* Unloads the loadable object and returns a reference to
	 * the previously loaded transport object (or null if empty). */
	TransportObject unloadObject(Transporter t);
	
	/* Returns a list of all contained objects. */
	List<String> list();
}

/* vim: set noet ts=4 sw=4: */
