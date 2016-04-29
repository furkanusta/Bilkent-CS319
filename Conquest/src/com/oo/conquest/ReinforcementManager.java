package com.oo.conquest;

public class ReinforcementManager {
	public ReinforcementManager(){

	}

	public void reinforce(Region destination, TroopManager troop){
		for(int i=0;i<troop.getTroopList().size();i++)
			destination.getTroopManager().getTroopList().add(troop.getTroopList().get(i));
	}
}
