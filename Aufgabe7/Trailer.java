import java.util.LinkedHashSet;

public class Trailer extends Transporter implements TransportObject {

	/* Creates a new Trailer with the passed Loadables
	 * (load != null)
	 */
	public Trailer(LinkedHashSet<Loadable> load) {
		super(load);
	}

	/* Connects this Trailer with the passed Transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadTrailer(this);
	}
}

/* vim: set noet ts=4 sw=4: */
