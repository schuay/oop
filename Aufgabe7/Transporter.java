import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Transporter extends Loadable {
	
	private final Set<Loadable> load;
	
	public Transporter(Set<Loadable> load) {
		this.load = Collections.unmodifiableSet(load);
	}
	
	/* Loads the TransportObject onto the transporter.
	 * Returns true if loading was successful, and false otherwise. */
	public boolean load(TransportObject to) {
		return to.loadObject(this);
	}
	
	/* Unloads the specified Loadable object and returns
	 * the object previously contained by it. */
	public TransportObject unload(Loadable l) {
		return l.unloadObject(this);
	}
	
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		for (Loadable loadable : load) {
			l.addAll(loadable.list());
		}
		return l;
	}
	
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

	public TransportObject unloadObject(Transporter t) {
		return null;
		
		/* TODO: move reference to next trailer to Transporter
		return coupling.unloadObject(t);
		*/
	}
}

/* vim: set noet ts=4 sw=4: */
