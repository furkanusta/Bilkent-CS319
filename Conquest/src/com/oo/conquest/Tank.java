package com.oo.conquest;

public class Tank extends Troop{
	public Tank(int level){
		super(level);
		attackPoint = new int[]{4,6,7};
		healthPoint = new int[]{12, 14, 17};
		attackSpeed = new double[]{1.5, 1.7, 2.1};
	}
}
