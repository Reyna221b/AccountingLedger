package org.pluralsight;
import org.pluralsight.templates.LogEntry;

import java.util.List;


public class Account
{
    private final Logger logger = new Logger("transactions");

    UserInterface ui = new UserInterface();


    public void run()
    {
        System.out.println("Welcome!");
        while(true)
        {

            String choice = ui.getHomeScreenChoice();

            switch(choice)
            {
                case "d":
                    ui.addDeposit();
                    break;
                case "p":
                    ui.makePayment();
                    break;
                case "l":
                    ledgerDisplay();
                    break;
                case "x":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("****That was an invalid selection.****");
                    break;
            }

        }
    }

    public void ledgerDisplay()
    {
        while(true)
        {
            String choice = ui.getLedgerDisplayChoice();

            switch(choice)
            {
                case "a":
                    ui.displayEntries();
                    break;
                case "d":
                    ui.displayDeposits();
                    break;
                case "p":
                    ui.displayPayments();
                    break;
                case "r":
                    reportDisplayScreen();
                    break;
                case "h":
                    System.out.println("Back to home screen we go!!");
                    return;
                default:
                    System.out.println("*****That was an invalid selection.******");
                    break;
            }

        }
    }

    public void reportDisplayScreen()
    {

        List<LogEntry> logEntryList = logger.readEntries();
        while(true) {

            int choice = ui.getReportDisplay();

            switch (choice)
            {
                case 1:
                    List<LogEntry> monthToDate = Report.monthToDate(logEntryList);
                    System.out.println("\nMonth to date Report: ");
                    System.out.println("-".repeat(60));
                    Report.reportResults(monthToDate);
                    break;
                case 2:
                    List<LogEntry> previousMonth = Report.previousMonth(logEntryList);
                    System.out.println("\nPrevious Month Report: ");
                    System.out.println("-".repeat(60));
                    Report.reportResults(previousMonth);
                    break;
                case 3:
                    List<LogEntry> yearToDate = Report.yearToDate(logEntryList);
                    System.out.println("\nYear to date Report ");
                    System.out.println("-".repeat(60));
                    Report.reportResults(yearToDate);
                    break;
                case 4:
                    List<LogEntry> previousYear = Report.previousYear(logEntryList);
                    System.out.println("\nPrevious Year Report: ");
                    System.out.println("-".repeat(60));
                    Report.reportResults(previousYear);
                    break;
                case 5:
                    String name = ui.getVendorName();
                    System.out.println("\nVendor Report ");
                    System.out.println("-".repeat(60));
                    List<LogEntry> searchVendor = Report.searchByVendor(logEntryList,name);
                    Report.reportResults(searchVendor);
                    break;
                case 0:
                    System.out.println("Back to Ledger screen we go!!");
                    return;
                default:
                    System.out.println("That was an invalid selection.");
                    break;
            }
        }

    }

}
