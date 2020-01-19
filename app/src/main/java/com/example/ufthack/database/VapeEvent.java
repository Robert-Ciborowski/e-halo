package com.example.ufthack.database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VapeEvent
{
    private String time;
    private Double nicotine;
    private Integer puffs;

    public VapeEvent(String time, Double nicotine, Integer puffs)
    {
        this.time = time;
        this.puffs = puffs;
        this.nicotine = nicotine;
    }


    public Double getNicotine()
    {
        return nicotine;
    }


    public Integer getPuffs()
    {
        return puffs;
    }

    public String toString(){
        return "VapeEvent(" + time + " " + nicotine + " " + puffs + ")";
    }

}