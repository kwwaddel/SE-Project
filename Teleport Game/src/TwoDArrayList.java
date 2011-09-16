import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoDArrayList<Tile> {
	
	ArrayList<ArrayList> list;
	int row;
	int col;

	public TwoDArrayList(int r, int c)
	{
		row = r;
		col = c;
		list = new ArrayList<ArrayList>();
		
		for(int i = 0; i < row; i++)
		{
			list.add(new ArrayList<Tile>());
		}
		
	}
	
	public void add(int r, Tile t)
	{
		list.get(r).add(t);
	}
	
	public Tile get(int r, int col)
	{
		return (Tile)list.get(r).get(col);
	}
	
	
}
