package org.pluralsight.templates;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogEntry
{
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public LogEntry(LocalDate date, LocalTime time, String description, String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount; 
    }


    public LocalDate getDate()
    {
        return date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
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

    public String toString()
    {
        return String.format("Date: %-15s Time: %-20s Description: %-30s Vendor: %-25s Amount: %.2f\n", date, time,
                description,vendor,amount);
    }
}
