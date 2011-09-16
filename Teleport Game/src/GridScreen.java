import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;

import javax.imageio.*;

public class GridScreen extends JFrame {
	
	//int moveCount = 0;
	//int moveNum = 0;
	boolean reachedGoal = false;
	Queue<String> dirQueue = new Queue<String>();
	Queue<String> moveQueue = new Queue<String>();
	Queue<String> movingQueue = new Queue<String>();
	Queue<Queue> superQueue = new Queue<Queue>();
	//Queue<String> teleQueue = new Queue<String>();
	
	final JDialog failure = new JDialog(this, "You Lost");
	JButton ok2 = new JButton("Ok");
	ActionListener OAL = new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			failure.dispose();
		}
	};
	
	
	ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			//moveCount < moveNum
			if (!movingQueue.isEmpty())
			{
				moveForward();
				movingQueue.remove();
			}
			else
			{
				//moveCount = 0;
				//moveNum = 0;
				timer.stop();
				
				if (superQueue.isEmpty())
				{
					if (!reachedGoal)
						failure.setVisible(true);
				}
				else
				{
					Queue<String> q = superQueue.remove();
					String line = q.remove();
					if (line.substring(0, 1).equals("T"))
					{
						startTeleport(line);
					}
					else if (line.substring(0, 1).equals("M"))
					{
						startMoveForward(line);
					}
					else if (line.substring(0, 1).equals("F"))
					{
						startChangeDir(line);
					}
				}
				
			}
			
			
			
		}
	};
	
	Timer timer = new Timer(700, al);
	TwoDArrayList<Tile> tiles;
	int xPointCoord = 0;
	int yPointCoord = 0;
	int direction = 0;
	
	ImageIcon blank = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/tile.jpg");
	ImageIcon wall = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/wall_tile.jpg");
	ImageIcon pointer = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/pointer.jpg");
	ImageIcon left = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/left.jpg");
	ImageIcon right = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/right.jpg");
	ImageIcon down = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/down.jpg");
	ImageIcon goal = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/goal.jpg");
	ImageIcon blue = new ImageIcon("/Users/Keith/Documents/workspace/Teleport Game/src/blue.jpg");
	
	ImageIcon imageDir = pointer;
	
	public GridScreen(TwoDArrayList<Tile> theTiles)
	{
		tiles = theTiles;
		Container container = getContentPane();
		GridLayout gl = new GridLayout(10, 10, 0, 0);
		container.setLayout(gl);
		
		ok2.addActionListener(OAL);
		failure.add(ok2);
		
		failure.setSize(300, 300);
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				tiles.add(i, new Tile(new JLabel(blank), "", true));
				add(tiles.get(i, j).getLabel());
			}
		}
		
		setSize(500, 500);
		setLocation(800, 0);
		setVisible(true);
		
		setGrid();
	
	}
	
	public void setGrid()
	{
		
		changeTile(1, 1, wall, "wall", false);
		changeTile(9, 4, pointer, "pointer", false);
		xPointCoord = 9;
		yPointCoord = 4;
		direction = 0;
		imageDir = pointer;
		changeTile(2, 7, goal, "goal", true); //should be false, will work out later
		changeTile(4, 4, blue, "Blue", true); //should be false, will work out later
	}
	
	public void changeTile(int x, int y, ImageIcon ii, String id, boolean e)
	{
		tiles.get(x, y).changeLabel(ii, id, e);
	}
	
	public void execute(String cmds)
	{
		timer.start();
		int check = 0;
		
		while (check == 0)
		{
			String line = "";
			
			if (cmds.indexOf("\n") == -1)
			{
				check = -1;
				line = cmds;
			}
			else
			{
				line = cmds.substring(0, cmds.indexOf("\n"));
				cmds = cmds.substring(cmds.indexOf("\n") + 1);
			}
			
			executeHelper(line);
			
		}
		
	}
	
	public void executeHelper(String line)
	{
			
			if (line.substring(0, 1).equals("T"))
			{
				moveQueue.add(line);
				superQueue.add(moveQueue);
			}
			else if (line.substring(0, 1).equals("M"))
			{
				moveQueue.add(line);
				superQueue.add(moveQueue);
			}
			else if (line.substring(0, 1).equals("F"))
			{
				dirQueue.add(line);
				superQueue.add(dirQueue);
			}
			else
			{
				//error handle
			}
		
	}
	
	public void teleport(String color)
	{
		for (int i = 0; i < tiles.row; i++)
		{
			for (int j = 0; j < tiles.col; j++)
			{
				ArrayList<Tile> a = tiles.list.get(i);
				Tile t = a.get(j);
				
				if (t.getID().equals(color))
				{
					changeTile(i, j, imageDir, "pointer", false);
					changeTile(xPointCoord, yPointCoord, blank, "", true);
					xPointCoord = i;
					yPointCoord = j;
				}
			}
		}
	
	}
	
	public void startMoveForward(String line)
	{
		timer.start();
		String number = line.substring(13, 14);
		int num = Integer.parseInt(number);
		//moveNum = num;
		for (int i = 0; i < num; i++)
		{
			movingQueue.add("*");
		}
	}
	
	public void startTeleport(String line)
	{
		timer.start();
		String color = line.substring(12, line.length());
		teleport(color);
	}
	
	public void startChangeDir(String line)
	{
		timer.start();
		String dir = line.substring(5, line.length());
		changeDir(dir);
	}
	
	public void moveForward()
	{

		
		int xNextDest = xPointCoord;
		int yNextDest = yPointCoord;
		
			if (direction == 0)
			{
				xNextDest--;
			}
			else if (direction == 90)
			{
				yNextDest++;
			}
			else if (direction == 180)
			{
				xNextDest++;
			}
			else if (direction == 270)
			{
				yNextDest--;
			}
			else
			{
				//error checking
			}
			
			if (tiles.get(xNextDest, yNextDest).isEmpty())
			{
				String nextTile = tiles.get(xNextDest, yNextDest).getID();
				changeTile(xNextDest, yNextDest, imageDir, "pointer", false);
				changeTile(xPointCoord, yPointCoord, blank, "", true);
				xPointCoord = xNextDest;
				yPointCoord = yNextDest;
				if (nextTile.equals("goal"))
				{
					reachedGoal = true;
					final JDialog success = new JDialog(this, "You Win!");
					JButton ok1 = new JButton("Ok");
					ActionListener OkAL = new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							success.dispose();
						}
					};
					ok1.addActionListener(OkAL);
					success.add(ok1);
					
					success.setSize(300, 300);
					success.setVisible(true);
				}
			}
			else
			{
				failure.setVisible(true);
			}
			

			//moveCount++;
	}
	
	public void changeDir(String d)
	{
		int dirNum = 0;
		
		if (d.equals("left"))
		{
			dirNum = 270;
		}
		else if (d.equals("right"))
		{
			dirNum = 90;
		}
		else if (d.equals("backwards"))
		{
			dirNum = 180;
		}
		else
		{
			//error checking
		}
		
		direction += dirNum;
		if (direction >= 360)
		{
			direction -= 360;
		}
		
		if (direction == 0)
		{
			changeTile(xPointCoord, yPointCoord, pointer, "pointer", false);
			imageDir = pointer;
		}
		else if (direction == 90)
		{
			changeTile(xPointCoord, yPointCoord, right, "pointer", false);
			imageDir = right;
		}
		else if (direction == 180)
		{
			changeTile(xPointCoord, yPointCoord, down, "pointer", false);
			imageDir = down;
		}
		else if (direction == 270)
		{
			changeTile(xPointCoord, yPointCoord, left, "pointer", false);
			imageDir = left;
		}
		else
		{
			//error checking
		}
		
	}
	
}
