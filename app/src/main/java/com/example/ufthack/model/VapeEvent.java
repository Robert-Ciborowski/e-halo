package com.example.ufthack.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VapeEvent
{
    private String time;
    private double nicotine;
    private int puffs;

    public VapeEvent(String time, double nicotine, int puffs)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        time = dtf.format(now);
        puffs = puffs;
        nicotine = nicotine;
    }


    public double getNicotine()
    {
        return nicotine;
    }


    public int getPuffs()
    {
        return puffs;
    }

}