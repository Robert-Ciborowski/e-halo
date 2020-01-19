package com.example.ufthack.database;

import com.example.ufthack.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class League {
    private ArrayList<User> user_list = new ArrayList<>();
    private String dateStarted = "";
    public static final int USERS_PER_LEADERBOARD = 10;

    public League(){}

    public void append(User user){
        user_list.add(user);
    }

    public void append(ArrayList<User> users){
        for (User user : users) {
            append(user);
        }
    }

    public void start(Date date){

        update();
    }

    public void update() {
        Collections.sort(user_list, new UserComparator());
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public boolean isSetUp() {
        return user_list != null && !dateStarted.equals("");
    }

    public String toString(){
        String temp="";
        if (user_list.size()>0) {
            for (int i = 0; i < user_list.size(); i++) {
                if (i == user_list.size() - 1) {
                    temp += user_list.get(i).toString();
                } else {
                    temp += user_list.get(i).toString() + ", ";
                }
            }
            return temp;
        } else{
            return temp;
        }

    }

    public class UserComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getScore() - o2.getScore();
        }
    }
}
