package com.example.ufthack.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ufthack.R;
import com.example.ufthack.model.UserDatabase;
import com.example.ufthack.model.VapeEvent;

import java.util.ArrayList;
import java.util.List;

public class VapeViewActivity extends AppCompatActivity {
    private RecyclerView myrecyclerview;
    private List<VapeTime> vapeTimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vape_view);

        vapeTimeList = new ArrayList<>();
//        vapeTimeList.add(new VapeTime("0.05", "15", "35"));
//        vapeTimeList.add(new VapeTime("0.05", "16", "25"));
//        vapeTimeList.add(new VapeTime("0.02", "17", "25"));

        Intent intent = getIntent();
        String message = intent.getStringExtra("date");
        System.out.println("DATE: " + message);
        String[] data = message.split("-");
        int day = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]) + 1;
        int year = Integer.parseInt(data[2]) + 1900;
        ArrayList<VapeEvent> events = UserDatabase.CURRENT_USER.getEventsByDate(day, month, year);

        for (VapeEvent e : events) {
            vapeTimeList.add(new VapeTime(e.getNicotine().toString(), e.getHour(), e.getMinute()));
        }

        myrecyclerview = (RecyclerView) findViewById(R.id.recyclerview2);
        VapeEventRecyclerViewAdapter recyclerViewAdapter2 = new VapeEventRecyclerViewAdapter(this.getApplicationContext(), vapeTimeList);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(recyclerViewAdapter2);
    }

    public VapeViewActivity(){

    }
}
