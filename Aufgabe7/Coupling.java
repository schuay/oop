import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* A coupling connects transporter cars and can have
 * big game tied to it. */

public class Coupling implements Loadable {
	
	private Trailer next = null;
	private final Set<LargeGame> tiedLargeGame; 
	
	/* The tied animals are not changed at runtime. */
	public Coupling(Set<LargeGame> tiedLargeGame) {
		this.tiedLargeGame = tiedLargeGame;
	}

	/* Only unloads connected cars, big game cannot be untied. */
	public TransportObject unloadObject(Transporter t) {
		Trailer trailer = next;
		next = null;
		return trailer;
	}
	
	/* Connects a trailer. This fails and returns false if
	 * there is already one connected; otherwise true is returned. */
	public boolean connectTrailer(Trailer t) {
		if (next != null) {
			return false;
		}
		next = t;
		return true;
	}

	/* Lists all tied animals, then recursively steps through connected
	 * trailers and merges the lists returned by their list() methods. */
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		for (LargeGame bg: tiedLargeGame) {
			l.add(bg.getName());
		}
		if (next != null) {
			l.addAll(next.list());
		}
		return l;
	}
}

/* vim: set noet ts=4 sw=4: */
