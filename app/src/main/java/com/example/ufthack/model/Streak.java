package com.example.ufthack.model;

import java.util.Calendar;

public class Streak {
	private Calendar startTime;
	private Calendar endTime;
	private Calendar currTime;

	public Streak() {
		startTime = Calendar.getInstance();
	}

	public void updateCurrTime(){
		endTime = Calendar.getInstance();
		currTime.setTimeInMillis(endTime.getTimeInMillis() - startTime.getTimeInMillis());
	}
	
	
	public void restartStreak() {
		startTime = Calendar.getInstance();
		currTime.setTimeInMillis(0);
	}
	
	public String getCurrTime(){
		String days = Integer.toString((int)(currTime.YEAR*365.25 +(currTime.MONTH-1)*30.4167+currTime.DAY_OF_MONTH));
		String hours = Integer.toString(currTime.HOUR_OF_DAY);
		String mins = Integer.toString(currTime.MINUTE);
		String secs = Integer.toString(currTime.SECOND);
		return days+":"+hours+":"+mins+":"+secs;
	}
	
}