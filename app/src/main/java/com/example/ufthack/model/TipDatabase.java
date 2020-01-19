package com.example.ufthack.model;

import java.util.ArrayList;


public class TipDatabase {

	private ArrayList<Tip> tips;
	
	public TipDatabase() {
	}
	
	public void addTip(Tip tip) {
		tips.add(tip);
	}
	
	public String getRandomTip() {
		int randIndex = (int)(Math.random()*(tips.size()+1));
		return tips.get(randIndex).getAdvice();
	}

}
