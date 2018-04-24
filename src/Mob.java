import java.awt.*;

public class Mob extends Rectangle{
	public int xCord, yCord;
	public int health;
	public int healthSpace = 3, healthHeight =6;
	public int mobWalk = 0;
	public int upward = 0, downward = 1, right = 2, left = 3;
	public int direction = right; 
	public int mobSize = 52;
	public int mobID = Value.mobAir;
	
	public boolean inGame = true;
	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasLeft = false;
	public boolean hasRight = false;
	
	public static int norepeat = 150;
	
	public Mob() {
		
	}
	public void spawnMob(int mobID){
		for (int y= 0; y< Screen.room.block.length; y++){
			if (Screen.room.block[y][0].groundID == Value.groundRoad){
				setBounds(Screen.room.block[y][0].x , Screen.room.block[y][0].y, mobSize, mobSize);
				xCord = 0;
				yCord = y;
			}
		}
		
		
		this.mobID = mobID;
		this.health = mobSize;
		inGame = true;
		
	}
	public void deleteMob(){
	inGame = false;
	mobWalk=0;
	}
	
	

		public void loseHealth (){
			Screen.health -=1;
		}
	public int walkFrame = 0;
	public static int walkSpeed = 3;
	
	public void physic(){
		if (walkFrame >= walkSpeed){
			if(direction == right){
			x += 1;
			}else if(direction == upward ){
				y -= 1;
			}else if( direction == downward){
				y += 1;
			}else if( direction == left){
				x -= 1;
			}
			
			mobWalk +=1;
			
			if( mobWalk == Screen.room.blockSize){
				if(direction == right){
					xCord += 1;
						hasRight = true;
				}else if(direction == upward ){
						yCord -= 1;
						hasUpward = true;
				}else if( direction == downward){
						yCord += 1;
						hasDownward = true;
				}else if (direction == left){
					xCord -= 1; 
					hasLeft = true;
				}
				
				
				
				if(!hasUpward){
					try{
						if(Screen.room.block[yCord + 1][xCord].groundID == Value.groundRoad){
							direction = downward;
						}
					}catch(Exception e){}
					
				}
				if (!hasDownward){
					try{
					if(Screen.room.block[yCord -1][xCord].groundID == Value.groundRoad){
						direction = upward;
					}
					}catch(Exception e){}
				}
				
				if (!hasLeft){
					try{
						if(Screen.room.block[yCord][xCord+ 1].groundID == Value.groundRoad){
							direction = right;
						}
					}catch(Exception e){}
				}
				if (!hasRight){
					try{
						if(Screen.room.block[yCord][xCord - 1].groundID == Value.groundRoad){
							direction = left;
						}
					}catch(Exception e){}
				}
				
				
				if(Screen.room.block[yCord][xCord].airID == Value.airCave){
					deleteMob();
					Screen.health -=1;
					checkDeath();
				}
				
				hasUpward = false;
				hasDownward = false;
				hasLeft = false;
				hasRight = false;
				mobWalk = 0;
			}
			walkFrame = 0;
		}else{
			walkFrame += 1;
		}
		
	}
	public void loseMobhealth (int amount){
		health -=amount;
		checkDeath();
		
	}
	public void checkDeath(){
		if (health == 0){
			deleteMob();
			Screen.coinage += 3;
			Screen.killed +=1;
			
		}
	}
	public boolean isDead(){
		if(inGame){
			
			return false;
			
		}else{
			
			return true;
		}
	}
	
	
	public void draw(Graphics g){
	
			g.drawImage(Screen.Tile_mob[mobID], x, y, width, height, null);
			
			//health
			g.setColor(new Color (180,50,50));
			g.fillRect(x, y - (healthSpace + healthHeight), width	, healthHeight);
			
			g.setColor(new Color (50,180,50));
			g.fillRect(x, y - (healthSpace + healthHeight), health	, healthHeight);
			
			g.setColor(new Color(0,0,0));
			g.drawRect(x, y - (healthSpace + healthHeight), health -1	, healthHeight -1 ); 
			
		
	}
	
}
