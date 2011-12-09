import java.util.Set;

public class Trailer extends Transporter implements TransportObject {

	public Trailer(Set<Loadable> load) {
		super(load);
	}

	public boolean loadObject(Transporter t) {
		return t.loadTrailer(this);
	}
}

/* vim: set noet ts=4 sw=4: */