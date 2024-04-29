package org.pluralsight;
import org.pluralsight.templates.LogEntry;



public class Account
{
    //private Scanner userInput = new Scanner(System.in);
    UserInterface ui = new UserInterface();


    public void run()
    {
        System.out.println("Welcome!");
        while(true)
        {

            String choice = ui.getHomeScreenChoice();

            switch(choice)
            {
                case "d": // available books
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
                    System.out.println("That was an invalid selection.");
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
                    System.out.println("Goodbye!");
                    return;
                case "h":
                    System.out.println("Back to home screen we go!!");
                    return;
                default:
                    System.out.println("That was an invalid selection.");
                    break;
            }

        }
    }


}
