package org.pluralsight.models;

import org.pluralsight.ui.Colors;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogEntry
{
    private  LocalDate times;
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
        return String.format(Colors.WHITE_BG + Colors.BLACK +
                        "Date: %-15s Time: %-20s Description: %-25s Vendor: %-20s Amount: %.2f" + Colors.RESET +"\n",
                date, time, description,vendor,amount);
    }
}
