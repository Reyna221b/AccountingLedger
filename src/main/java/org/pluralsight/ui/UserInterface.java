package org.pluralsight.ui;

import org.pluralsight.services.Logger;
import org.pluralsight.models.LogEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    static final Scanner userInput = new Scanner(System.in);
    private final Logger logger = new Logger("transactions");

    public static String getHomeScreenChoice()
    {
        while (true) {
            try {
                System.out.println(Colors.YELLOW);
                System.out.println("Home Screen");
                System.out.println("-".repeat(30));
                System.out.println("D - Add Deposit");
                System.out.println("P - Make Payments");
                System.out.println("L - Ledger");
                System.out.println(Colors.RED + "X - Exit"+ Colors.RESET);
                System.out.println(Colors.YELLOW+ "-".repeat(30));
                System.out.print(Colors.RESET);
                System.out.print("Enter your choice: ");
                return userInput.nextLine().strip().toLowerCase();

            } catch (Exception ex) {
                System.out.println(Colors.RED + "invalid selection!"+Colors.RESET);
            }
        }
    }

    public void addDeposit()
    {
        while (true) {
            try {
                System.out.print("Please enter the amount you would like to deposit: ");
                double amount = Double.parseDouble(userInput.nextLine().strip());
                System.out.print("Please enter who you got paid by: ");
                String name = userInput.nextLine().strip();
                String invoice = "Invoice Paid" ;

                logger.logMessage(invoice, name, amount);
                System.out.println("You have successfully deposited $ " + amount + " to your account.");
                System.out.print("\nWould you like to make another deposit? (Y/N): ");
                String input = userInput.nextLine().toLowerCase().strip();
                if(input.equals("n")||input.equals("no"))
                {
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println(Colors.RED +"invalid input!"+Colors.RESET);
            }

        }

    }

    public void makePayment()
    {
        while (true) {
            try {
                System.out.print("Please enter a description of what you purchased:  ");
                String itemDescription = userInput.nextLine().strip();
                System.out.print("Please enter vendor's name: ");
                String name = userInput.nextLine().strip();
                System.out.print("Please enter the amount paid: $ ");
                double amount = Double.parseDouble(userInput.nextLine().strip());
                amount = amount * -1;
                logger.logMessage(itemDescription,name,amount);

                System.out.print("\nWould you like to make another Payment? (Y/N): ");
                String input = userInput.nextLine().toLowerCase().strip();
                if(input.equals("n")||input.equals("no"))
                {
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println(Colors.RED +"invalid input!"+Colors.RESET);
            }
        }
    }


    public String getLedgerDisplayChoice()
    {
        while (true)
        {
            try
            {
                System.out.println(Colors.BLUE);
                System.out.println("Ledger Screen");
                System.out.println("-".repeat(30));
                System.out.println("A - All Entries");
                System.out.println("D - Deposits");
                System.out.println("P - Payments");
                System.out.println("R - Reports");
                System.out.println(Colors.GREEN + "H - Home"+ Colors.RESET);
                System.out.println(Colors.BLUE + "-".repeat(30));
                System.out.print(Colors.RESET);
                System.out.print("Enter your choice: ");
                return userInput.nextLine().strip().toLowerCase();
            }
            catch(Exception ex)
            {
                System.out.println(Colors.RED + "Invalid selection. Please Try Again." + Colors.RESET);
            }
        }

    }
    public void displayEntries()
    {
        List<LogEntry> logEntries = logger.readEntries();
        System.out.println();
        System.out.println("All Entries");
        System.out.println("-".repeat(70));

        for (int i = logEntries.size()-1; i >= 0; i--){

            System.out.println(logEntries.get(i));
        }
        if (logEntries.isEmpty()){
            System.out.println("No Record Found.");
        }
        System.out.println();
    }

    public void displayDeposits()
    {
        System.out.println("All Deposits");
        System.out.println("-".repeat(70));
        ArrayList<LogEntry> logEntries = logger.readEntries();
        Collections.reverse(logEntries);
        for(LogEntry entry: logEntries){

            if (entry.getAmount() > 0 )
            {
                System.out.println(entry);
            }
        }
    }

    public void displayPayments()
    {
        System.out.println("All Payments");
        System.out.println("-".repeat(70));
        ArrayList<LogEntry> logEntries = logger.readEntries();
        Collections.reverse(logEntries);
        for(LogEntry entry: logEntries){

            if (entry.getAmount() < 0 ){
                System.out.println(entry);
            }
        }
    }

    public int getReportDisplay(){
        while (true)
        {
            try
            {
                System.out.println(Colors.PURPLE);
                System.out.println("Report Screen");
                System.out.println("-".repeat(30));
                System.out.println("Search By: ");
                System.out.println("1 - Month To Date");
                System.out.println("2 - Previous Month");
                System.out.println("3 - Year To Date");
                System.out.println("4 - Previous Year");
                System.out.println("5 - Search By Vendor");
                System.out.println(Colors.BLUE + "0 - Exit To Ledger Screen"+ Colors.RESET);
                System.out.println(Colors.PURPLE + "-".repeat(30));
                System.out.print(Colors.RESET);
                System.out.print("Enter your choice: ");
                return Integer.parseInt(userInput.nextLine().strip());
            }
            catch(Exception ex)
            {
                System.out.println(Colors.RED + "Invalid selection. Please Try Again."+ Colors.RESET);
            }
        }
    }

    public String getVendorName()
    {
        while (true)
        {
            try
            {
                System.out.print("Please Enter Vendor's Name: ");
                return userInput.nextLine().strip().toLowerCase();
            }
            catch(Exception ex)
            {
                System.out.println(Colors.RED + "Invalid selection. Please Try Again."+ Colors.RESET);
            }
        }
    }

    public void message(String displayString){
        System.out.println(displayString);
    }

}

