package com.example.ufthack.database;

import androidx.annotation.NonNull;

import com.example.ufthack.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DatabaseTesting {
    private DatabaseReference mDatabase;

    public DatabaseTesting() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void performTestOnUser(User user){
//        DatabaseAccessor.getInstance().updateUser("-LytX2fLXjpSdCF9QHJz", user);
        // DatabaseAccessor.getInstance().updateUserProperty("-LytX2fLXjpSdCF9QHJz","email", "Ur mum");
    }

    public void runTests() {

        // removeUsernamePropertyOfUser(hex);

//        for (int i=10; i < 30; i++){
//            Random random = new Random();
//            int val = random.nextInt(1000000000);
//            String hex = Integer.toHexString(val);
//            writeNewUser(hex, "James" + Integer.toString(i), "james.kibi.tang" + Integer.toString(i)+"@mail.utoronto.ca");
//            writeNewPost(hex, "James" + Integer.toString(i), "Good mOrNiNg!"+Integer.toString(i), "Kaye!"+Integer.toString(i));
//        }
//        OnDataRetrieval<User> onData = new OnDataRetrieval<User>() {
//            @Override
//            public void onRetrieval() {
//                User user = (User) data;
//                // user.username += " Tang";
//                System.out.println(user.toString());
//                performTestOnUser(user);
//            }
//        };
//
//        UserDatabaseAccessor.getInstance().getUserByID("-LytX2fLXjpSdCF9QHJz", onData);

//        LeagueDatabaseAccessor.getInstance().getLeague("Bronze", new OnDataRetrieval<League>() {
//            @Override
//            public void onRetrieval() {
//                System.out.println(((League) data).toString());
//            }
//        });

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        DatabaseReference newUserRef = mDatabase.child("users").push();
        newUserRef.setValue(user);
        newUserRef.child("username").setValue(name);
    }

    private void writeNewUserWithListeners(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Write was successful!
                // ...
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });

        mDatabase.child("users").child(userId).child("username").setValue(name);
    }

    private void removeUsernamePropertyOfUser(String userId) {
        mDatabase.child("users").child(userId).child("username").child("username").removeValue();
    }

    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}
