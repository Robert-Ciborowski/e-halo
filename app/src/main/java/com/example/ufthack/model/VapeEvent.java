package com.example.ufthack.model;

public class VapeEvent
{
    private String time;
    private Double nicotine;
    private Integer puffs;

    public VapeEvent(String time, double nicotine, int puffs)
    {
        this.puffs = puffs;
        this.nicotine = nicotine;
        this.time = time;
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