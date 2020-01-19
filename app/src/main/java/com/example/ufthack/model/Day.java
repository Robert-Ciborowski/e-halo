package com.example.ufthack.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Day {
	private Date date;
	private ArrayList<VapeEvent> vapeEvents;

	public Day() {
		DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd");
		Date date = new Date();
	}

	public Date getDate() {
		return date;
	}
	
	public VapeEvent getVapeEvent(int index) {
		return vapeEvents.get(index);
	}
	
	public int getVapeEventSize() {
		return vapeEvents.size();
	}
	
	public void addVapeEvent(String time, double nicotine, int puffs) {
		vapeEvents.add(new VapeEvent(time,nicotine,puffs));
	}
}