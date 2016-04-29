package com.oo.conquest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MapManager {
	private final int regionCount = 12;
	private final int continentCount = 6;
	private Continent[] continentArray = new Continent[continentCount];
	private Region[] regionArray = new Region[regionCount];

	public MapManager(){
		for(int i=0;i<continentCount;i++){
			continentArray[i] = new Continent(i);
		}
		for(int i=0;i<regionCount;i++){
			regionArray[i] = new Region(i);
		}
	}

	public void initialize(int userCount){
		Random r = new Random();
		ArrayList<Region> temp = new ArrayList<Region> (Arrays.asList(regionArray));

		for(int i=0;i<regionArray.length/userCount;i++){
			for(int j=0;j<userCount;j++){
				int index = r.nextInt(temp.size());
				regionArray[temp.get(index).getId()].setOwner(j);
				temp.remove(index);
			}
		}
		
		for(int i=0;i<userCount;i++){
			for(int j = 0; j < 5; j++){
				int index = r.nextInt(regionArray.length);
				if(regionArray[index].getOwner() == i){
					regionArray[index].getTroopManager().addTroop(new Infantry(0));
				}
				else
				{
					j--;
				}
			}
		}
	}
	
	public Continent[] getContinentArray(){
		return continentArray;
	}

	public Region[] getRegionArray(){
		return regionArray;
	}
}
