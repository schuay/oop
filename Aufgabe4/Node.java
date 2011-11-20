public interface Node {

	public boolean isFull();
	public boolean hasLeft();
	public boolean hasRight();
	public Node getLeft();
	public Node getRight();
	public int getLevel();
}