public class PythonTerrarium extends Terrarium {	
	public boolean loadPython(Python o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	public boolean loadMamba(Mamba o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
