package com.example.ufthack.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public int score = 0;
    public ArrayList<VapeEvent> events;

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
}