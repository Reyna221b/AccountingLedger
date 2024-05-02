package org.pluralsight.services;
import org.pluralsight.models.LogEntry;
import org.pluralsight.models.Report;
import org.pluralsight.ui.Colors;
import org.pluralsight.ui.UserInterface;

import java.util.Collections;
import java.util.List;


public class AccountApp
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
                    System.out.println(Colors.RED + "****That was an invalid selection.****"+ Colors.RESET);
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
                    ui.message(Colors.CYAN+"Back to home screen we go!!"+ Colors.RESET);

                    return;
                default:
                    ui.message(Colors.RED+"*****That was an invalid selection.******"+Colors.RESET);
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
                    Collections.reverse(monthToDate);
                    ui.message("\nMonth to date Report: ");
                    ui.message("-".repeat(60));
                    Report.reportResults(monthToDate);
                    break;
                case 2:
                    List<LogEntry> previousMonth = Report.previousMonth(logEntryList);
                    Collections.reverse(previousMonth);
                    ui.message("\nPrevious Month Report: ");
                    ui.message("-".repeat(60));
                    Report.reportResults(previousMonth);
                    break;
                case 3:
                    List<LogEntry> yearToDate = Report.yearToDate(logEntryList);
                    Collections.reverse(yearToDate);
                    ui.message("\nYear to date Report ");
                    ui.message("-".repeat(60));
                    Report.reportResults(yearToDate);
                    break;
                case 4:
                    List<LogEntry> previousYear = Report.previousYear(logEntryList);
                    Collections.reverse(previousYear);
                    ui.message("\nPrevious Year Report: ");
                    ui.message("-".repeat(60));
                    Report.reportResults(previousYear);
                    break;
                case 5:
                    String name = ui.getVendorName();
                    ui.message("\nVendor Report ");
                    ui.message("-".repeat(60));
                    List<LogEntry> searchVendor = Report.searchByVendor(logEntryList,name);
                    Report.reportResults(searchVendor);
                    break;
                case 0:
                    ui.message(Colors.CYAN+"Back to Ledger screen we go!!"+ Colors.RESET);
                    return;
                default:
                    ui.message(Colors.RED+"*****That was an invalid selection.******"+Colors.RESET);
                    break;
            }
        }

    }

}
