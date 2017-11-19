package com.example.luckycookies;

import java.util.Comparator;

public class DiningSorterByDistance implements Comparator<Dining> {

	@Override
	public int compare(Dining o1, Dining o2) {
		int num = 0;
		if (o1.distance.compareTo(o2.distance) > 0) {
			num = 1;
		} else if (o1.distance.compareTo(o2.distance) < 0) {
			num = -1;
		} 
		return num;
	}
	
	

}
