package com.oo.conquest;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Region {

	private int id;
	private Rectangle coordinates = new Rectangle(0,0,0,0);
	private int[] neighbor;
	private int ownerId;
	private String imgPath;
	private TroopManager troop_M;

	public Region(int id){
		this.id = id;
		this.ownerId = -1;
		imgPath = "";
		BufferedReader br = null;
		this.setTroopManager(new TroopManager());

		try {
			br = new BufferedReader(new FileReader("data/region.txt"));
			for(int i=0;i<id;i++){
				br.readLine();
			}
			Scanner scan = new Scanner(br.readLine());
			coordinates.x = scan.nextInt();
			coordinates.y = scan.nextInt();
			coordinates.width = scan.nextInt();
			coordinates.height = scan.nextInt();

			int neighborCount = scan.nextInt();

			neighbor = new int[neighborCount];
			for(int i=0;i<neighborCount;i++){
				neighbor[i] = scan.nextInt();
			}
			
			scan.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(int[] neighbor) {
		this.neighbor = neighbor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rectangle getCoordinate() {
		return coordinates;
	}

	public void setCoordinate(Rectangle coordinate) {
		this.coordinates = coordinate;
	}

	public int getOwner() {
		return ownerId;
	}

	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public TroopManager getTroopManager() {
		return troop_M;
	}

	public void setTroopManager(TroopManager troop_M) {
		this.troop_M = troop_M;
	}
}
