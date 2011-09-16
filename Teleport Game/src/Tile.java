import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tile {
	
	JLabel label;
	String id;
	boolean empty;
	
	public Tile(JLabel l, String identifier, boolean e)
	{
		label = l;
		id = identifier;
		empty = e;
	}
	
	public void setLabel(JLabel l)
	{
		label = l;
	}
	
	public void changeLabel(ImageIcon i, String identifier, boolean e)
	{
		label.setIcon(i);
		id = identifier;
		empty = e;
	}
	
	public JLabel getLabel()
	{
		return label;
	}
	
	public void setEmpty(boolean b)
	{
		empty = b;
	}
	
	public boolean isEmpty()
	{
		return empty;
	}
	
	public String getID()
	{
		return id;
	}
	

}
