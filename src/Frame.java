import javax.swing.*;

import java.awt.*;


public class Frame extends JFrame {
	
	public static String title = "Tower Defense by Andrew Barsoom";
	public static Dimension size = new Dimension (632,490);
	 public static boolean start = false;
	public Frame()
	{
		
		setTitle(title);
		setSize(size);
		setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    init();
		
	}
	
	
	public void init() 
	{
		
		setLayout(new GridLayout(1,1,0,0));
		
		Screen screen = new Screen(this);
		add(screen);
		
		
		setVisible(true);
	}
	
	public static void main (String args[] )
	{
		
		Frame frame = new Frame ();
		JTextArea textArea = new JTextArea();
		 textArea.setText("\n \n \n \n \n \nHow to Play\nSelect Towers and position them strategically on the map! \nPrevent any monsters from escaping into the cave!\nYou are Given 100 Health points, for every monster that escapes you lose 1 health.\n \n \n \n \nThere are 3 Levels: \n Level 1 : Kill 50 Monsters \n Level 2 : kill 75 Monsters \n Level 3: kill 100 monsters! "); 
		 textArea.setFont(new Font ("Courier New" , Font.BOLD, 15));
		  textArea.setRows(25);
		  textArea.setColumns(65);
		  textArea.setOpaque(false);
		  textArea.setEditable(false);
		  textArea.setLineWrap(true);
		  textArea.setWrapStyleWord(true);
		  JOptionPane.showMessageDialog(null, textArea, "Truncated!", JOptionPane.INFORMATION_MESSAGE);
		
		  
	}
	

	
	
}
