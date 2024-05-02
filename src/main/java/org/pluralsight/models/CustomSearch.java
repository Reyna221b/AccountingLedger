package org.pluralsight.models;

import java.time.LocalDate;

public class CustomSearch
{
    private LocalDate start;
    private LocalDate end;
    private String description;
    private String vendor;
    private double amount;

    public CustomSearch(LocalDate start,LocalDate end, String vendor, String description, double amount)
    {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;

    }

    public LocalDate getStart()
    {
        return start;
    }

    public void setStart(LocalDate start)
    {
        this.start = start;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setUSerAmount(double amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getEnd()
    {
        return end;
    }

    public void setEnd(LocalDate end)
    {
        this.end = end;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }
}
