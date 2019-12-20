package com.PFCbalancer.model;

public class PFC {
	
	private int weight;
	private int height;
	private int age;
	
	double bmr;
    int tdee;
	
	private int protein = (weight * 2 * 4);
	private int fat = (tdee / 10 * 2);
	private int carb = (tdee - protein - fat);
		
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
//	
//	public void setPFC(int weight, int totalKal) {
//		this.protein = (weight * 2 * 4);
//		this.fat = (totalKal / 10 * 2);
//		this.carbohydrate = (totalKal - protein - fat);
//	}
	
	public void setPFC() {
		this.bmr = (13.397 * this.weight) + (4.799 * this.height) - (5.677 * this.age) + 88.362;
		this.tdee = (int)(this.bmr * 1.55);
		
		this.protein = (this.weight * 2 * 4);
		this.fat = (this.tdee / 10 * 2);
		this.carb = (this.tdee - this.protein - this.fat);
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getProtein() {
		return protein;
	}
	
	public int getFat() {
		return fat;
	}
	
	public int getCarb() {
		return carb;
	}
	
	
}
