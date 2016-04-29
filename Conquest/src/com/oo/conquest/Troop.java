package com.oo.conquest;

public class Troop {
	protected int[] healthPoint;
	protected int[] attackPoint;
	protected double[] attackSpeed;
	protected int level = 0;
	private final int MAX_LEVEL = 3;
	
	public Troop(int level){
		this.level = level;
	}
	
	public void upgrade(){
		if(level < MAX_LEVEL)
			level++;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getHealthPoint() {
		return healthPoint[level];
	}
	public void setHealthPoint(int healthPoint) {
		this.healthPoint[level] = healthPoint;
	}
	public int getAttackPoint() {
		return attackPoint[level];
	}
	public void setAttackPoint(int attackPoint) {
		this.attackPoint[level] = attackPoint;
	}
	public double getAttackSpeed() {
		return attackSpeed[level];
	}
	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed[level] = attackSpeed;
	}

}
