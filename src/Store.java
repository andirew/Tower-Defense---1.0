import java.awt.*;
public class Store {
	public static int shopWidth = 2;
	public static int buttonSize = 40;
	public static int cellspace = 2;
	public static int awayfromroom = 20;
	public static int iconSize = 17;
	public static int iconspace = 6;
	public static int iconTextY = 10;
	public static int itemIn = 4;
	public static int heldID = -1;
	public static int realID = -1;
	public static int[] buttonID = { Value.airTowerLaser,Value.airTrashCan }; // assign a variable form Value class
	public static int[] buttonPriceONE = {10,0};	// Set Values for Shops
	
	
	public Rectangle[] button = new Rectangle [shopWidth];
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	
	public boolean holdsItem = false;
	
	public Store(){
		define();
	}
	
	public void click(int mouseButton){
		if(mouseButton == 1){ // If left clicked
			for (int i=0; i<button.length; i++){ // array
				if(button[i].contains(Screen.mse)){// If mouse button contains inside the button 1
					if(buttonID[i]!=Value.Top){ // if its not on anything under Top
						if (buttonID[i] == Value.airTrashCan){// if ontop of Trashcan picture, delete
							holdsItem = false; // delete item
							heldID = Value.Top;
						}else{
							heldID = buttonID[i];
							realID = i;
							holdsItem = true;
							
						}
				   }
				}
			}
			
			if (holdsItem){ // if holding item
				if(Screen.coinage >= buttonPriceONE[realID]){ // if you have enough coins
					
					for(int y = 0; y<Screen.room.block.length; y++){ // y value
						
						for(int x = 0; x<Screen.room.block[0].length; x++){ // xvalue
							if (Screen.room.block[y][x].contains(Screen.mse)){ // if Room contains mouse curse
								
								if(Screen.room.block[y][x].groundID != Value.groundRoad && Screen.room.block[y][x].airID == Value.Top){
									
									Screen.room.block[y][x].airID = heldID; // Place the tower on Mouse position
									Screen.coinage -= buttonPriceONE[realID]; // Subtract coins
									}
									
								}
							}
						}
					}
				}
			}
		}
	

	
	public void define(){
		
		// Defines Store, health, coins positions
		for (int i = 0; i <button.length; i++){
			button[i] = new Rectangle((Screen.myWidth/2) - ((shopWidth*(buttonSize+cellspace))/2 ) + ((buttonSize +cellspace)  * i), 422, buttonSize, buttonSize);
			
		}
		
		buttonHealth = new Rectangle(Screen.room.block[0][0].x-1, button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x-1, button[0].y + button[0].height - iconSize, iconSize, iconSize);
	}
	public void draw(Graphics g){
		
		
		// Draw Graphics and define them for all store icons, and health, coins.
		for (int i = 0; i <button.length; i++){
			if (button[i].contains(Screen.mse)){
				g.setColor(new Color (255,255,255,100));
				g.fillRect(button[i].x, button[i].y, button[i].width,  button[i].height);
			}
			g.drawImage(Screen.Tile_res[0],button[i].x, button[i].y, button[i].width,  button[i].height, null);
			if (buttonID[i] != Value.Top)g.drawImage(Screen.Tile_air[buttonID[i]], button[i].x+itemIn, button[i].y+itemIn, button[i].width - (itemIn*2),  button[i].height -(itemIn*2), null);
			if (buttonPriceONE[i] > 0 ) {
				g.setColor(new Color(255,255,255));
				g.setFont(new Font ("Courier New", Font.BOLD,14));
				g.drawString("$" +  buttonPriceONE[i] ,button[i].x+itemIn, button[i].y+itemIn + 10);
			}
		
		}
		g.drawImage(Screen.Tile_res[1],buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.Tile_res[2],buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.setColor (new Color (255,255,255));
		g.drawString("" + Screen.health, buttonHealth.x + buttonHealth.width + iconspace , buttonHealth.y + iconTextY);
		g.drawString("" + Screen.coinage, buttonCoins.x + buttonCoins.width + iconspace , buttonCoins.y + iconTextY);
		
		
		//=====================================================
		if(holdsItem){
			
			g.drawImage(Screen.Tile_air[heldID],Screen.mse.x - ((button[0].width - (itemIn*2))/2)+ itemIn, Screen.mse.y-((button[0].width - (itemIn*2))/2)+ itemIn, button[0].width - (itemIn*2),  button[0].height -(itemIn*2), null);
			
		}
		
	}	
}
