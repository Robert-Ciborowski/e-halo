package com.example.ufthack.model;

public class User {
	private Sex sex;
	private String name;
	private String username;
	private String password;
	private String email;
	private String userId;
	private int age;
	private int streak;
	private int max_streak = 0;
	private Rank rank;
	private int currNic;
	private int nicGoal;

	/**
	 * Constructor initializes the variables
	 * 
	 * @param name
	 * @param sex
	 * @param age
	 * @param username
	 * @param password
	 * @param email
	 * @param streak
	 * @param rank
	 * @param currNic
	 * @param nicGoal
	 */

	public User(String name, Sex sex, String username, String password, String email, int streak, int age, Rank rank,
			int currNic, int nicGoal) {
		this.sex = sex;
		this.name = name;
		this.age = age;
		this.username = username;
		this.password = password;
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

	public String getName() {
		return name;
	}

	public Sex getSex() {
		return sex;
	}

	public int getAge() {
		return age;
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