import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

/* A coupling and can have big game tied and one Trailer connected to it. */

public class Coupling extends Loadable {
	
	private final Set<LargeGame> tiedLargeGame; 
	private Trailer trailer;
	
	/* The tied animals are not changed at runtime. 
	 * (tiedLargeGame != null)
	 * */
	
	public Coupling(Set<LargeGame> tiedLargeGame) {
		this.tiedLargeGame = Collections.unmodifiableSet(tiedLargeGame);
	}

	/* Disconnect the Trailer.*/
	public TransportObject unloadObject() {
		Trailer t = trailer;

		trailer = null;

		return t;
	}

	/* Lists all tied animals, then recursively steps through connected
	 * trailers and merges the lists returned by their list() methods. */
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		for (LargeGame bg: tiedLargeGame) {
			l.add(bg.getName());
		}

		if (trailer != null) {
			l.addAll(trailer.list());
		}

		return l;
	}

	/* Connect a Trailer. */
	public boolean loadLoadable(Trailer o) {
		if (trailer != null) {
			return false;
		}

		trailer = o;

		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
