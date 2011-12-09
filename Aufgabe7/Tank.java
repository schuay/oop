import java.util.LinkedList;
import java.util.List;

/* A tank holds sea animals. */

public abstract class Tank extends Loadable {
	
	private SeaAnimal load = null;
	
	/*
	 * Unloads the Animal inside the passed Transporter and returns it
	 * (t != null)
	 */
	public TransportObject unloadObject(Transporter t) {
		SeaAnimal sa = getLoad();
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
	protected SeaAnimal getLoad() {
		return load;
	}

	/*
	 * Sets the Animal loaded inside this Loadable
	 * (load != null)
	 */
	protected void setLoad(SeaAnimal load) {
		this.load = load;
	}
}

/* vim: set noet ts=4 sw=4: */
