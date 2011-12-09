import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* A coupling connects transporter cars and can have
 * big game tied to it. */

public class Coupling extends Loadable {
	
	private final Set<LargeGame> tiedLargeGame; 
	
	/* The tied animals are not changed at runtime. */
	public Coupling(Set<LargeGame> tiedLargeGame) {
		this.tiedLargeGame = tiedLargeGame;
	}

	/* Big game cannot be untied. */
	public TransportObject unloadObject(Transporter t) {
		return null;
	}

	/* Lists all tied animals, then recursively steps through connected
	 * trailers and merges the lists returned by their list() methods. */
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		for (LargeGame bg: tiedLargeGame) {
			l.add(bg.getName());
		}
		return l;
	}
}

/* vim: set noet ts=4 sw=4: */
