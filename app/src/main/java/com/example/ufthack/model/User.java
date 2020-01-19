package com.example.ufthack.model;

import java.util.ArrayList;

public class User {
	private String email = "", password = "", phone = "";

	public User(String email, String password, String name , String phone){
		this.email = email;
		this.password = password;
		this.username = name;
		this.phone = phone;
	}

	public void setName(String name){
		this.username = name;
	}

	public String getName() {return username;}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public int getMax_streak() {
		return max_streak;
	}

	private String username = "";
	private String userId = "";
	private int streak = 0;
	private int max_streak = 0;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public void setMax_streak(int max_streak) {
		this.max_streak = max_streak;
	}

	public void setNicGoal(int nicGoal) {
		this.nicGoal = nicGoal;
	}

	private Rank rank;
	private int currNic = 0;
	private int nicGoal = 0;
	private int score = 0;
	public ArrayList<VapeEvent> events;

	public ArrayList<VapeEvent> getEventsByDate(int day, int month, int year) {
		ArrayList<VapeEvent> e = new ArrayList<>();

		for (VapeEvent e2 : events) {
			System.out.println("A: " + e2.toString());
			String[] s = e2.getTime().split("-");
			if (Integer.parseInt(s[2]) == day && Integer.parseInt(s[3]) == month && Integer.parseInt(s[4]) == year) {
				e.add(e2);
			} else {
			}
		}

		return e;
	}

	public User() {
		// Default constructor required for calls to DataSnapshot.getValue(User.class)
		username = "";
		email = "";
		events = new ArrayList<>();
	}

	public User(String username, String email) {
		// Default constructor required for calls to DataSnapshot.getValue(User.class)
		this.username = username;
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addEvent(VapeEvent event){
		events.add(event);

	}

	public boolean isSetUp() {
		return !email.equals("") && !username.equals("");
	}

	public String toString() {
		String s = "User(" + email + " " + username;

		for (VapeEvent e : events) {
			s += "\n    " + e.toString();
		}

		s += "\n";
		return s;
	}

	public User(String name, Sex sex, String username, String password, String email, int streak, int age, Rank rank,
			int currNic, int nicGoal) {
		this.username = username;
		this.email = email;
		this.streak = streak;
		this.userId = randHex();
		this.rank = Rank.NONE;
		this.currNic = currNic;
		this.nicGoal = nicGoal;

	}

	public String randHex() {
		String[] nums = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		String hex = "";
		int randIndex;
		for (int i = 0; i < 20; i++) {
			randIndex = (int) Math.random() * 16;
			hex += nums[randIndex];
		}

		return hex;
	}

	public int getStreak() {
		return streak;
	}

	public void incStreak() {
		streak += 1;
	}

	public String getUserId() {
		return userId;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public int getCurrNic() {
		return currNic;
	}
	
	public void setCurrNic(int currNic) {
		this.currNic = currNic;
	}
	
	public int getNicGoal() {
		return nicGoal;
	}
	
}