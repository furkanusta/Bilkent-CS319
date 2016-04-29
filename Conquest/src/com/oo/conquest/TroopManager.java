package com.oo.conquest;

import java.util.ArrayList;

public class TroopManager {
	private ArrayList<Troop> troopList;

	public TroopManager(){
		troopList = new ArrayList<Troop>();
		troopList.add(new Infantry(0));
	}
	
	public ArrayList<Troop> getTroopList(){
		return troopList;
	}

	public TroopManager getNewTroopManager(int infantryCount, int tankCount, int jetCount){
		if(infantryCount == 0 && tankCount == 0 && jetCount == 0)
			return null;
		else if(infantryCount == getInfantryCount() && tankCount == getTankCount() && jetCount == getJetCount())
			return null;
		else{
			TroopManager newTroopManager = new TroopManager();
			newTroopManager.clear();
			for(int i=0;i<infantryCount;i++){
				for(int j=0;j<troopList.size();j++){
					if(troopList.get(j) instanceof Infantry){
						newTroopManager.addTroop(troopList.get(j));
						troopList.remove(j);
						j = troopList.size();
						break;
					}
				}
			}
			for(int i=0;i<tankCount;i++){
				for(int j=0;j<troopList.size();j++){
					if(troopList.get(j) instanceof Tank){
						newTroopManager.addTroop(troopList.get(j));
						troopList.remove(j);
						j = troopList.size();
						break;
					}
				}
			}
			for(int i=0;i<jetCount;i++){
				for(int j=0;j<troopList.size();j++){
					if(troopList.get(j) instanceof Jet){
						newTroopManager.addTroop(troopList.get(j));
						troopList.remove(j);
						j = troopList.size();
						break;
					}
				}
			}
			return newTroopManager;
		}			
	}
	
	public void clear(){
		troopList.clear();
	}

	public void addTroop(Troop t){
		troopList.add(t);
	}

	public void deleteTroop(Troop t){
		troopList.remove(t);
	}

	public int getInfantryCount(){
		int count = 0;
		for(int i=0;i<troopList.size();i++)
			if(troopList.get(i) instanceof Infantry)
				count++;
		return count;
	}

	public int getTankCount(){
		int count = 0;
		for(int i=0;i<troopList.size();i++)
			if(troopList.get(i) instanceof Tank)
				count++;
		return count;
	}

	public int getJetCount(){
		int count = 0;
		for(int i=0;i<troopList.size();i++)
			if(troopList.get(i) instanceof Jet)
				count++;
		return count;
	}

	public void upgradeTroop(int type){
		if (type==0){
			for(int i=0;i<troopList.size();i++)
				if(troopList.get(i) instanceof Infantry)
					troopList.get(i).upgrade();
		}
		else if (type==1){
			for(int i=0;i<troopList.size();i++)
				if(troopList.get(i) instanceof Tank)
					troopList.get(i).upgrade();
		}
		else {
			for(int i=0;i<troopList.size();i++)
				if(troopList.get(i) instanceof Jet)
					troopList.get(i).upgrade();
		}
	}
}
