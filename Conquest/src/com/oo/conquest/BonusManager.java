package com.oo.conquest;

import java.util.Random;

public class BonusManager {

	public BonusManager(){

	}

	public void checkBonusCondition(MapManager map_M, User user, int turnCount){
		for(int i=0;i<map_M.getContinentArray().length;i++){
			boolean isAllSame = true;
			for(int j=0;j<map_M.getContinentArray()[i].getRegionArray().length;j++){
				if(map_M.getRegionArray()[map_M.getContinentArray()[i].getRegionArray()[j]].getOwner() != user.getId())
					isAllSame = false;
			}
			if(isAllSame){
				Random r = new Random();
				int index = 0;
				do{
					index = r.nextInt(12);
				}
				while(map_M.getRegionArray()[index].getOwner() != user.getId());
				for(int k = 0; k<map_M.getContinentArray()[i].getBonusTroopCount();k++)
					if(map_M.getContinentArray()[i].getBonusTroopType() == 0){
						map_M.getRegionArray()[index].getTroopManager().addTroop(new Infantry(user.getInfLevel()));
					}
					else if(map_M.getContinentArray()[i].getBonusTroopType() == 1){
						map_M.getRegionArray()[index].getTroopManager().addTroop(new Tank(user.getTankLevel()));
					}
					else{
						map_M.getRegionArray()[index].getTroopManager().addTroop(new Jet(user.getJetLevel()));
					}
			}
		}
		
		Random r = new Random();
		int index = 0;
		do{
			index = r.nextInt(12);
		}
		while(map_M.getRegionArray()[index].getOwner() != user.getId());
			if(turnCount < 5){
				map_M.getRegionArray()[index].getTroopManager().addTroop(new Infantry(user.getInfLevel()));
			}
			else if(turnCount < 10){
				map_M.getRegionArray()[index].getTroopManager().addTroop(new Tank(user.getTankLevel()));
			}
			else{
				map_M.getRegionArray()[index].getTroopManager().addTroop(new Jet(user.getJetLevel()));
			}

	}

}
