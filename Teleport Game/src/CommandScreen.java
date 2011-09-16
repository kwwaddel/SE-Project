
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CommandScreen extends JFrame {
	
	public CommandScreen(final GridScreen gs)
	{
		Container container = getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		container.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JButton teleport = new JButton("Teleport");
		gbl.setConstraints(teleport, gbc);
		
		JLabel teleportColor = new JLabel("Teleport Color: ");
		gbc.gridx = 1;
		gbl.setConstraints(teleportColor, gbc);
		
		Vector<String> colors = new Vector<String>();
		colors.add("Blue");
		colors.add("Red");
		colors.add("Purple");
		final JComboBox tColor = new JComboBox(colors);
		gbc.gridx = 2;
		gbl.setConstraints(tColor, gbc);
		
		JButton forward = new JButton("Forward");
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbl.setConstraints(forward, gbc);
		
		JLabel forwardNum = new JLabel("Number of Spaces to Move Forward: ");
		forwardNum.setSize(100, 100);
		gbc.gridx = 1;
		gbl.setConstraints(forwardNum, gbc);
		
		final JTextField fNum = new JTextField("0", 2);
		gbc.gridx = 2;
		gbl.setConstraints(fNum, gbc);
		
		JButton left = new JButton("Left");
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbl.setConstraints(left, gbc);
		
		JButton right = new JButton("Right");
		gbc.gridy = 3;
		gbl.setConstraints(right, gbc);
		
		JButton backwards = new JButton("Backwards");
		gbc.gridy = 4;
		gbl.setConstraints(backwards, gbc);
		
		final JTextArea commands = new JTextArea("", 16, 8);
		gbc.ipadx = 100;
		gbc.gridy = 5;
		gbc.gridx = 1;
		gbl.setConstraints(commands, gbc);
		
		JButton commit = new JButton("Commit");
		gbc.gridy = 6;
		gbc.ipadx = 0;
		gbl.setConstraints(commit, gbc);
		
		JButton reset = new JButton("Reset");
		gbc.gridy = 7;
		gbl.setConstraints(reset, gbc);
		
		ActionListener TAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!commands.getText().equals(""))
					commands.append("\n");
				
				String color = (String)tColor.getModel().getSelectedItem();
				commands.append("Teleport to " + color);
				
			}
		};
		
		teleport.addActionListener(TAL);
		
		ActionListener FAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!commands.getText().equals(""))
					commands.append("\n");
				commands.append("Move forward " + fNum.getText() + " spaces");
				fNum.setText("0");
			}
		};
		
		forward.addActionListener(FAL);
		
		ActionListener LAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!commands.getText().equals(""))
					commands.append("\n");
				commands.append("Face left");
			}
		};
		
		left.addActionListener(LAL);
		
		ActionListener RAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!commands.getText().equals(""))
					commands.append("\n");
				commands.append("Face right");
			}
		};
		
		right.addActionListener(RAL);
		
		ActionListener BAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!commands.getText().equals(""))
					commands.append("\n");
				commands.append("Face backwards");
			}
		};
		
		backwards.addActionListener(BAL);
		
		ActionListener CAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//error checking needed
				gs.execute(commands.getText());
			}
		};
		
		commit.addActionListener(CAL);
		
		ActionListener ReAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				commands.setText("");
			}
		};
		
		reset.addActionListener(ReAL);
		
		add(teleport);
		add(teleportColor);
		add(tColor);
		
		add(forward);
		add(forwardNum);
		add(fNum);
		
		add(left);
		add(right);
		add(backwards);
		
		add(commands);
		add(commit);
		add(reset);
		
		setSize(800, 800);
		setVisible(true);
		
	}

}
	
