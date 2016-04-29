package com.oo.conquest;

public class Jet extends Troop{
	
	public Jet(int level){
		super(level);
		attackPoint = new int[]{12, 14, 16};
		healthPoint = new int[]{6, 8, 10};
		attackSpeed = new double[]{2.5, 2.8, 3.1};
	}
}