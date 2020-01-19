package com.example.ufthack.ui;

// import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.ufthack.R;
import com.example.ufthack.model.UserDatabase;
import com.example.ufthack.model.VapeEvent;

import java.util.ArrayList;

public class AddFragment extends Fragment implements TimePickerFragment.TimePickerLister {

    public static ArrayList<VapeTime> VAPE_TIMES;

    static {
        VAPE_TIMES = new ArrayList<>();
    }

    Button time,enter;
    View view;
    Context context;
    EditText text;
    String amount, Hour, Minutes;
    private FragmentActivity myContext;

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add,container,false);
        time = view.findViewById(R.id.timebottom);
        text = view.findViewById(R.id.amount);
        enter = view.findViewById(R.id.enter);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getFragmentManager(),"TimePicker");
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = text.getText().toString();
                VapeTime vapeTime = new VapeTime(amount,Hour,Minutes);
                // VAPE_TIMES.add(vapeTime);
                Double d = Double.parseDouble(amount);

                String time = Hour + "-" + Minutes + "-" + "19-01-2020";
                UserDatabase.CURRENT_USER.addEvent(new VapeEvent(time, d, 1));
            }
        });


        return view;



    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        time.setText("Hour = " + hour +" Minute = "+minute);
        Hour = String.valueOf(hour);
        Minutes = String.valueOf(minute);
    }
}
