
public class InterfaceControl {

	
	public static void main(String args [])
	{
		TwoDArrayList<Tile> theTiles = new TwoDArrayList<Tile>(10, 10);
		//GridRunner gr = new GridRunner(0, 0, theTiles);
		GridScreen gs = new GridScreen(theTiles);
		CommandScreen cs = new CommandScreen(gs);
		
	}
}
