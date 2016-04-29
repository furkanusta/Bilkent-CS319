package com.oo.conquest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Continent {

	private int continentId;
	private int[] regionArray;
	private int bonusTroopType;
	private int bonusTroopCount;

	public Continent(int continentId){
		this.setContinentId(continentId);

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("data/continent.txt"));
			for(int i=0;i<continentId;i++){
				br.readLine();
			}
			Scanner scan = new Scanner(br.readLine());
			this.bonusTroopType = scan.nextInt();
			this.bonusTroopCount = scan.nextInt();
			int regionCount = scan.nextInt();


			regionArray = new int[regionCount];
			for(int i=0;i<regionCount;i++){
				regionArray[i] = scan.nextInt();
			}
			scan.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Mutators
	public int[] getRegionArray() {
		return regionArray;
	}

	public void setRegionArray(int[] regionArray) {
		this.regionArray = regionArray;
	}

	public int getBonusTroopType() {
		return bonusTroopType;
	}

	public void setBonusTroopType(int bonusTroopType) {
		this.bonusTroopType = bonusTroopType;
	}

	public int getBonusTroopCount() {
		return bonusTroopCount;
	}

	public void setBonusTroopCount(int bonusTroopCount) {
		this.bonusTroopCount = bonusTroopCount;
	}


	public int getContinentId() {
		return continentId;
	}


	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}
}
