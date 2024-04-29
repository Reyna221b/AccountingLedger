package org.pluralsight;



import org.pluralsight.templates.LogEntry;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    static final Scanner userInput = new Scanner(System.in);
    private static int i = 0;
    private final Logger logger = new Logger("transactions");

    public static String getHomeScreenChoice()
    {
        while (true) {
            try {
                System.out.println();
                System.out.println("Home Screen!");
                System.out.println("---------------------------------");
                System.out.println("D - Add Deposit");
                System.out.println("P - Make Payments");
                System.out.println("L - Ledger");
                System.out.println("X - Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                return userInput.nextLine().strip().toLowerCase();

            } catch (Exception ex) {
                System.out.println("invalid selection!");

            }
        }
    }

    public void addDeposit()
    {

        System.out.print("Please enter the amount you would like to deposit: ");
        double amount = Double.parseDouble(userInput.nextLine().strip());
        System.out.print("Please enter who you got paid by: ");
        String name = userInput.nextLine().strip();
        i++;
        String invoice = "Invoice Paid 100" + i;


        logger.logMessage(invoice, name, amount);
    }

    public void makePayment()
    {
        System.out.print("Please enter a description of what you purchased:  ");
        String itemDescription = userInput.nextLine().strip();
        System.out.print("Please enter vendors: ");
        String name = userInput.nextLine().strip();
        System.out.print("Please enter the amount paid: $ ");
        double amount = Double.parseDouble(userInput.nextLine().strip());
        amount = amount * -1;
        logger.logMessage(itemDescription,name,amount);

    }


    public String getLedgerDisplayChoice()
    {
        while (true)
        {
            try
            {
                System.out.println();
                System.out.println("Ledger Screen");
                System.out.println("---------------------------------");
                System.out.println("A - All Entries");
                System.out.println("D - Deposits");
                System.out.println("P - Payments");
                System.out.println("R - Reports");
                System.out.println("H - Home");
                System.out.println();
                System.out.print("Enter your choice: ");
                return userInput.nextLine().strip().toLowerCase();
            }
            catch(Exception ex)
            {
                System.out.println("Invalid selection. Please Try Again.");
            }
        }

    }
    public void displayEntries()
    {
        ArrayList<LogEntry> logEntries = logger.readEntries();
        System.out.println();
        System.out.println("All Entries");
        System.out.println("-".repeat(35));

        for (int i = logEntries.size()-1; i >= 0; i--){


            System.out.println(logEntries.get(i));
        }
        System.out.println();
    }

    public void displayDeposits()
    {
        ArrayList<LogEntry> logEntries = logger.readEntries();
        for(LogEntry entry: logEntries){


            if (entry.getAmount() > 0 )
            {
                System.out.println(entry);
            }
        }

    }

    public void displayPayments()
    {
        ArrayList<LogEntry> logEntries = logger.readEntries();
        for(LogEntry entry: logEntries){

            if (entry.getAmount() < 0 ){
                System.out.println(entry);
            }
        }

    }

}

