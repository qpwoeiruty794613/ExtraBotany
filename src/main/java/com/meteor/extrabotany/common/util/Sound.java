package com.meteor.extrabotany.common.util;

public class Sound {
	public String name;
	public float volume;
	
	public static Sound ATTACK_SHADOW = new Sound("botania:attack.shadow", 0.6F);
	public static Sound ATTACK_FROST = new Sound("botania:attack.frost", 0.6F);
	
	public Sound(String name, float volume){
		this.name = name;
		this.volume = volume;
	}
	
	public String getName(){
		return name;
	}
	
	public float getVolume(){
		return volume;
	}
}
