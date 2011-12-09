import java.util.List;

/* An object which can be loaded with transport objects. */ 
public abstract class Loadable {
	
	/* Unloads the loadable object and returns a reference to
	 * the previously loaded transport object (or null if empty). */
	public abstract TransportObject unloadObject(Transporter t);
	
	/* Returns a list of all contained objects. */
	public abstract List<String> list();

	/* Implement loading routines for all TransportObject types;
	 * returns true if loading was successful and false otherwise. */
	public boolean loadLoadable(Mamba o) { return false; }
	public boolean loadLoadable(Python o) { return false; }
	public boolean loadLoadable(Trailer o) { return false; }
	public boolean loadLoadable(Dolphin o) { return false; }
	public boolean loadLoadable(DwarfTortoise o) { return false; }
	public boolean loadLoadable(Elephant o) { return false; }
	public boolean loadLoadable(GiantTortoise o) { return false; }
	public boolean loadLoadable(Giraffe o) { return false; }
	public boolean loadLoadable(Seal o) { return false; }
	public boolean loadLoadable(Swordfish o) { return false; }
}

/* vim: set noet ts=4 sw=4: */
