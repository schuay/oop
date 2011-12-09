public class PythonTerrarium extends Terrarium {	
	public boolean loadLoadable(Python o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	public boolean loadLoadable(Mamba o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
