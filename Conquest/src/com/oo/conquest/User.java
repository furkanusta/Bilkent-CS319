package com.oo.conquest;

public class User {
	private int id;
	private int color;
	private String name;
	private int killCount;
	private int deathCount;
	private int numberOfRegions;
	private int experiencePoints;
	private int infLevel, tankLevel, jetLevel;

	public User(int id, int color, String name, int kill, int death, int numberOfRegions, int experience){
		this.id = id;
		this.color = color;
		this.name = name;
		this.setKillCount(kill);
		this.setDeathCount(death);
		this.setNumberOfRegions(numberOfRegions);
		this.setExperiencePoints(experience);
		this.infLevel = 0;
		this.tankLevel = 0;
		this.jetLevel = 0;
	}

	public void updateStatistics(int killCount, int deathCount){
		this.killCount += killCount;
		this.deathCount += deathCount;
		
		setExperiencePoints((killCount-deathCount)*100);
	}

	//Mutators
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getKillCount() {
		return killCount;
	}

	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	public int getNumberOfRegions() {
		return numberOfRegions;
	}

	public void setNumberOfRegions(int numberOfRegions) {
		System.out.println(getName() + " " + this.numberOfRegions + " idi " + numberOfRegions + "oldu!");
		this.numberOfRegions = numberOfRegions;
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints += experiencePoints;
		if(this.experiencePoints < 0)
			this.experiencePoints = 0;
	}

	public int getInfLevel() {
		return infLevel;
	}

	public void setInfLevel(int infLevel) {
		this.infLevel = infLevel;
	}

	public int getTankLevel() {
		return tankLevel;
	}

	public void setTankLevel(int tankLevel) {
		this.tankLevel = tankLevel;
	}

	public int getJetLevel() {
		return jetLevel;
	}

	public void setJetLevel(int jetLevel) {
		this.jetLevel = jetLevel;
	}
}
