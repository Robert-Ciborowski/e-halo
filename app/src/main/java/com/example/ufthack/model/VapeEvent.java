package com.example.ufthack.model;

public class VapeEvent
{
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMinute() {
        return time.split("-")[1];
    }

    public String getHour() {
        return time.split("-")[0];
    }

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