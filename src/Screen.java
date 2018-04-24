import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.*;
import java.io.*;

public class Screen extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	
	public static Image[] Tile_ground = new Image[100];// Tile Images
	public static Image[] Tile_air = new Image[100];// Tile Top Images
	public static Image[] Tile_res = new Image [100];// background
	public static Image[] Tile_mob = new Image[100];// Monster Images
	public static int mobHealth = 100;
	
	public static int myWidth, myHeight;// Window Width and Height
	
	public static int coinage = 30, health = 50; // User Coins and health
	public static int killed = 0, killsToWin, level = 1 , maxlevel = 3;// Objective
	public static int winTime = 4000, winFrame = 0;
	
	public static boolean isFirst = true;// Game is on
	public static boolean isWin = false;
	
	public static Point mse = new Point(0,0);// Cursor Point
	
	
	public static Room room;// Room class
	public static Save save;// Save class
	public static Store store;// Store Class
	
	public static Mob[] mobs = new Mob[200];// Define array for number of mobs

	public static boolean isDebug = false;// Debug mode for Tower Range
	
	public Screen(Frame frame) 
	{
		frame.addMouseListener(new KeyHandel());// Add mouse listeners
		frame.addMouseMotionListener(new KeyHandel());
		thread.start();
	}
	
	public static void hasWon(){
		if(killed == killsToWin){
			isWin = true;
			killed = 0;
			coinage = 0;
			i = 0;
		}
	}
	
	
	public void define() // Define location of Images
	{
		room = new Room();
		save = new Save();
		store = new Store();
		coinage = 30;
		health = 50;
		
		
		for(int i=0; i<Tile_ground.length; i++){ // Array
			Tile_ground[i] = new ImageIcon("Res/Tile_ground.png").getImage(); // Get image from directory
			Tile_ground[i] = createImage(new FilteredImageSource(Tile_ground[i].getSource(), new CropImageFilter(0,26*i,26,26))); // Create Image and crop
		}
		for(int i=0; i<Tile_air.length; i++){//Array
			Tile_air[i] = new ImageIcon("Res/Tile_air.png").getImage();//Get image from directory
			Tile_air[i] = createImage(new FilteredImageSource(Tile_air[i].getSource(), new CropImageFilter(0,26*i,26,26))); // Create image and crop
		}
		
		
		Tile_res[0] = new ImageIcon("res/cell.png").getImage(); // Place Image
		Tile_res[1] = new ImageIcon ("res/heart.png").getImage();// Place Image
		Tile_res[2] = new ImageIcon ("res/coin.png").getImage();// Place Image
		
		Tile_mob[0] = new ImageIcon ("res/mob1.png").getImage();// Place Image
		
		save.loadSave(new File("Save/misson" + level + ".andrew"));// Load Fist Misson
		
		for (int j = 0; j < mobs.length; j++){ // Spawn the mobs
			mobs [j] = new Mob();
			mobs[j].spawnMob(0);
		}
	}
	
	
	public void paintComponent (Graphics g)
	{
		if(isFirst ) // Start get
		{
			myWidth= getWidth();// Get width
			myHeight = getHeight();// get height
			define();// run define()
			
			isFirst = false;// Set game to not start
			
		}
		g.setColor (new Color(95,95,95));// Set room color
		
		g.fillRect(0, 0, getWidth(), getHeight());// Fill Color 
		g.setColor(new Color(0,0,0));// set New color black
		
		g.drawLine(room.block[0][0].x-1, 0, room.block[0][0].x-1, room.block[room.worldHeight - 1][0].y + room.blockSize );// Drawing the left line.
		g.drawLine(room.block[0][room.worldWidth -1].x + room.blockSize, 0, room.block[0][room.worldWidth - 1].x + room.blockSize, room.block[room.worldHeight - 1][0].y + room.blockSize );// Drawing the Right line.
		g.drawLine(room.block[0][0].x, room.block [room.worldHeight -1][0].y + room.blockSize, room.block[0][room.worldWidth -1].x+ room.blockSize, room.block [room.worldHeight -1][0].y + room.blockSize); // Drawing Bottom line
		
		room.draw(g); // Drawing the room
		
		for (int i=0; i<mobs.length; i++){ // Array
			
			if (mobs[i].inGame){// If monsters are ingame, draw new monster
				mobs[i].draw(g);
				
			}
		}
		
		store.draw(g);// Drawing the store
		if (health <1) { // If health is depleted, lose game
			g.setColor( new Color (240,20,20));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(255,255,255));
			g.setFont(new Font ("Courier New" , Font.BOLD, 14));
			g.drawString("Game over! Thanks for playing!", 10, 10);
			}
		
		if (isWin){
			g.setColor(new Color (255,255,255));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(0,0,0));
			g.setFont(new Font ("Courier New" , Font.BOLD, 14));
			if (level == maxlevel){
				g.drawString("You won the whole game!!", 10, 20);
			}else {
				g.drawString("You won! congratulations! Please wait for the next level..", 10, 20);

			}
		}
		
		// This block of code is for several Bug workaround fixed
		
		
			if (i < 10){
				Mob.walkSpeed = 0;
			}
			if(i > 1){
				Mob.walkSpeed = 10;
			}
			
				
			
		if ( level == 1){
			if (i == 1){
				health = 50;
				spawnTime = 12000;
			}
			if (i > 20){
				spawnTime = 1000;
				Block.loseTime = 150;
			}
			
			if ( i > 35){
				Mob.walkSpeed = 8;
				Block.loseTime = 250;
			}
		}
		if ( level == 2){
			if ( i == 2){
				Block.loseTime = 75;
			}
			if (i > 20){
				spawnTime = 1000;
				Block.loseTime = 150;
			}
			
			if ( i > 35){
				
				Block.loseTime = 200;
			}
			
		}
		if ( level == 3){
			if ( i == 2){
				Block.loseTime = 75;
			}
			if (i > 20){
				spawnTime = 1000;
				Block.loseTime = 150;
			}
			
			if ( i > 35){
				Mob.walkSpeed = 5;
				
				Block.loseTime = 200;
			}
			if ( i> 45){
				
				Mob.walkSpeed = 5;
				Block.loseTime = 215;
			}
			
		}
	
		
		
			
		
				
		//============
	}
	/// SPAWN MOBS///////////////
	public int spawnTime ,spawnFrame = 0; // Set time frame between each mob
	public static  int i = 0;
	public void mobSpawner(){
	
		
		if(spawnFrame >= spawnTime){
			
				spawnTime = 1600;
				for (i++;i<mobs.length;){
					System.out.println(killed);
					if (!mobs[i].inGame){
						mobs[i].spawnMob(0);
						
					}
					break;
				}
					spawnFrame = 0;
			
				}else{
					spawnFrame += 1;
				}
		   }
	
	
	//==================================
	public void run()
	{
	
		while (true) 
		{
			
			if(!isFirst && health >0 && !isWin ) // If still alive
			{
				
				room.physic();// run Room physic for mob pathing
				
				
				mobSpawner();// Run monster spawning
					
				for (int i=0; i<mobs.length; i++){ // Array 
					if (mobs[i].inGame){ // check if ingame
						mobs[i].physic();// run specific array pathing
						
					}
				}
			}else{
				if(isWin){
					if( winFrame >= winTime){
						if(level == maxlevel){
							System.exit(0);
						}else{
							level +=1;
							define();
							isWin = false;
						
						}
						winFrame = 0;
					}else{
						winFrame +=1;
					}
				}
			}
			
			repaint ();
			
			try{
				Thread.sleep(1);
			}catch (Exception e){}
		}
	}
}
