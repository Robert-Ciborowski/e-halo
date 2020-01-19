package com.example.ufthack.database;

import androidx.annotation.NonNull;

import com.example.ufthack.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeagueDatabaseAccessor {
    private DatabaseReference mDatabase;
    private static LeagueDatabaseAccessor LeagueDatabaseAccessor;
    private LeagueRequest leagueRequest = null;

    public LeagueDatabaseAccessor() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    static {
        LeagueDatabaseAccessor = new LeagueDatabaseAccessor();
    }

    public static LeagueDatabaseAccessor getInstance() {
        return LeagueDatabaseAccessor;
    }

    public void getLeague(String leagueName, final OnDataRetrieval<League> onDataRetrieval) {
        DatabaseReference ref = mDatabase.child("League").child(leagueName);
        DatabaseReference ref2 = ref.child("Users");
        DatabaseReference ref3 = ref.child("Date started");
        final League league = new League();

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String users = dataSnapshot.getValue(String.class);
                UserDatabaseAccessor.getInstance().getUsersByID(users.split(","), new OnDataRetrieval<ArrayList<User>>() {
                    @Override
                    public void onRetrieval() {
                        league.append(data);

                        if (league.isSetUp()) {
                            onDataRetrieval.data = league;
                            onDataRetrieval.onRetrieval();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });

        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                league.setDateStarted(date);

                if (league.isSetUp()) {
                    onDataRetrieval.data = league;
                    onDataRetrieval.onRetrieval();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });
    }
}
