package com.oo.conquest;

public class Infantry extends Troop {

	public Infantry(int level){
		super(level);
		attackPoint = new int[]{3, 4, 6};
		healthPoint = new int[]{4, 5, 7};
		attackSpeed = new double[]{2, 2.7, 2.9};
	}
}
