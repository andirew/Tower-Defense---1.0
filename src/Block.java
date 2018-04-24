import java.awt.*;

public class Block extends Rectangle{
	public Rectangle towerSquare;
	public int towerSquareSize = 130;
	public int groundID;
	public int airID;
	public int shootMob = -1;
	public static int loseTime = 100;
	public int loseFrame =0;
	public boolean shooting = false;
	
	public Block (int x, int y, int width, int height, int groundID, int airID)
	{
		setBounds(x, y, width, height);
		towerSquare = new Rectangle(x -(towerSquareSize/2), y - (towerSquareSize/2), width + (towerSquareSize), height + (towerSquareSize));
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(Screen.Tile_ground[groundID], x, y, width, height,null);
		
		if (airID != Value.Top)
		{
			g.drawImage(Screen.Tile_air[airID], x, y, width, height,null);
			
			
			
			}
		}
	
	public void physic(){
		if(shootMob != -1 && towerSquare.intersects(Screen.mobs[shootMob])){
			shooting=true;
			
		}else {
		shooting = false;
		}
		
		if (!shooting){
			if (airID == Value.airTowerLaser ){
				for(int i=0 ; i<Screen.mobs.length;i++){
					if(Screen.mobs[i].inGame){
						if(towerSquare.intersects(Screen.mobs[i])){
							shooting=true;
							shootMob = i;
							//System.out.println(i);
						}
					}
				}
			}
		}

		if(shooting){
			if(loseFrame >= loseTime){
				
				Screen.mobs[shootMob].loseMobhealth(1);
				
				loseFrame =0;
			}else{
				loseFrame+=1;
			}
			
			if(Screen.mobs[shootMob].isDead()){
				
				
				shooting = false;
				shootMob = -1;
				
				
				
				Screen.hasWon();
				
			}
		}
   }

	
	
	public void fight (Graphics g){
		if (Screen.isDebug){
			if (airID == Value.airTowerLaser){
				g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			}
		}
			if(shooting){
				g.setColor(new Color(255,255,0));
				g.drawLine(x + (width/2), y+ (height/2), Screen.mobs[shootMob].x + (Screen.mobs[shootMob].width/2), Screen.mobs[shootMob].y + (Screen.mobs[shootMob].height/2) );
				
			}
		
	}
}

	

