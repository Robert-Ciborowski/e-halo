package com.example.ufthack.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;
import java.util.Date;

import com.example.ufthack.R;
import com.example.ufthack.model.UserDatabase;
import com.example.ufthack.model.VapeEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        List<EventDay> events = new ArrayList<>();

        for (VapeEvent e : UserDatabase.CURRENT_USER.events) {
            String[] array = e.getTime().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(array[4]), Integer.parseInt(array[3]) - 1, Integer.parseInt(array[2]));
            events.add(new EventDay(calendar, R.drawable.smoke));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 19);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        try {
            calendarView.setDate(new Date());
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                String message = "";
                message += clickedDayCalendar.getTime().getDate() + "-";
                message += clickedDayCalendar.getTime().getMonth() + "-";
                message += clickedDayCalendar.getTime().getYear();

//                System.out.println(clickedDayCalendar.getTime().getDate());
//                System.out.println(clickedDayCalendar.getTime().getMonth());
//                System.out.println(clickedDayCalendar.getTime().getYear());
                Intent intent = new Intent(Main2Activity.ACTIVITY_HANDLE, VapeViewActivity.class);
                intent.putExtra("date", message);
                startActivity(intent);
            }
        });

        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                // System.out.println("prev");
            }
        });

        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                // System.out.println("next");
            }
        });

        return view;
    }
}
