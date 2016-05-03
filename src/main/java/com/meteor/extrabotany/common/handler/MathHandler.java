package com.meteor.extrabotany.common.handler;

public class MathHandler {
	public static int max(int a, int b ,int c){
		int d = Math.max(Math.max(a, b),c);
		return d;	
	}
	
	public static float max(float a, float b ,float c){
		float d = Math.max(Math.max(a, b),c);
		return d;	
	}
	
	public static double max(double a, double b ,double c){
		double d = Math.max(Math.max(a, b),c);
		return d;	
	}
	
	public static int min(int a, int b ,int c){
		int d = Math.min(Math.min(a, b),c);
		return d;	
	}
	
	public static float min(float a, float b ,float c){
		float d = Math.min(Math.min(a, b),c);
		return d;	
	}
	
	public static double min(double a, double b ,double c){
		double d = Math.min(Math.min(a, b),c);
		return d;	
	}
}
