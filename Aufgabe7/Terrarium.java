import java.util.LinkedList;
import java.util.List;

/* A terrarium holds reptiles. */

public abstract class Terrarium implements Loadable {
	
	private Reptile load = null;

	public TransportObject unloadObject(Transporter t) {
		Reptile sa = getLoad();
		setLoad(null);
		return sa;
	}

	public List<String> list() {
		List<String> l = new LinkedList<String>();
		if (getLoad() != null) {
			l.add(getLoad().getName());
		}
		return l;
	}

	protected Reptile getLoad() {
		return load;
	}

	protected void setLoad(Reptile load) {
		this.load = load;
	}
}

/* vim: set noet ts=4 sw=4: */
