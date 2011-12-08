import java.util.LinkedList;
import java.util.List;

/* A tank holds sea animals. */

public abstract class Tank implements Loadable {
	
	private SeaAnimal load = null;
	
	public TransportObject unloadObject(Transporter t) {
		SeaAnimal sa = getLoad();
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

	protected SeaAnimal getLoad() {
		return load;
	}

	protected void setLoad(SeaAnimal load) {
		this.load = load;
	}
}

/* vim: set noet ts=4 sw=4: */
