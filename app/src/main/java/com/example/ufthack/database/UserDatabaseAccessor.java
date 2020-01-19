package com.example.ufthack.database;

import androidx.annotation.NonNull;

import com.example.ufthack.model.User;
import com.example.ufthack.model.VapeEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDatabaseAccessor {
    private DatabaseReference mDatabase;
    private static UserDatabaseAccessor UserDatabaseAccessor;
    private UserRequest userRequest = null;

    static {
        UserDatabaseAccessor = new UserDatabaseAccessor();
    }

    public static UserDatabaseAccessor getInstance() {
        return UserDatabaseAccessor;
    }

    public UserDatabaseAccessor() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void createNewUser(String name, String email){
        User user = new User(name, email);
        DatabaseReference newUserRef = mDatabase.child("users").push();
        newUserRef.setValue(user);
        newUserRef.child("username").setValue(name);
    }

    public void getUserByID(String id, final OnDataRetrieval<User> onDataRetrieval) {
        userRequest = new UserRequest();
        userRequest.user = new User();
        mDatabase.child("test").setValue("hi");
        DatabaseReference ref = mDatabase.child("users").child(id);
        DatabaseReference ref2 = ref.child("email");
        DatabaseReference ref3 = ref.child("username");
        final DatabaseReference ref4 = ref.child("vape_dates");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue(String.class);
                userRequest.user.setEmail(email);

                if (userRequest.user.isSetUp()) {
                    addEventsToUser(ref4, userRequest.user, new OnDataRetrieval<ArrayList<VapeEvent>>() {
                        @Override
                        public void onRetrieval() {
                            onDataRetrieval.data = userRequest.user;
                            onDataRetrieval.onRetrieval();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });

        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                userRequest.user.setUsername(username);

                if (userRequest.user.isSetUp()) {
                    addEventsToUser(ref4, userRequest.user, new OnDataRetrieval<ArrayList<VapeEvent>>() {
                        @Override
                        public void onRetrieval() {
                            onDataRetrieval.data = userRequest.user;
                            onDataRetrieval.onRetrieval();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });
    }

    private void addEventsToUser(DatabaseReference ref4, User user, final OnDataRetrieval<ArrayList<VapeEvent>> onDataRetrieval) {
        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userRequest.user.addEvent(new VapeEvent(snapshot.getKey(), snapshot.getValue(Double.class), 0));
                }

                if (userRequest.user.isSetUp()) {
                    onDataRetrieval.onRetrieval();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });
    }

    public void getAllUserIDs(final OnDataRetrieval<ArrayList<String>> onDataRetrieval) {
        DatabaseReference ref = mDatabase.child("users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    keys.add(snapshot.getKey());
                }

                onDataRetrieval.data = keys;
                onDataRetrieval.onRetrieval();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("NOPE");
            }
        });
    }

    public void getUsersByID(String[] id, final OnDataRetrieval<ArrayList<User>> onDataRetrieval) {
        final int size = id.length;
        final ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            getUserByID(id[i], new OnDataRetrieval<User>() {
                @Override
                public void onRetrieval() {
                    users.add((User) data);

                    if (users.size() == size) {
                        onDataRetrieval.data = users;
                        onDataRetrieval.onRetrieval();
                    }
                }
            });
        }
    }

    public void updateUser(String id, User user) {
        DatabaseReference ref = mDatabase.child("users").child(id);
        ref.child("email").setValue(user.getEmail());
        ref.child("username").setValue(user.getUsername());
        for (int i=0; i < user.events.size(); i++){
            ref.child("vape_events").setValue(user.events.get(i));
        }
    }

    public void updateUserProperty(String id, String property, String value) {
        mDatabase.child("users").child(id).child(property).setValue(value);
    }

    public User getUserByEmail(String email) {

//        String id = ...;
//        return getUserByID(id);
        return null;
    }

}
