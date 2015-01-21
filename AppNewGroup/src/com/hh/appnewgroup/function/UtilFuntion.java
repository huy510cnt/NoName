package com.hh.appnewgroup.function;

public class UtilFuntion {
	public static int getRandomIndex(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
}
