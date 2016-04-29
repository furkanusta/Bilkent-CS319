package com.oo.conquest;

import java.util.Random;

public class WarManager {

	public WarManager(){

	}

	public TroopManager simulateWar(TroopManager attack, TroopManager defense, User defender, User attacker){
		while(!attack.getTroopList().isEmpty() && !defense.getTroopList().isEmpty()){
			Troop attackTroop = attack.getTroopList().get(0);
			Troop defenseTroop = defense.getTroopList().get(0);

			defenseTroop.setHealthPoint(defenseTroop.getHealthPoint() - (int)(attackTroop.getAttackPoint()*attackTroop.getAttackSpeed()));
			attackTroop.setHealthPoint(attackTroop.getHealthPoint() - (int)(defenseTroop.getAttackPoint()*defenseTroop.getAttackSpeed()));

			if(attackTroop.getHealthPoint() <= 0)
			{
				defender.updateStatistics(1, 0);
				attacker.updateStatistics(0, 1);
				attack.getTroopList().remove(0);
			}
			if(defenseTroop.getHealthPoint() <= 0){
				defender.updateStatistics(0, 1);
				attacker.updateStatistics(1, 0);
				defense.getTroopList().remove(0);
			}
		}
		
		if(attack.getTroopList().isEmpty() && defense.getTroopList().isEmpty()){
			Random rand = new Random();
			int result = rand.nextInt(2);
			if(result == 0){
				defense.getTroopList().add(new Infantry(defender.getInfLevel()));
				return defense;
			}
			else{
				attack.getTroopList().add(new Infantry(attacker.getInfLevel()));
				return attack;
			}
			
		}
		else if(attack.getTroopList().isEmpty())
			return defense;
		else 
			return attack;
	}
}
