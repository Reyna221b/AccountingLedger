package org.pluralsight.services;
import org.pluralsight.models.LogEntry;
import org.pluralsight.models.Report;
import org.pluralsight.ui.Colors;
import org.pluralsight.ui.UserInterface;

import java.util.List;


public class AccountApp
{
    private final Logger logger = new Logger("transactions");

    UserInterface ui = new UserInterface();


    public void run()
    {
        ui.message(" \n" +
                "\n" +
                " .+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+. \n" +
                "(     __        __   _                                )\n" +
                " )    \\ \\      / /__| | ___ ___  _ __ ___   ___      ( \n" +
                "(      \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\      )\n" +
                " )      \\ V  V /  __/ | (_| (_) | | | | | |  __/     ( \n" +
                "(        \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|      )\n" +
                " )                                                   ( \n" +
                "(                                                     )\n" +
                " \"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\" \n" +
                "\n");
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
                case"s":
                    ui.addToSavings();
                    break;
                case "l":
                    ledgerDisplay();
                    break;
                case "x":
                    ui.message(" \t\t      ,---------------------------,\n" +
                            "              |  /---------------------\\  |\n" +
                            "              | |                       | |\n" +
                            "              | |     Goodbye!          | |\n" +
                            "              | |      Goodbye!         | |\n" +
                            "              | |       GoodBye!         | |\n" +
                            "              | |                       | |\n" +
                            "              |  \\_____________________/  |\n" +
                            "              |___________________________|\n" +
                            "            ,---\\_____     []     _______/------,\n" +
                            "          /         /______________\\           /|\n" +
                            "        /___________________________________ /  | ___\n" +
                            "        |                                   |   |    )\n" +
                            "        |  _ _ _                 [-------]  |   |   (\n" +
                            "        |  o o o                 [-------]  |  /    _)_\n" +
                            "        |__________________________________ |/     /  /\n" +
                            "    /-------------------------------------/|      ( )/\n" +
                            "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n" +
                            "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return;
                default:
                    ui.message(Colors.RED + "****That was an invalid selection.****"+ Colors.RESET);
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
                case "s":
                    ui.displaySavings();
                    break;
                case "r":
                    reportDisplayScreen();
                    break;
                case "h":
                    ui.message(Colors.YELLOW+"Back to Home screen we go!!"+ Colors.RESET);

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
                    ui.message("\nMonth to date Report: ");
                    ui.message("-".repeat(120));
                    Report.reportResults(monthToDate);
                    break;
                case 2:
                    List<LogEntry> previousMonth = Report.previousMonth(logEntryList);
                    ui.message("\nPrevious Month Report: ");
                    ui.message("-".repeat(120));
                    Report.reportResults(previousMonth);
                    break;
                case 3:
                    List<LogEntry> yearToDate = Report.yearToDate(logEntryList);
                    ui.message("\nYear to date Report ");
                    ui.message("-".repeat(120));
                    Report.reportResults(yearToDate);
                    break;
                case 4:
                    List<LogEntry> previousYear = Report.previousYear(logEntryList);
                    ui.message("\nPrevious Year Report: ");
                    ui.message("-".repeat(120));
                    Report.reportResults(previousYear);
                    break;
                case 5:
                    String name = ui.getVendorName();
                    ui.message("\nVendor Report ");
                    ui.message("-".repeat(120));
                    List<LogEntry> searchVendor = Report.searchByVendor(logEntryList,name);
                    Report.reportResults(searchVendor);
                    break;
                case 6:
                    Report.reportResults(ui.userInputSearch());
                    break;
                case 0:
                    ui.message(Colors.YELLOW+"Back to Ledger screen we go!!"+ Colors.RESET);
                    return;
                default:
                    ui.message(Colors.RED+"*****That was an invalid selection.******"+Colors.RESET);
                    break;
            }
        }

    }

}
