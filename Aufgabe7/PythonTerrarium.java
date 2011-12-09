public class PythonTerrarium extends Terrarium {	
	
	/*
	 * Loads the passed Python into this Loadable
	 * (o != null)
	 */
	public boolean loadLoadable(Python o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	
	/*
	 * Loads the passed Mamba into this Loadable
	 * Mamba can be loaded into this Loadable as well
	 * (o != null)
	 */
	public boolean loadLoadable(Mamba o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
