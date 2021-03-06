import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

public class Transporter {
	
	private final Set<Loadable> load;
	
	public Transporter(LinkedHashSet<Loadable> load) {
		this.load = Collections.unmodifiableSet(load);
	}
	
	/* Loads the TransportObject onto the transporter.
	 * Returns true if loading was successful, and false otherwise. 
	 * (to != null)
	 * */
	public boolean load(TransportObject to) {
		if (to == null) {
			return false;
		}

		return to.loadObject(this);
	}
	
	/* Unloads the specified Loadable object and returns
	 * the object previously contained by it. 
	 * (l != null)
	 * */
	public TransportObject unload(Loadable l) {
		if (l == null) {
			return null;
		}

		return l.unloadObject();
	}
	
	/*
	 * Returns a List of the Names of Animals loaded in this Transporter
	 */
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		for (Loadable loadable : load) {
			l.addAll(loadable.list());
		}
		return l;
	}
	
	/*
	 * Tries to load the passed DwarfTortoise into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadDwarfTortoise(DwarfTortoise o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed GiantTortoise into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadGiantTortoise(GiantTortoise o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed Mamba into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadMamba(Mamba o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed Python into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadPython(Python o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed Dolphin into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadDolphin(Dolphin o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed Seal into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadSeal(Seal o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
	
	/*
	 * Tries to load the passed Swordfish into the Transporter and returns whether it was possible.
	 * (o != null)
	 */
	public boolean loadSwordfish(Swordfish o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);	
	}

	/* Connects a trailer. This fails and returns false if
	 * there is already one connected; otherwise true is returned. 
	 * (o != null)
	 * */
	public boolean loadTrailer(Trailer o) {
		Iterator<Loadable> it = load.iterator();
		Loadable i = null, prev = null;

		/* Case 1: empty list, i = prev = null, returns false
		 * Case 2: inserted successfully, i != prev, returns true
		 * Case 3: not inserted, i == prev, return false
		 */
		while (it.hasNext() && !(i=it.next()).loadLoadable(o)) {
			prev = i;
		}

		return (i != prev);
	}
}

/* vim: set noet ts=4 sw=4: */
