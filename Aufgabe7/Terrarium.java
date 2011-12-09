import java.util.LinkedList;
import java.util.List;

/* A terrarium holds reptiles. */

public abstract class Terrarium extends Loadable {
	
	private Reptile load = null;

	/*
	 * Unloads the Animal inside the passed Transporter and returns it
	 */
	public TransportObject unloadObject() {
		Reptile sa = getLoad();
		setLoad(null);
		return sa;
	}

	/*
	 * Returns a list with the name of the loaded Animal
	 */
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		if (getLoad() != null) {
			l.add(getLoad().getName());
		}
		return l;
	}

	/*
	 * Returns the Animal loaded inside this Loadable
	 */
	protected Reptile getLoad() {
		return load;
	}

	/*
	 * Sets the Animal loaded inside this Loadable
	 * (load != null)
	 */
	protected void setLoad(Reptile load) {
		this.load = load;
	}
}

/* vim: set noet ts=4 sw=4: */
