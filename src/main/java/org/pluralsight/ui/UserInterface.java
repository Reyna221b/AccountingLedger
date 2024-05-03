package org.pluralsight.ui;

import org.pluralsight.models.Report;
import org.pluralsight.services.Logger;
import org.pluralsight.models.LogEntry;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserInterface
{
    static final Scanner userInput = new Scanner(System.in);
    private final Logger logger = new Logger("transactions");

    public static String getHomeScreenChoice()
    {
        while (true) {
            try {
                System.out.println(Colors.CYAN);
                System.out.println("\t\tHome Screen");
                System.out.println("-".repeat(30));
                System.out.println("[D] - Add Deposit");
                System.out.println("[P] - Make Payments");
                System.out.println("[S] - Savings");
                System.out.println("[L] - Ledger");
                System.out.println(Colors.RED + "[X] - Exit"+ Colors.RESET);
                System.out.println(Colors.CYAN+ "-".repeat(30));
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
                System.out.print("Please describe purchase:  ");
                String itemDescription = userInput.nextLine().strip();
                System.out.print("Please enter who you paid: ");
                String name = userInput.nextLine().strip();
                System.out.print("Please enter the amount paid: $ ");
                double amount = Double.parseDouble(userInput.nextLine().strip());
                amount = amount * -1;
                logger.logMessage(itemDescription, name, amount);

                System.out.print("\nWould you like to make another Payment? (Y/N): ");
                String input = userInput.nextLine().toLowerCase().strip();
                if (!input.equals("y") && !input.equals("yes")) {
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println(Colors.RED +"invalid input!"+Colors.RESET);
            }
        }
    }

    public void addToSavings()
    {
        while (true) {
            try {
                System.out.print("Please enter the amount you would like to add to your savings: ");
                double amount = Double.parseDouble(userInput.nextLine().strip());
                System.out.print("Please enter a savings category(car, home, general, etc): ");
                String name = userInput.nextLine().strip().toLowerCase();
                String invoice = "Savings" ;

                logger.logMessage(invoice, name, amount);
                System.out.println("You have successfully deposited $ " + amount + " to your " + name + " savings.");
                System.out.print("\nWould you like to make another deposit? (Y/N): ");
                String input = userInput.nextLine().toLowerCase().strip();
                if(!input.equals("y") && !input.equals("yes"))
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
                System.out.println("\t\tLedger Screen");
                System.out.println("-".repeat(30));
                System.out.println("[A] - All Entries");
                System.out.println("[D] - Deposits");
                System.out.println("[P] - Payments");
                System.out.println("[S] - Savings");
                System.out.println("[R] - Reports");
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
        System.out.println("\nAll Entries");
        System.out.println("-".repeat(140));

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
        System.out.println("\nAll Deposits");
        System.out.println("-".repeat(140));
        ArrayList<LogEntry> logEntries = logger.readEntries();
        Collections.reverse(logEntries);
        double total = 0;
        for(LogEntry entry: logEntries){

            if (entry.getAmount() > 0 )
            {
                System.out.println(entry);
                total += entry.getAmount();
            }
        }
        System.out.printf(Colors.YELLOW + "Total Deposit: $ %.2f\n" + Colors.RESET, total);
    }

    public void displayPayments()
    {
        System.out.println("\nAll Payments");
        System.out.println("-".repeat(140));
        ArrayList<LogEntry> logEntries = logger.readEntries();
        Collections.reverse(logEntries);
        double total = 0;
        for(LogEntry entry: logEntries){

            if (entry.getAmount() < 0 ){
                System.out.println(entry);
                total += entry.getAmount();
            }
        }
        System.out.printf(Colors.YELLOW + "Total Payments: $ %.2f\n" + Colors.RESET, total);
    }

    public void displaySavings()
    {
        HashMap<String, Double> savingsMaps = new HashMap<>();
        System.out.println("\nAll Savings");
        System.out.println("-".repeat(140));
        ArrayList<LogEntry> logEntries = logger.readEntries();
        Collections.reverse(logEntries);

        double total = 0;
        for (LogEntry savings : logEntries.stream().filter(entry -> entry.getDescription().
                        equalsIgnoreCase("Savings")).
                collect((Collectors.toList())))
        {
            double categoryTotal = savingsMaps.getOrDefault(savings.getVendor(), 0.0);
            categoryTotal += savings.getAmount();
            savingsMaps.put(savings.getVendor(), categoryTotal);

            System.out.println(savings);

            total += savings.getAmount();
        }

        for (Map.Entry<String, Double> entry : savingsMaps.entrySet()) {
            System.out.printf("Total " + entry.getKey() + " Savings: $ %.2f\n", entry.getValue());
        }
        System.out.printf(Colors.YELLOW + "Total Savings: $ %.2f\n" + Colors.RESET, total);

    }


    public int getReportDisplay(){
        while (true)
        {
            try
            {
                System.out.println(Colors.PURPLE);
                System.out.println("\t\tReport Screen");
                System.out.println("-".repeat(30));
                System.out.println("Search By: ");
                System.out.println("[1] - Month To Date");
                System.out.println("[2] - Previous Month");
                System.out.println("[3] - Year To Date");
                System.out.println("[4] - Previous Year");
                System.out.println("[5] - Search By Vendor");
                System.out.println("[6] - Custom Search");
                System.out.println(Colors.BLUE + "[0] - Exit To Ledger Screen"+ Colors.RESET);
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

    public List <LogEntry> userInputSearch(){

            while(true){

                try {
                    List<LogEntry> search = logger.readEntries();
                    System.out.println("\nCustom Search:");
                    System.out.println("-".repeat(60));

                    System.out.print("Enter start date (yyyy-MM-dd) or leave empty: ");
                    String startDateStr = userInput.nextLine().strip();
                    LocalDate startDate = startDateStr.isEmpty() ? null : LocalDate.parse(startDateStr);

                    System.out.print("Enter end date (yyyy-MM-dd) or leave empty: ");
                    String endDateStr = userInput.nextLine().strip();
                    LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr);

                    System.out.print("Enter description or leave empty: ");
                    String description = userInput.nextLine().trim().toLowerCase();

                    System.out.print("Enter vendor or leave empty: ");
                    String vendor = userInput.nextLine().strip().toLowerCase();

                    System.out.print("Enter amount or leave empty: ");
                    String amountStr = userInput.nextLine().strip();
                    Double amount = amountStr.isEmpty() ? null : Double.parseDouble(amountStr);

                    System.out.println("\nFiltered Results: ");
                    System.out.println("-".repeat(138));

                    return Report.customSearch(search,startDate,endDate,description,vendor, amount);

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

