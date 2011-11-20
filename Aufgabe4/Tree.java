public abstract class Tree {
	protected static final String leftStep = "left";
	protected static final String rightStep = "right";
	protected static final String separator = " ";
	protected static final String emptyString = "";
	protected static final String elementNotFound = "Knoten wurde nicht gefunden";

	abstract protected Node getRoot();
}
